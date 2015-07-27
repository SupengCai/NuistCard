package com.linklife.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.linklife.domain.Nuist;
import com.linklife.domain.ibator.NuistInfo;
import com.linklife.domain.ibator.NuistLogin;
import com.linklife.domain.model.CardUnlostModel;
import com.linklife.domain.model.ChangePWDModel;
import com.linklife.domain.model.NuistChatModel;
import com.linklife.domain.model.NuistClassModel;
import com.linklife.domain.model.NuistCourseModel;
import com.linklife.domain.model.NuistHistoryModel;
import com.linklife.domain.model.NuistInfoModel;
import com.linklife.domain.model.NuistLoginHistoryModel;
import com.linklife.domain.model.NuistLoginModel;
import com.linklife.domain.model.NuistRechargeHistoryModel;
import com.linklife.domain.model.NuistScoreHistoryModel;
import com.linklife.domain.model.NuistSuggestModel;
import com.linklife.domain.model.NuistTradeModel;
import com.linklife.domain.model.RechargeModel;
import com.linklife.domain.model.StatisticData;
import com.linklife.web.base.utils.Base64;
import com.linklife.web.base.utils.RegexUtil;
import com.linklife.web.httpapi.CookieTool;
import com.linklife.web.httpapi.HttpUtils;
import com.linklife.web.httpapi.NuistCardAPI;

/**
 * <p>
 * AccountServiceImpl.java
 * </p>
 * 
 * <pre>
 * 南信大一卡通相关业务接口的实现类
 * </pre>
 * 
 * @author caisupeng
 */
@Service
public class NuistServiceImpl {

	/** 用户领域类 */
	@Autowired
	private Nuist nuist;

	/**
	 * 历史账单类型枚举
	 * 
	 * @author caisupeng
	 *
	 */
	public static enum AutoRecordType {
		NewUser, Regular;
	}

	protected static Log log = LogFactory.getLog("NuistServiceImpl");

	public int getAccountIdByUserNumber(String userNumber) {

		NuistInfoModel nuistInfoModel = new NuistInfoModel();
		nuistInfoModel.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel.setUsernumber(userNumber);
		nuistInfoModel.selectByUserNumber();
		return nuistInfoModel.getAccountId();

	}

	/**
	 * 用户登陆
	 * 
	 * @param nuistLoginModel
	 * @param request
	 * @param response
	 * @return int
	 */
	public JSONObject login(NuistLoginModel nuistLoginModel, HttpServletRequest request, HttpServletResponse response) {

		NuistCardAPI nuistCardAPI = new NuistCardAPI();
		JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(nuistLoginModel.getUsernumber(),
				NuistCardAPI.NUIST_CARD_LOGIN_API, getNuistLoginPostParams(nuistLoginModel));
		String remoteAddr = request.getRemoteAddr();

		if (jsonObject.isNull(NuistCardAPI.REQUEST_STATE)) {
			NuistLoginModel nuistLoginModleLocal = new NuistLoginModel();
			nuistLoginModleLocal.setUsernumber(nuistLoginModel.getUsernumber());
			nuistLoginModleLocal.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
			nuistLoginModleLocal.selectByUserNumber();
			if (nuistLoginModleLocal.getPassword().equals(nuistLoginModel.getPassword())) {
				// 存储用户Cookies
				CookieTool.addCookie(response, CookieTool.COOKIE_USER_NUMBER, nuistLoginModel.getUsernumber(), 60 * 10);
				if (nuistLoginModel.getRememberme()) {
					CookieTool.addCookie(response, CookieTool.COOKIE_USER_ACCOUNT, nuistLoginModel.getUsernumber(),
							60 * 60 * 24 * 7);
					CookieTool.addCookie(response, CookieTool.COOKIE_USER_PWD,
							Base64.Decode(nuistLoginModel.getPassword()), 60 * 60 * 24 * 7);
					CookieTool.addCookie(response, CookieTool.COOKIE_USER_REMEMBER, "1", 60 * 60 * 24 * 7);
				} else {
					CookieTool.clearCookie(response, CookieTool.COOKIE_USER_ACCOUNT);
					CookieTool.clearCookie(response, CookieTool.COOKIE_USER_PWD);
					CookieTool.clearCookie(response, CookieTool.COOKIE_USER_REMEMBER);
				}
				// 添加提示框显示判断Coookies
				CookieTool.addCookie(response, CookieTool.COOKIE_FIRST_PAGE, "1", 30);
				// 添加离线登陆Cookies
				CookieTool.addCookie(response, CookieTool.COOKIE_STATE_OFFLINE, "1", 60 * 10);
				// 添加用户登陆记录
				NuistLoginHistoryModel nuistLoginHistoryModel = new NuistLoginHistoryModel();
				nuistLoginHistoryModel.mockRepository(nuist.getNuistLoginHistoryModel().acquiredTargetRepository());
				// nuistLoginHistoryModel.insertLoginHistory(nuistLoginModleLocal.getAccountId(),
				// remoteAddr,
				// RegexUtil.explorerFilter(request.getHeader("User-Agent").toString()));
				jsonObject.append("success", "true");
			}
		}
		// 判断用户登陆状态
		else if (jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE)) {

			String old_PWD = nuistLoginModel.getPassword();

			nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());

