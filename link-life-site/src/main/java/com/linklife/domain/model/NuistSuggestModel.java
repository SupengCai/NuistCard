package com.linklife.domain.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistSuggest;
import com.linklife.domain.ibator.NuistSuggestExample;

/**
 * <p>
 * NuistSuggestModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户反馈表封装类
 * </pre>
 * 
 * @author caisupeng
 * 
 */
@SuppressWarnings("rawtypes")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class NuistSuggestModel extends NuistSuggest<NuistSuggest, NuistSuggestExample> {

	private String formatTime;

	/**
	 * 检索最近的20条意见反馈信息
	 * 
	 * @return List<NuistSuggestModel>
	 */
	@SuppressWarnings("unchecked")
	public List<NuistSuggestModel> selectListByPrimaryKeyLimit() {

		try {
			List<NuistSuggestModel> list = (List<NuistSuggestModel>) acquiredTargetRepository().getSqlMapClient()
					.queryForList("nuist_suggest.selectListByPrimaryKeyLimit");
			for (int i = 0; i < list.size(); i++) {
				formatModelTime(list.get(i));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 检索最近20条意见反馈信息的回复
	 * 
	 * @return List<NuistSuggestModel>
	 */
	@SuppressWarnings("unchecked")
	public List<NuistSuggestModel> selectListByPrimaryKeyAndParentId() {

		try {
			List<NuistSuggestModel> list = (List<NuistSuggestModel>) acquiredTargetRepository().getSqlMapClient()
					.queryForList("nuist_suggest.selectListByPrimaryKeyAndParentId");
			for (int i = 0; i < list.size(); i++) {
				formatModelTime(list.get(i));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	public String getFormatTime() {

		return formatTime;
	}

	public void setFormatTime(String formatTime) {

		this.formatTime = formatTime;
	}

	private void formatModelTime(NuistSuggestModel NuistSuggestModel) {

		DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		NuistSuggestModel.setFormatTime(format.format(NuistSuggestModel.getAddtime()));
	}
}
