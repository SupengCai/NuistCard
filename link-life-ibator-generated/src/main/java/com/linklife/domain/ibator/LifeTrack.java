package com.linklife.domain.ibator;

import java.util.Date;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

/**
 * <p>
 * AccountInfo.java
 * </p>
 * 
 * <pre>
 * 用户轨迹表对应实体类
 * </pre>
 * 
 * @author caisupeng
 */
@SqlMap(Name = "life_track", Class = LifeTrack.class)
public class LifeTrack<T, U>  extends BaseDomain<T, U> {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.track_id
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private Integer trackId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.email
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String email;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.longitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String longitude;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.latitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String latitude;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.do_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String doWhat;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.say_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String sayWhat;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.time
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private Date time;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.title
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String title;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.address
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String address;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.city
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String city;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column life_track.name
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	private String name;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.track_id
	 * @return  the value of life_track.track_id
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public Integer getTrackId() {
		return trackId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.track_id
	 * @param trackId  the value for life_track.track_id
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.email
	 * @return  the value of life_track.email
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.email
	 * @param email  the value for life_track.email
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.longitude
	 * @return  the value of life_track.longitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.longitude
	 * @param longitude  the value for life_track.longitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.latitude
	 * @return  the value of life_track.latitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.latitude
	 * @param latitude  the value for life_track.latitude
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.do_what
	 * @return  the value of life_track.do_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getDoWhat() {
		return doWhat;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.do_what
	 * @param doWhat  the value for life_track.do_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setDoWhat(String doWhat) {
		this.doWhat = doWhat;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.say_what
	 * @return  the value of life_track.say_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getSayWhat() {
		return sayWhat;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.say_what
	 * @param sayWhat  the value for life_track.say_what
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setSayWhat(String sayWhat) {
		this.sayWhat = sayWhat;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.time
	 * @return  the value of life_track.time
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.time
	 * @param time  the value for life_track.time
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.title
	 * @return  the value of life_track.title
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.title
	 * @param title  the value for life_track.title
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.address
	 * @return  the value of life_track.address
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.address
	 * @param address  the value for life_track.address
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.city
	 * @return  the value of life_track.city
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getCity() {
		return city;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.city
	 * @param city  the value for life_track.city
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column life_track.name
	 * @return  the value of life_track.name
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column life_track.name
	 * @param name  the value for life_track.name
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	public void setName(String name) {
		this.name = name;
	}
}