			// 获取用户基本信息
			Elements trs = nuistCardAPI.getNuistCardInfo(nuistLoginModel.getUsernumber());
			int accountId = 0;
			if (nuistLoginModel.selectByPrimaryKey() == null)
				// 新用户首次登陆
				accountId = insertNuistInfo(nuistLoginModel, trs, remoteAddr);
			else
				// 老用户更新数据
				accountId = updateNuistInfo(nuistLoginModel, old_PWD, trs);

			// 存储用户Cookies
			CookieTool.addCookie(response, CookieTool.COOKIE_USER_NUMBER, nuistLoginModel.getUsernumber(), 60 * 10);
			if (nuistLoginModel.getRememberme()) {
				CookieTool.addCookie(response, CookieTool.COOKIE_USER_ACCOUNT, nuistLoginModel.getUsernumber(),
						60 * 60 * 24 * 7);
				CookieTool.addCookie(response, CookieTool.COOKIE_USER_PWD, Base64.Decode(nuistLoginModel.getPassword()),
						60 * 60 * 24 * 7);
				CookieTool.addCookie(response, CookieTool.COOKIE_USER_REMEMBER, "1", 60 * 60 * 24 * 7);
			} else {
				CookieTool.clearCookie(response, CookieTool.COOKIE_USER_ACCOUNT);
				CookieTool.clearCookie(response, CookieTool.COOKIE_USER_PWD);
				CookieTool.clearCookie(response, CookieTool.COOKIE_USER_REMEMBER);
			}
			// 添加提示框显示判断Coookies
			CookieTool.addCookie(response, CookieTool.COOKIE_FIRST_PAGE, "1", 30);
			// 添加手机API加密Cookies
			// String obj = "";
			// obj = moblieLogin( nuistLoginModel, request, response );
			// CookieTool.addCookie( response, CookieTool.COOKIE_API_OBJ, obj,
			// 60 * 15 );

