package com.linklife.domain.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistChat;
import com.linklife.domain.ibator.NuistChatExample;

/**
 * <p>
 * NuistTradeModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户交易记录表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistChatModel extends NuistChat<NuistChat, NuistChatExample> {

	private String formatTime;


	/**
	 * 根据班级id检索班级交流父信息
	 * 
	 * @return List<NuistChatModel>
	 */
	@SuppressWarnings( "unchecked" )
	public List<NuistChatModel> selectListByClassId() {

		try {
			List<NuistChatModel> list = ( List<NuistChatModel> )acquiredTargetRepository().getSqlMapClient().queryForList( "nuist_chat.selectListByClassId",
					this );
			for( int i = 0; i < list.size(); i++ ) {
				formatModelTime( list.get( i ) );
			}
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	/**
	 * 根据班级id和父信息id检索班级交流子信息
	 * 
	 * @return List<NuistChatModel>
	 */
	@SuppressWarnings( "unchecked" )
	public List<NuistChatModel> selectListByClassIdAndParentId() {

		try {
			List<NuistChatModel> list = ( List<NuistChatModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_chat.selectListByClassIdAndParentId", this );
			for( int i = 0; i < list.size(); i++ ) {
				formatModelTime( list.get( i ) );
			}
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	public String getFormatTime() {

		return formatTime;
	}


	public void setFormatTime( String formatTime ) {

		this.formatTime = formatTime;
	}


	private void formatModelTime( NuistChatModel nuistChatModel ) {

		DateFormat format = new java.text.SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		nuistChatModel.setFormatTime( format.format( nuistChatModel.getAddTime() ) );
	}
}
