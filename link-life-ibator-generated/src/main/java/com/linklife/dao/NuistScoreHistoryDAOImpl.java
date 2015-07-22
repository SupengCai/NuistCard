package com.linklife.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.linklife.domain.ibator.NuistScoreHistory;
import com.linklife.domain.ibator.NuistScoreHistoryExample;
import java.sql.SQLException;
import java.util.List;

public class NuistScoreHistoryDAOImpl implements NuistScoreHistoryDAO {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public NuistScoreHistoryDAOImpl( SqlMapClient sqlMapClient ) {

		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int countByExample( NuistScoreHistoryExample example ) throws SQLException {

		Integer count = ( Integer )sqlMapClient.queryForObject( "nuist_score_history.ibatorgenerated_countByExample", example );
		return count.intValue();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int deleteByExample( NuistScoreHistoryExample example ) throws SQLException {

		int rows = sqlMapClient.delete( "nuist_score_history.ibatorgenerated_deleteByExample", example );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int deleteByPrimaryKey( Long scoreId ) throws SQLException {

		NuistScoreHistory key = new NuistScoreHistory();
		key.setScoreId( scoreId );
		int rows = sqlMapClient.delete( "nuist_score_history.ibatorgenerated_deleteByPrimaryKey", key );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public Long insert( NuistScoreHistory record ) throws SQLException {

		Object newKey = sqlMapClient.insert( "nuist_score_history.ibatorgenerated_insert", record );
		return ( Long )newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public Long insertSelective( NuistScoreHistory record ) throws SQLException {

		Object newKey = sqlMapClient.insert( "nuist_score_history.ibatorgenerated_insertSelective", record );
		return ( Long )newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public List selectByExample( NuistScoreHistoryExample example ) throws SQLException {

		List list = sqlMapClient.queryForList( "nuist_score_history.ibatorgenerated_selectByExample", example );
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public NuistScoreHistory selectByPrimaryKey( Long scoreId ) throws SQLException {

		NuistScoreHistory key = new NuistScoreHistory();
		key.setScoreId( scoreId );
		NuistScoreHistory record = ( NuistScoreHistory )sqlMapClient.queryForObject( "nuist_score_history.ibatorgenerated_selectByPrimaryKey", key );
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int updateByExampleSelective( NuistScoreHistory record, NuistScoreHistoryExample example ) throws SQLException {

		UpdateByExampleParms parms = new UpdateByExampleParms( record, example );
		int rows = sqlMapClient.update( "nuist_score_history.ibatorgenerated_updateByExampleSelective", parms );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int updateByExample( NuistScoreHistory record, NuistScoreHistoryExample example ) throws SQLException {

		UpdateByExampleParms parms = new UpdateByExampleParms( record, example );
		int rows = sqlMapClient.update( "nuist_score_history.ibatorgenerated_updateByExample", parms );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int updateByPrimaryKeySelective( NuistScoreHistory record ) throws SQLException {

		int rows = sqlMapClient.update( "nuist_score_history.ibatorgenerated_updateByPrimaryKeySelective", record );
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	public int updateByPrimaryKey( NuistScoreHistory record ) throws SQLException {

		int rows = sqlMapClient.update( "nuist_score_history.ibatorgenerated_updateByPrimaryKey", record );
		return rows;
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table nuist_score_history
	 * @ibatorgenerated  Sat Jun 27 15:52:38 CST 2015
	 */
	private static class UpdateByExampleParms extends NuistScoreHistoryExample {

		private Object record;


		public UpdateByExampleParms( Object record, NuistScoreHistoryExample example ) {

			super( example );
			this.record = record;
		}


		public Object getRecord() {

			return record;
		}
	}
}