			// 添加用户登陆记录
			NuistLoginHistoryModel nuistLoginHistoryModel = new NuistLoginHistoryModel();
			nuistLoginHistoryModel.mockRepository(nuist.getNuistLoginHistoryModel().acquiredTargetRepository());
			nuistLoginHistoryModel.insertLoginHistory(accountId, remoteAddr,
					RegexUtil.explorerFilter(request.getHeader("User-Agent").toString()));

		}
		return jsonObject;
	}

	/**
	 * 用户手机API获取成绩
	 * 
	 * @param nuistLoginModel
	 * @param request
	 * @param response
	 * @return int
	 */
	public String getNuistScoreJson(String pageIndex, HttpServletRequest request, HttpServletResponse response) {

		Cookie cookieName = CookieTool.getCookieByName(request, CookieTool.COOKIE_USER_NUMBER);
		if (null == cookieName)
			return "";

		String obj = "";
		String userAccount = cookieName.getValue();
		Cookie cookieAPIOBJ = CookieTool.getCookieByName(request, CookieTool.COOKIE_API_OBJ);

		if (null != cookieAPIOBJ)
			obj = cookieAPIOBJ.getValue();
		else {
			NuistLoginModel nuistLoginModel = new NuistLoginModel();
			nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
			nuistLoginModel.setUsernumber(userAccount);
			nuistLoginModel.selectByUserNumber();

			obj = moblieLogin(nuistLoginModel, response);
		}

		JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(userAccount, NuistCardAPI.NUIST_CARD_MOBILE_SCORE,
				getNuistMobileScorePostParams(obj, pageIndex));

		return jsonObject.toString();
	}

	/**
	 * 用户手机API登陆
	 * 
	 * @param nuistLoginModel
	 * @param request
	 * @param response
	 * @return int
	 */
	public boolean moblieLogin(NuistLoginModel nuistLoginModel) {

		boolean result = false;
		JSONObject jsonObject = null;
		try {
			jsonObject = NuistCardAPI.nuistPost4JSON(nuistLoginModel.getUsernumber(),
					NuistCardAPI.NUIST_CARD_MOBILE_LOGIN, getNuistMobileLoginPostParams(nuistLoginModel));
			result = jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE);
		} catch (Exception e) {
			return false;
		}

		if (result)
			nuistLoginModel.setObj(jsonObject.getString(NuistCardAPI.REQUEST_OBJ));
		return result;
	}

	/**
	 * 用户手机API登陆
	 * 
	 * @param nuistLoginModel
	 * @param request
	 * @param response
	 * @return int
	 */
	public String moblieLogin(NuistLoginModel nuistLoginModel, HttpServletResponse response) {

		JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(nuistLoginModel.getUsernumber(),
				NuistCardAPI.NUIST_CARD_MOBILE_LOGIN, getNuistMobileLoginPostParams(nuistLoginModel));

		// 判断用户登陆状态
		if (jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE)) {

			// 添加API加密Coookies
			String obj = jsonObject.getString(NuistCardAPI.REQUEST_OBJ);
			CookieTool.addCookie(response, CookieTool.COOKIE_API_OBJ, obj, 60 * 5);
			return obj;

		}
		return "";
	}

	/**
	 * 后台自动更新用户历史消费记录
	 * 
	 */
	public void autoRecordData(AutoRecordType autoRecordType) {

		// 测试官网服务是否正常
		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.setUsernumber("20141322004");
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.selectByUserNumber();

		// 网站服务正常 请求数据
		if (NuistCardAPI.nuistPostJSON4RequesrtState(nuistLoginModel.getUsernumber(), NuistCardAPI.NUIST_CARD_LOGIN_API,
				getNuistLoginPostParams(nuistLoginModel))) {
			switch (autoRecordType) {
			case NewUser:
				// 首次更新的用户交易数据
				insertNuistTradeByList(nuistLoginModel.selectByStatisticsType(1));
				break;
			case Regular:
				// 当日更新的用户交易数据
				insertNuistTradeByList(nuistLoginModel.selectByStatisticsType(2));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 后台自动更新用户成绩记录
	 * 
	 */
	public void autoRecordScore(AutoRecordType autoRecordType) {

		// 测试官网服务是否正常
		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.setUsernumber("20142328028");
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.selectByUserNumber();

		// 网站服务正常 请求数据
		if (moblieLogin(nuistLoginModel)) {
			switch (autoRecordType) {
			case NewUser:
				// 新用户成绩更新
				insertNuistScoreByList(nuistLoginModel.selectByScoreAndEducationType(0));
				break;
			case Regular:
				// 更新老用户本学期的成绩
				insertNuistScoreByList(nuistLoginModel.selectByScoreAndEducationType(1));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 一卡通转账
	 * 
	 * @param NuistLoginModel
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public JSONObject recharge(NuistLogin nuistLogin, RechargeModel rechargeModel, HttpServletRequest request) {

		// 登陆成功进行充值
		JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(nuistLogin.getUsernumber(),
				NuistCardAPI.NUIST_CARD_RECHARGE_API, getNuistRechargePostParams(rechargeModel));
		if (jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE)) {
			// 记录用户充值
			NuistRechargeHistoryModel nuistRechargeHistoryModel = new NuistRechargeHistoryModel();
			nuistRechargeHistoryModel.mockRepository(nuist.getNuistRechargeHistoryModel().acquiredTargetRepository());

			nuistRechargeHistoryModel.insertRechargeHistory(nuistLogin.getAccountId(),
					Double.parseDouble(rechargeModel.getAmount()), request.getRemoteAddr(),
					RegexUtil.explorerFilter(request.getHeader("User-Agent").toString()));

		}
		return jsonObject;
	}

	/**
	 * 生成南信一卡通获取登陆用户信息
	 * 
	 * @param userNumber
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public NuistInfo getNuistInfoModelByUserNumber(String userNumber) {

		NuistInfoModel nuistInfoModel = new NuistInfoModel();
		nuistInfoModel.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel.setUsernumber(userNumber);
		return nuistInfoModel.selectByUserNumber();

	}

	/**
	 * 生成南信一卡通获取登陆用户信息
	 * 
	 * @param userNumber
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public NuistLogin getNuistLoginModelByUserNumber(String userNumber) {

		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.setUsernumber(userNumber);
		return nuistLoginModel.selectByPrimaryKey();

	}

	/**
	 * 生成南信一卡通用户登陆post参数
	 * 
	 * @param nuistLoginModel
	 * @return
	 */
	public List<NameValuePair> getNuistLoginPostParams(NuistLoginModel nuistLoginModel) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("SignType", "SynSno"));
		postParams.add(new BasicNameValuePair("UserAccount", nuistLoginModel.getUsernumber()));
		postParams.add(new BasicNameValuePair("Password", nuistLoginModel.getPassword()));
		postParams.add(new BasicNameValuePair("NextUrl",
				"aHR0cDovL3VjYXJkLm51aXN0LmVkdS5jbjo4MDcwL1N5bkNhcmQvTWFuYWdlL1RyYW5zZmVy"));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户手机API登陆post参数
	 * 
	 * @param nuistLoginModel
	 * @return
	 */
	public List<NameValuePair> getNuistMobileScorePostParams(String obj, String pageIndex) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("xn", ""));
		postParams.add(new BasicNameValuePair("iPlanetDirectoryPro", obj));
		postParams.add(new BasicNameValuePair("pageIndex", pageIndex));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户手机API登陆post参数
	 * 
	 * @param nuistLoginModel
	 * @return
	 */
	public List<NameValuePair> getNuistMobileLoginPostParams(NuistLoginModel nuistLoginModel) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("SignType", "SynSno"));
		postParams.add(new BasicNameValuePair("account", nuistLoginModel.getUsernumber()));
		postParams.add(new BasicNameValuePair("password", Base64.Decode(nuistLoginModel.getPassword())));
		postParams.add(new BasicNameValuePair("cardimsi", ""));
		postParams.add(new BasicNameValuePair("phone", ""));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户登陆post参数
	 * 
	 * @param nuistLoginModel
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<NameValuePair> getNuistLoginPostParams(NuistLogin nuistLogin) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("SignType", "SynSno"));
		postParams.add(new BasicNameValuePair("UserAccount", nuistLogin.getUsernumber()));
		postParams.add(new BasicNameValuePair("Password", nuistLogin.getPassword()));
		postParams.add(new BasicNameValuePair("NextUrl",
				"aHR0cDovL3VjYXJkLm51aXN0LmVkdS5jbjo4MDcwL1N5bkNhcmQvTWFuYWdlL1RyYW5zZmVy"));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户充值post参数
	 * 
	 * @param rechargeModel
	 * @return
	 */
	private List<NameValuePair> getNuistRechargePostParams(RechargeModel rechargeModel) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("FromCard", "bcard"));
		postParams.add(new BasicNameValuePair("ToCard", "card"));
		postParams.add(new BasicNameValuePair("Amount", rechargeModel.getAmount()));
		postParams.add(new BasicNameValuePair("Password", rechargeModel.getPassWord()));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户修改密码post参数
	 * 
	 * @param changePWDModel
	 * @return
	 */
	private List<NameValuePair> getNuistChangePWDPostParams(ChangePWDModel changePWDModel) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("Password", changePWDModel.getPassword()));
		postParams.add(new BasicNameValuePair("NewPassword", changePWDModel.getNewPassword()));
		postParams.add(new BasicNameValuePair("ConfirmPassword", changePWDModel.getConfirmPassword()));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户挂失post参数
	 * 
	 * @param passWord
	 * @return
	 */
	private List<NameValuePair> getNuistCardLostPostParams(String passWord) {

		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("Password", passWord));
		return postParams;
	}

	/**
	 * 生成南信一卡通用户解除挂失post参数
	 * 
	 * @param cardUnlostModel
	 * @return
	 */
	private List<NameValuePair> getNuistCardUnlostPostParams(CardUnlostModel cardUnlostModel) {

		// 用户信息认证
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("CardNo", cardUnlostModel.getCardNo()));
		postParams.add(new BasicNameValuePair("Password", cardUnlostModel.getPassword()));
		return postParams;
	}

	/**
	 * 添加新用户的信息
	 * 
	 * @param nuistLoginModel
	 * @param trs
	 * @param remoteAddr
	 * @return
	 */
	private int insertNuistInfo(NuistLoginModel nuistLoginModel, Elements trs, String remoteAddr) {

		NuistInfoModel nuistInfoModel = new NuistInfoModel();
		nuistInfoModel.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel.fillNuistInfo(trs, remoteAddr);
		nuistInfoModel.setCode(Base64.Decode(nuistLoginModel.getPassword()));

		nuistLoginModel.setAccountId(nuistInfoModel.getAccountId());
		nuistLoginModel.setStatisticsType(1);
		nuistLoginModel.setScoreType(0);
		nuistLoginModel.insert();

		addStartYear(nuistLoginModel.getUsernumber());

		NuistClassModel nuistClassModel = new NuistClassModel();
		nuistClassModel.mockRepository(nuist.getNuistClassModel().acquiredTargetRepository());
		nuistClassModel.setClassName(nuistInfoModel.getPeriod());
		nuistClassModel.selectByShopName();

		if (null == nuistClassModel.getClassId()) {
			nuistClassModel.setEducation(nuistInfoModel.getEducation());
			nuistClassModel.insert();
			nuistClassModel.selectByShopName();
		}
		nuistInfoModel.setClassId(nuistClassModel.getClassId());
		nuistInfoModel.insert();

		return nuistInfoModel.getAccountId();

	}

	/**
	 * 更新老用户的信息
	 * 
	 * @param nuistLoginModel_old
	 * @param old_PWD
	 * @param trs
	 * @return
	 */
	private int updateNuistInfo(NuistLoginModel nuistLoginModel_old, String old_PWD, Elements trs) {

		// 获取本地用户基本信息
		NuistInfoModel nuistInfoModel_old = new NuistInfoModel();
		nuistInfoModel_old.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel_old.setAccountId(nuistLoginModel_old.getAccountId());
		nuistInfoModel_old.selectByPrimaryKey();

		// 更新登陆密码
		if (!nuistLoginModel_old.getPassword().equals(old_PWD)) {
			// 更新加密密码
			nuistLoginModel_old.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
			nuistLoginModel_old.setPassword(old_PWD);
			nuistLoginModel_old.updateByPrimaryKeySelective();

			nuistInfoModel_old.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
			nuistInfoModel_old.setCode(Base64.Decode(nuistLoginModel_old.getPassword()));
			nuistInfoModel_old.updateByPrimaryKeySelective();
		}

		// 更新用户基本信息:余额&银行卡&卡状态
		if (nuistInfoModel_old.updateNuistInfo(trs)) {
			nuistInfoModel_old.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
			nuistInfoModel_old.updateByPrimaryKeySelective();
		}
		addStartYear(nuistLoginModel_old.getUsernumber());

		return nuistInfoModel_old.getAccountId();
	}

	/**
	 * 为model添加用户信息
	 * 
	 * @param usernumber
	 * @param model
	 */
	public void setUserInfoModel(String usernumber, Model model) {

		NuistInfoModel nuistInfoModel = new NuistInfoModel();
		nuistInfoModel.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel.setUsernumber(usernumber);
		nuistInfoModel = (NuistInfoModel) nuistInfoModel.selectByUserNumber();
		model.addAttribute("username", nuistInfoModel.getUsername());
		model.addAttribute("usernumber", nuistInfoModel.getUsernumber());
		model.addAttribute("balance", nuistInfoModel.getBalance());
	}

	/**
	 * 为model添加用户历史账单信息
	 * 
	 * @param usernumber
	 * @param model
	 */
	public void setUserHistoryModel(String usernumber, Model model) {

		// Elements tbs = NuistCardAPI.getNuistTable( usernumber, null );
		// NuistHistoryModel.getAmountStatistics(
		// NuistHistoryModel.getNuistHistoryList( tbs ) );
	}

	/**
	 * 修改查询密码
	 * 
	 * @param userNumber
	 * @param changePWDModel
	 * @return
	 */
	public JSONObject chargePWD(String userNumber, ChangePWDModel changePWDModel) {

		// 修改查询密码
		return NuistCardAPI.nuistPost4JSON(userNumber, NuistCardAPI.NUIST_CARD_CHANGEPWD_API,
				getNuistChangePWDPostParams(changePWDModel));
	}

	/**
	 * 一卡通挂失
	 * 
	 * @param userNumber
	 * @param Password
	 * @return
	 */
	public JSONObject cardLost(String userNumber, String Password) {

		// 挂失请求
		return NuistCardAPI.nuistPost4JSON(userNumber, NuistCardAPI.NUIST_CARD_LOST_API,
				getNuistCardLostPostParams(Password));
	}

	/**
	 * 一卡通解除挂失
	 * 
	 * @param Password
	 * @param cardUnlostModel
	 * @return
	 */
	public JSONObject cardUnlost(String userNumber, CardUnlostModel cardUnlostModel) {

		// 解除挂失请求
		return NuistCardAPI.nuistPost4JSON(userNumber, NuistCardAPI.NUIST_CARD_UNLOST_API,
				getNuistCardUnlostPostParams(cardUnlostModel));
	}

	/**
	 * 根据传入类型，获取交易记录json数据
	 * 
	 * @param userNumber
	 * @param historyType
	 * @return
	 */
	public String getHistoryJson(String userNumber, int historyType) {

		return NuistHistoryModel.getHistoryJson(userNumber, historyType);
	}

	/**
	 * 根据传入类型，更新用户参与统计状态
	 * 
	 * @param userNumber
	 * @param statisticType
	 * @return
	 */
	public void updateStatisticType(String userNumber, int statisticType) {

		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.setUsernumber(userNumber);
		nuistLoginModel.selectByUserNumber();

		switch (statisticType) {
		case 1:// 参与分享
			if (3 == nuistLoginModel.getStatisticsType())
				nuistLoginModel.setStatisticsType(2);
			else
				nuistLoginModel.setStatisticsType(statisticType);
			break;
		case 3:// 退出分享
			if (1 == nuistLoginModel.getStatisticsType()) {
				nuistLoginModel.setStatisticsType(0);
				break;
			}
			nuistLoginModel.setStatisticsType(statisticType);
			break;
		default:
			break;
		}
		nuistLoginModel.updateByPrimaryKeySelective();
	}

	/**
	 * 填充用户浏览器和设备信息
	 * 
	 * @param model
	 * @param userAgent
	 */
	public void fillExplorerAndDevice(Model model, String userAgent) {

		String exlporer = RegexUtil.explorerFilter(userAgent);
		String device = RegexUtil.deviceFilter(userAgent);
		// 填充model
		if (!StringUtils.isEmpty(exlporer))
			model.addAttribute("explorer", exlporer);
		if (!StringUtils.isEmpty(device))
			model.addAttribute("device", device);
	}

	/**
	 * 插入用户反馈数据
	 * 
	 * @param userNumber
	 * @param suggest
	 */
