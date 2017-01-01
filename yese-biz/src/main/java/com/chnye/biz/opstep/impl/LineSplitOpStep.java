package com.chnye.biz.opstep.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chnye.biz.ImportDataConstants;
import com.chnye.biz.opstep.IOpStep;
import com.chnye.common.function.Function;
import com.chnye.common.function.Functions;
import com.chnye.framework.common.exception.ServiceException;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.chnye.framework.context.IContext;


public class LineSplitOpStep implements IOpStep{

	private static final Logger logger = LoggerFactory.getLogger( LineSplitOpStep.class ); 
	private static final String CLASS_NAME = LineSplitOpStep.class.getName();
	
	
	@Override
	public int execute(IContext context) throws ServiceException {
		// TODO Auto-generated method stub
		
		logger.debug( CLASS_NAME + "----->execute begin" );
		
		String lineContent = (String)context.getProperty( ImportDataConstants.LINE_CONTENT_KEY );
		if( StringUtils.isBlank( lineContent ) ){
			logger.error( CLASS_NAME + "----->lineContent is null" );
			throw new ServiceException(-1, "lineContent is null");
		}
		
		//获取配置参数
		logger.debug( CLASS_NAME + "----->读取配置文件:" + ImportDataConstants.IMPORT_DATA_CONFIG_FILE );
		IPropertiesHolder configHolder = PropertiesManager.getInstance().getProperties( ImportDataConstants.IMPORT_DATA_CONFIG_FILE );
		String lineSplitSeparator = configHolder.getProperty( ImportDataConstants.PROPKEY_LINE_SPLIT_SEPARATOR );
		logger.debug( CLASS_NAME + "----->读取配置文件参数:" +  ImportDataConstants.PROPKEY_LINE_SPLIT_SEPARATOR  + " [" + lineSplitSeparator + "]");
		Function<String,String[]> dotSplitFunc = Functions.split( lineSplitSeparator );
		String[] fieldDatas = dotSplitFunc.apply( lineSplitSeparator );
		logger.debug(CLASS_NAME + "----->拆分字段后个数:" + fieldDatas.length );
		
		
		String fieldsContextPath = configHolder.getProperty( ImportDataConstants.PROPKEY_FIELDS_CONTEXT_PATH );
		logger.debug( CLASS_NAME + "----->读取配置文件参数:" +  ImportDataConstants.PROPKEY_FIELDS_CONTEXT_PATH  + " [" + fieldsContextPath + "]" );
		String fieldNamePrefix = configHolder.getProperty( ImportDataConstants.PROPKEY_FIELD_NAME_PREFIX );
		logger.debug( CLASS_NAME + "----->读取配置文件参数:" +  ImportDataConstants.PROPKEY_FIELD_NAME_PREFIX  + " [" + fieldNamePrefix + "]" );
		for( int i = 0; i < fieldDatas.length; i++ ){
			String fieldContextPath = fieldsContextPath + "/" + fieldNamePrefix + i;
			logger.debug("into context: [" + fieldContextPath + "] [" + fieldDatas[i] + "] ");
			context.setValueAt( fieldContextPath,  fieldDatas[i] );
		}
		
		logger.debug( CLASS_NAME + "----->execute end" );
		
		return 0;
	}

}
