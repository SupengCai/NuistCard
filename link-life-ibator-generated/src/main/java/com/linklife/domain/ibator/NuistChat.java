package com.linklife.domain.ibator;

import java.util.Date;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

@SqlMap( Name = "nuist_chat", Class = NuistChat.class )
public class NuistChat<T, U> extends BaseDomain<T, U> {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.chat_id
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private Integer chatId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.account_id
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private Integer accountId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.username
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private String username;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.class_id
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private Integer classId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.parent_id
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private Integer parentId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.content
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private String content;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.add_time
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private Date addTime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_chat.add_ip
	 * 
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	private String addIp;


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.chat_id
	 * 
	 * @return the value of nuist_chat.chat_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public Integer getChatId() {

		return chatId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.chat_id
	 * 
	 * @param chatId
	 *            the value for nuist_chat.chat_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setChatId( Integer chatId ) {

		this.chatId = chatId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.account_id
	 * 
	 * @return the value of nuist_chat.account_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public Integer getAccountId() {

		return accountId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.account_id
	 * 
	 * @param accountId
	 *            the value for nuist_chat.account_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setAccountId( Integer accountId ) {

		this.accountId = accountId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.username
	 * 
	 * @return the value of nuist_chat.username
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public String getUsername() {

		return username;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.username
	 * 
	 * @param username
	 *            the value for nuist_chat.username
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setUsername( String username ) {

		this.username = username;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.class_id
	 * 
	 * @return the value of nuist_chat.class_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public Integer getClassId() {

		return classId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.class_id
	 * 
	 * @param classId
	 *            the value for nuist_chat.class_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setClassId( Integer classId ) {

		this.classId = classId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.parent_id
	 * 
	 * @return the value of nuist_chat.parent_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public Integer getParentId() {

		return parentId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.parent_id
	 * 
	 * @param parentId
	 *            the value for nuist_chat.parent_id
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setParentId( Integer parentId ) {

		this.parentId = parentId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.content
	 * 
	 * @return the value of nuist_chat.content
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public String getContent() {

		return content;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.content
	 * 
	 * @param content
	 *            the value for nuist_chat.content
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setContent( String content ) {

		this.content = content;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.add_time
	 * 
	 * @return the value of nuist_chat.add_time
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public Date getAddTime() {

		return addTime;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.add_time
	 * 
	 * @param addTime
	 *            the value for nuist_chat.add_time
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setAddTime( Date addTime ) {

		this.addTime = addTime;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_chat.add_ip
	 * 
	 * @return the value of nuist_chat.add_ip
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public String getAddIp() {

		return addIp;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_chat.add_ip
	 * 
	 * @param addIp
	 *            the value for nuist_chat.add_ip
	 * @ibatorgenerated Sat Jul 25 22:12:25 CST 2015
	 */
	public void setAddIp( String addIp ) {

		this.addIp = addIp;
	}
}