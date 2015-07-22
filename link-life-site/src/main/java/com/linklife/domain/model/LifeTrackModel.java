package com.linklife.domain.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.LifeTrack;
import com.linklife.domain.ibator.LifeTrackExample;
import com.linklife.web.base.utils.ContextUtils;

/**
 * <p>
 * LifeTrackModel.java
 * </p>
 * 
 * <pre>
 * 用户生活轨迹表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings("rawtypes")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class LifeTrackModel extends LifeTrack<LifeTrack, LifeTrackExample> {

	/** 百度LBS 密钥 */
	private String ak;
	
	/** 坐标类型 */
	private String coord_type;
	
	/** LBS云服务geotable表ID */
	private String geotable_id;
	
	public LifeTrackModel()
	{
		this.ak="BV4fuXdUHi3FQLqY44XBNaZG";
		this.coord_type="3";
		this.geotable_id="75311";
	}
	
	/**
     * 填充用户生活轨迹相关信息
     *
     * @param 
     * @return
     */
	public void fillTrackInfo() {
		if (StringUtils.isEmpty(getCity()))
			setCity("南京市");
		setEmail(ContextUtils.getCurrentUserEmail());
		setTime(new Date());
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(String coord_type) {
		this.coord_type = coord_type;
	}

	public String getGeotable_id() {
		return geotable_id;
	}

	public void setGeotable_id(String geotable_id) {
		this.geotable_id = geotable_id;
	}

}
