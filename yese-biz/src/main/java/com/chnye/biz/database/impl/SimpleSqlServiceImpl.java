package com.chnye.biz.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chnye.biz.ImportDataConstants;
import com.chnye.biz.database.ISqlService;
import com.chnye.biz.database.cache.DbCacheManager;
import com.chnye.biz.entity.DbConnInfo;
import com.chnye.biz.entity.DbConnection;
import com.chnye.biz.entity.DbSqlBean;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions.MapDefinition;
import com.chnye.framework.common.exception.ServiceException;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.google.common.base.Throwables;

public class SimpleSqlServiceImpl implements ISqlService{
	
	private static final Logger logger = LoggerFactory.getLogger( SimpleSqlServiceImpl.class ); 
	private static final String CLASS_NAME = SimpleSqlServiceImpl.class.getName();
	
	@Override
	public void executeSQL(DbSqlBean dbSqlBean) throws ServiceException {
		// TODO Auto-generated method stub
		
		logger.debug( CLASS_NAME + "----->executeSQL begin" );
		
		try{
			//获取规则配置文件
			IPropertiesHolder configHolder = PropertiesManager.getInstance().getProperties( ImportDataConstants.DATABASE_CONFIG_FILE );
			ListMapDefinitions listMapDefs = (ListMapDefinitions)configHolder.getProperties();
			MapDefinition mapDef = listMapDefs.getMapDefinitionByKey( dbSqlBean.getDatabase() );
		
			String database = mapDef.getKey();
			String driver = mapDef.getString( ImportDataConstants.PROPKEY_DB_DRIVER );
			String url = mapDef.getString( ImportDataConstants.PROPKEY_DB_URL );
			String username = mapDef.getString( ImportDataConstants.PROPKEY_DB_USERNAME );
			String password = mapDef.getString( ImportDataConstants.PROPKEY_DB_PASSWORD );
			DbConnInfo dbConnInfo = DbConnInfo.create(database, driver, url, username, password);

			//获取数据库连接
			DbConnection dbConnection = DbCacheManager.getInstance().getFreeConnectionByCache( dbConnInfo );
		
			Connection conn = dbConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			
			int iCount = 0;
			try{
				
				//执行判重的SQL语句
				for( String chkSql : dbSqlBean.getCheckRepeatSqls() ){
					logger.debug( CLASS_NAME + "----->chkSql:" + chkSql );
//					ps = conn.prepareStatement( chkSql );
//					rs = ps.executeQuery();
//					if( rs.next() ){
//						iCount = rs.getInt( 1 );
//					}
					if( iCount > 0 ){
						throw new ServiceException(-1, "checkrepeat[" + chkSql + "] count[" + iCount + "]" );
					}
					if( ps != null ){	ps.close();	}
					if( rs != null ){	rs.close(); }
				}
				
				
				//执行入库的SQL语句
				for( String execSql : dbSqlBean.getExecuteSqls() ){
					logger.debug( CLASS_NAME + "----->execSql:" + execSql );
//					ps = conn.prepareStatement( execSql );
//					int iResult = ps.executeUpdate();
					if( ps != null ){	ps.close();		}
				}
				
			} finally{
				dbConnection.setbFree( false, true );
			}
			
			
		} catch( Exception e ){
			Throwables.propagate( e );
		}
		
		logger.debug( CLASS_NAME + "----->executeSQL end" );
	}



}