//	public void insertSuggest(String userNumber, NuistSuggestModel nuistSuggestModel) {
//
//		nuistSuggestModel.mockRepository(nuist.getNuistsuggestmodel().acquiredTargetRepository());
//		nuistSuggestModel.setUsernumber(userNumber);
//		nuistSuggestModel.setSuggest(suggest);
//		nuistSuggestModel.setAddtime(new Date());
//		nuistSuggestModel.insert();
//	}

	public String postSuggestContent(NuistSuggestModel nuistSuggestModel, String userNumber) {

		nuistSuggestModel.mockRepository(nuist.getNuistsuggestmodel().acquiredTargetRepository());
		nuistSuggestModel.setAddtime(new Date());

		NuistInfoModel nuistInfoModel = getNuistInfoByUserNumber(userNumber);
		Integer accountId = nuistInfoModel.getAccountId();

		if (null != accountId) {

			nuistSuggestModel.setAccountId(accountId);
			nuistSuggestModel.setUsername(nuistInfoModel.getUsername());
			nuistSuggestModel.setUsernumber(nuistInfoModel.getUsernumber());
		}
		nuistSuggestModel.insert();
		return "true";
	}

	/**
	 * 填充用户参与统计状态
	 * 
	 * @param userNumber
	 * @param model
	 */
	public void fillStatisticType(String userNumber, Model model) {

		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.setUsernumber(userNumber);
		nuistLoginModel.selectByUserNumber();
		model.addAttribute("statisticType", nuistLoginModel.getStatisticsType());
	}

	/**
	 * 填充用户参与统计结果
	 * 
	 * @param userNumber
	 * @param model
	 */
	public void fillStatisticData(String userNumber, Model model) {

		model.addAttribute("people", StatisticData.people);
		model.addAttribute("dataSize", StatisticData.dataSize);

		NuistLoginModel nuistLoginModel = new NuistLoginModel();
		nuistLoginModel.setUsernumber(userNumber);
		nuistLoginModel.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
		nuistLoginModel.selectByUserNumber();
		int statisticsType = nuistLoginModel.getStatisticsType();
		model.addAttribute("statisticType", statisticsType);
		if (statisticsType == 2 || statisticsType == 1) {
			model.addAttribute("eastAmountRank", StatisticData.eastAmountRank);
			model.addAttribute("centerAmountRank", StatisticData.centerAmountRank);
			model.addAttribute("westAmountRank", StatisticData.westAmountRank);
			model.addAttribute("eastPopularityRank", StatisticData.eastPopularityRank);
			model.addAttribute("centerPopularityRank", StatisticData.centerPopularityRank);
			model.addAttribute("westPopularityRank", StatisticData.westPopularityRank);
			model.addAttribute("eastPerConsumeRank", StatisticData.eastPerConsumeRank);
			model.addAttribute("centerPerConsumeRank", StatisticData.centerPerConsumeRank);
			model.addAttribute("westPerConsumeRank", StatisticData.westPerConsumeRank);
			model.addAttribute("eastCostMostRank", StatisticData.eastCostMostRank);
			model.addAttribute("centerCostMostRank", StatisticData.centerCostMostRank);
			model.addAttribute("westCostMostRank", StatisticData.westCostMostRank);
			// NuistTradeModel nuistTradeModel = nuist.getNuistTradeModel();
			// model.addAttribute( "myEastRank",
			// nuistTradeModel.query4MyEastRank( userNumber ) );
			// model.addAttribute( "myCenterRank",
			// nuistTradeModel.query4MyCenterRank( userNumber ) );
			// model.addAttribute( "myWestRank",
			// nuistTradeModel.query4MyWestRank( userNumber ) );
		}
	}

	/**
	 * 填充用户参与统计结果
	 * 
	 * @param userNumber
	 * @param model
	 */
	public void updateStatisticData() {

		NuistTradeModel nuistTradeModel = new NuistTradeModel();
		nuistTradeModel.mockRepository(nuist.getNuistTradeModel().acquiredTargetRepository());
		// 统计状态
		StatisticData.people = nuistTradeModel.query4People();
		StatisticData.dataSize = nuistTradeModel.query4DataSize();
		// 排行数据
		// StatisticData.eastRank = nuistTradeModel.query4EastRank();
		// StatisticData.centerRank = nuistTradeModel.query4CenterRank();
		// StatisticData.westRank = nuistTradeModel.query4WestRank();
		StatisticData.eastAmountRank = nuistTradeModel.query4EastAmountRank();
		StatisticData.eastPopularityRank = nuistTradeModel.query4EastPopularityRank();
		StatisticData.eastPerConsumeRank = nuistTradeModel.query4EastPerConsumeRank();
		StatisticData.eastCostMostRank = nuistTradeModel.query4EastCostMostRank();
		StatisticData.westAmountRank = nuistTradeModel.query4WestAmountRank();
		StatisticData.westPopularityRank = nuistTradeModel.query4WestPopularityRank();
		StatisticData.westPerConsumeRank = nuistTradeModel.query4WestPerConsumeRank();
		StatisticData.westCostMostRank = nuistTradeModel.query4WestCostMostRank();
		StatisticData.centerPerConsumeRank = nuistTradeModel.query4CenterPerConsumeRank();
		StatisticData.centerAmountRank = nuistTradeModel.query4CenterAmountRank();
		StatisticData.centerCostMostRank = nuistTradeModel.query4CenterCostMostRank();
		StatisticData.centerPopularityRank = nuistTradeModel.query4CenterPopularityRank();
	}

	/**
	 * 为登陆用户添加年级数据缓存
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	// private void addStartYear( String userNumber, int startYear ) {
	//
	// HttpUtils.httpClientsMap.get( userNumber ).setStartYear( startYear );
	// }

	/**
	 * 为登陆用户添加年级数据缓存
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	private void addStartYear(String userNumber) {

		HttpUtils.httpClientsMap.get(userNumber).setStartYear(Integer.parseInt(userNumber.substring(0, 4)));
	}

	/**
	 * 根据用户登陆信息的list更新历史交易数据
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void insertNuistTradeByList(List<NuistLogin> list) {

		while (!list.isEmpty()) {
			NuistLogin model = list.get(0);
			String userNumber = model.getUsernumber();
			if (NuistCardAPI.nuistPostJSON4RequesrtState(userNumber, NuistCardAPI.NUIST_CARD_LOGIN_API,
					getNuistLoginPostParams(model))) {
				// 获取用户基本信息(似乎激活了链接，删除后无法正常访问)
				NuistCardAPI nuistCardAPI = new NuistCardAPI();
				nuistCardAPI.getNuistCardInfo(userNumber);
				int statisticsType = model.getStatisticsType();
				switch (statisticsType) {
				case 1:
					int startYear = Integer.parseInt(userNumber.substring(0, 4));
					int index = 0;
					do {
						int year = startYear + index;
						try {
							Elements tbs = NuistCardAPI.getNuistTable(userNumber,
									"?beginTime=" + year + "/01/01&endTime=" + year + "/12/31");
							NuistTradeModel.insertNuistTradeByTables(tbs, userNumber, model.getAccountId(),
									nuist.getNuistTradeModel().acquiredTargetRepository(),
									nuist.getNuistShopModel().acquiredTargetRepository());
						} catch (Exception e) {
							continue;
						}
						index++;
					} while (index < 5);
					model.setStatisticsType(2);// 修改数据分享状态
					model.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
					model.updateByPrimaryKeySelective();
					break;
				case 2:
					try {
						Elements tbs = NuistCardAPI.getNuistTable(userNumber, NuistHistoryModel.URL_PARAMS_MONTH);
						NuistTradeModel.insertNuistTradeByTables(tbs, userNumber, model.getAccountId(),
								nuist.getNuistTradeModel().acquiredTargetRepository(),
								nuist.getNuistShopModel().acquiredTargetRepository());
					} catch (Exception e) {
						continue;
					}
					break;
				default:
					break;
				}
			}
			list.remove(0);
		}
	}

	/**
	 * 根据用户登陆信息的list更新历史交易数据
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	private void insertNuistScoreByList(List<NuistLoginModel> list) {

		while (!list.isEmpty()) {
			NuistLoginModel model = list.get(0);
			if (moblieLogin(model)) {

				int statisticsType = model.getStatisticsType();
				int i = 1;
				boolean hasScore = false;
				switch (statisticsType) {
				case 1:
					do {
						try {
							JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(model.getUsernumber(),
									NuistCardAPI.NUIST_CARD_MOBILE_SCORE,
									getNuistMobileScorePostParams(model.getObj(), String.valueOf(i)));
							hasScore = jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE);

							if (hasScore) {
								i++;
								insertScores((long) model.getAccountId(),
										jsonObject.getJSONArray(NuistCardAPI.REQUEST_OBJ));
							}

						} catch (Exception e) {
							continue;
						}

					} while (hasScore);
					model.setScoreType(1);// 修改数据分享状态
					model.mockRepository(nuist.getNuistLoginModel().acquiredTargetRepository());
					model.updateByPrimaryKeySelective();
					break;
				case 2:
					try {
						JSONObject jsonObject = NuistCardAPI.nuistPost4JSON(model.getUsernumber(),
								NuistCardAPI.NUIST_CARD_MOBILE_SCORE,
								getNuistMobileScorePostParams(model.getObj(), String.valueOf(i)));
						hasScore = jsonObject.getBoolean(NuistCardAPI.REQUEST_STATE);

						if (hasScore)
							insertScores((long) model.getAccountId(),
									jsonObject.getJSONArray(NuistCardAPI.REQUEST_OBJ));

					} catch (Exception e) {
						continue;
					}
					break;
				default:
					break;
				}
			}
			list.remove(0);
		}
	}

	/**
	 * 插入用户成绩数据
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	private void insertScores(long accountId, JSONArray array) {

		// 保存json中的成绩信息
		NuistScoreHistoryModel nuistScoreHistoryModel = new NuistScoreHistoryModel();
		nuistScoreHistoryModel.mockRepository(nuist.getNuistScoreHistoryModel().acquiredTargetRepository());
		NuistCourseModel nuistCourseModel = new NuistCourseModel();
		nuistCourseModel.mockRepository(nuist.getNuistCourseModel().acquiredTargetRepository());

		nuistScoreHistoryModel.setAccountId(accountId);
		nuistScoreHistoryModel.setTime(new Date());

		// 静态数据
		JSONObject one = array.getJSONObject(0);
		nuistScoreHistoryModel.setYear(one.getString(NuistCardAPI.SCORE_REQUEST_YEAR));
		nuistScoreHistoryModel.setTerm(one.getInt(NuistCardAPI.SCORE_REQUEST_TERM));

		for (int i = 0; i < array.length(); i++) {

			JSONObject item = array.getJSONObject(i);

			try {
				nuistScoreHistoryModel.setGrade(null);
				Double score = item.getDouble(NuistCardAPI.SCORE_REQUEST_SCORE);
				nuistScoreHistoryModel.setScore(score);
			} catch (Exception e) {
				nuistScoreHistoryModel.setScore(null);
				nuistScoreHistoryModel.setGrade(item.getString(NuistCardAPI.SCORE_REQUEST_SCORE));
			}

			nuistScoreHistoryModel.setCredit(item.getDouble(NuistCardAPI.SCORE_REQUEST_CREDIT));
			nuistScoreHistoryModel.setCourse(item.getString(NuistCardAPI.SCORE_REQUEST_COURSE));
			nuistScoreHistoryModel.insert();

			nuistCourseModel.setCourseName(nuistScoreHistoryModel.getCourse());
			nuistCourseModel.selectByShopName();
			if (null == nuistCourseModel.getCourseId())
				nuistCourseModel.insert();
		}
	}

	public String postChatContent(NuistChatModel nuistChatModel, String userNumber, String ip) {

		String result = "";
		NuistInfoModel nuistInfoModel = getNuistInfoByUserNumber(userNumber);
		Integer accountId = nuistInfoModel.getAccountId();

		if (null != accountId) {

			nuistChatModel.mockRepository(nuist.getNuistChatModel().acquiredTargetRepository());
			nuistChatModel.setAccountId(accountId);
			nuistChatModel.setUsername(nuistInfoModel.getUsername());
			nuistChatModel.setClassId(nuistInfoModel.getClassId());
			nuistChatModel.setAddIp(ip);
			nuistChatModel.setAddTime(new Date());
			nuistChatModel.insert();
			result = "true";
		}
		return result;
	}

	private NuistInfoModel getNuistInfoByUserNumber(String userNumber) {

		NuistInfoModel nuistInfoModel = new NuistInfoModel();
		nuistInfoModel.mockRepository(nuist.getNuistInfoModel().acquiredTargetRepository());
		nuistInfoModel.setUsernumber(userNumber);
		nuistInfoModel.selectByUserNumber();
		return nuistInfoModel;
	}

	public void fillChatContents(String userNumber, Model model) {

		NuistInfoModel nuistInfoModel = getNuistInfoByUserNumber(userNumber);
		NuistChatModel nuistChatModel = new NuistChatModel();
		nuistChatModel.mockRepository(nuist.getNuistChatModel().acquiredTargetRepository());
		nuistChatModel.setClassId(nuistInfoModel.getClassId());
		model.addAttribute("class", nuistInfoModel.getPeriod());
		model.addAttribute("people", nuistInfoModel.getNumberofPeopleByClassId());
		model.addAttribute("parentChatList", nuistChatModel.selectListByClassId());
		model.addAttribute("childrenChatList", nuistChatModel.selectListByClassIdAndParentId());

	}

	public void fillSuggestContents(Model model) {

		NuistSuggestModel nuistSuggestModel = new NuistSuggestModel();
		nuistSuggestModel.mockRepository(nuist.getNuistsuggestmodel().acquiredTargetRepository());
		model.addAttribute("parentSuggestList", nuistSuggestModel.selectListByPrimaryKeyLimit());
		model.addAttribute("childrenSuggestList", nuistSuggestModel.selectListByPrimaryKeyAndParentId());

	}

}
