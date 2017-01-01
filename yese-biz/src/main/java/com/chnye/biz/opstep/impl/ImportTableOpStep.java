package com.chnye.biz.opstep.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chnye.biz.ImportDataConstants;
import com.chnye.biz.database.impl.SimpleSqlServiceImpl;
import com.chnye.biz.opstep.IOpStep;
import com.chnye.framework.common.exception.ServiceException;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.chnye.framework.context.IContext;


public class ImportTableOpStep implements IOpStep{

	private static final Logger logger = LoggerFactory.getLogger( ImportTableOpStep.class ); 
	private static final String CLASS_NAME = ImportTableOpStep.class.getName();
	
	@Override
	public int execute(IContext context) throws ServiceException {
		// TODO Auto-generated method stub
		
		logger.debug( CLASS_NAME + "----->execute begin" );
		
		IPropertiesHolder configHolder = PropertiesManager.getInstance().getProperties( ImportDataConstants.IMPORT_DATA_CONFIG_FILE );
		//获取当前要处理的规则名  
		
		String currentTableRule = configHolder.getProperty( ImportDataConstants.PROPKEY_CURRENT_TABLE_RULE_PATH );
		if( StringUtils.isBlank( currentTableRule ) ){
			logger.error( CLASS_NAME + "----->currentTableRule is null" );
			throw new ServiceException( -1, "currentTableRule is null" );
		}		
	
		//获取规则配置文件
		IPropertiesHolder configHolder2 = PropertiesManager.getInstance().getProperties( currentTableRule );
		
		//开始计算Database值
		
		//开始计算Fields值
		
		//开始构造checkrepat语句
		
		//开始执行checkrepeat语句
		
		//开始构造SQL语句
		
		//将SQL语句扔进eventbus 
		
		
		logger.debug( CLASS_NAME + "----->execute end" );
		
		return 0;
	}

}
