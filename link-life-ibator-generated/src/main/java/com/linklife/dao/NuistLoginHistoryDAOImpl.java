package com.linklife.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.linklife.domain.ibator.NuistLoginHistory;
import com.linklife.domain.ibator.NuistLoginHistoryExample;
import java.sql.SQLException;
import java.util.List;

public class NuistLoginHistoryDAOImpl implements NuistLoginHistoryDAO {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public NuistLoginHistoryDAOImpl( SqlMapClient sqlMapClient ) {

		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int countByExample( NuistLoginHistoryExample example ) throws SQLException {

		Integer count = ( Integer )sqlMapClient.queryForObject( "nuist_login_history.ibatorgenerated_countByExample", example );
		return count.intValue();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int deleteByExample( NuistLoginHistoryExample example ) throws SQLException {

		int rows = sqlMapClient.delete( "nuist_login_history.ibatorgenerated_deleteByExample", example );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int deleteByPrimaryKey( Integer signinId ) throws SQLException {

		NuistLoginHistory key = new NuistLoginHistory();
		key.setSigninId( signinId );
		int rows = sqlMapClient.delete( "nuist_login_history.ibatorgenerated_deleteByPrimaryKey", key );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public Integer insert( NuistLoginHistory record ) throws SQLException {

		Object newKey = sqlMapClient.insert( "nuist_login_history.ibatorgenerated_insert", record );
		return ( Integer )newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public Integer insertSelective( NuistLoginHistory record ) throws SQLException {

		Object newKey = sqlMapClient.insert( "nuist_login_history.ibatorgenerated_insertSelective", record );
		return ( Integer )newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public List selectByExample( NuistLoginHistoryExample example ) throws SQLException {

		List list = sqlMapClient.queryForList( "nuist_login_history.ibatorgenerated_selectByExample", example );
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public NuistLoginHistory selectByPrimaryKey( Integer signinId ) throws SQLException {

		NuistLoginHistory key = new NuistLoginHistory();
		key.setSigninId( signinId );
		NuistLoginHistory record = ( NuistLoginHistory )sqlMapClient.queryForObject( "nuist_login_history.ibatorgenerated_selectByPrimaryKey", key );
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int updateByExampleSelective( NuistLoginHistory record, NuistLoginHistoryExample example ) throws SQLException {

		UpdateByExampleParms parms = new UpdateByExampleParms( record, example );
		int rows = sqlMapClient.update( "nuist_login_history.ibatorgenerated_updateByExampleSelective", parms );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int updateByExample( NuistLoginHistory record, NuistLoginHistoryExample example ) throws SQLException {

		UpdateByExampleParms parms = new UpdateByExampleParms( record, example );
		int rows = sqlMapClient.update( "nuist_login_history.ibatorgenerated_updateByExample", parms );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int updateByPrimaryKeySelective( NuistLoginHistory record ) throws SQLException {

		int rows = sqlMapClient.update( "nuist_login_history.ibatorgenerated_updateByPrimaryKeySelective", record );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public int updateByPrimaryKey( NuistLoginHistory record ) throws SQLException {

		int rows = sqlMapClient.update( "nuist_login_history.ibatorgenerated_updateByPrimaryKey", record );
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	private static class UpdateByExampleParms extends NuistLoginHistoryExample {

		private Object record;


		public UpdateByExampleParms( Object record, NuistLoginHistoryExample example ) {

			super( example );
			this.record = record;
		}


		public Object getRecord() {

			return record;
		}
	}
}