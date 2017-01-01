package com.chnye.yese.servletcontext;

import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chnye.biz.database.ISqlService;
import com.chnye.biz.database.impl.SimpleSqlServiceImpl;
import com.chnye.biz.entity.DbSqlBean;
import com.chnye.common.eventbus.MapSetsEventManager;
import com.chnye.common.eventbus.MethodObjectListener;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;





public class StartupServlet extends HttpServlet{

	private static final Logger logger = LoggerFactory.getLogger( StartupServlet.class );
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		logger.debug("=================Yese Project StartupServlet destroy================");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		logger.debug("=================Yese Project StartupServlet Init begin================");
		
		logger.debug("=====>> begin show properties: " + AppConstants.APP_PROPERTY_CONFIG );
		IPropertiesHolder holder = PropertiesManager.getInstance().getProperties( AppConstants.APP_PROPERTY_CONFIG );
		Properties props = (Properties)holder.getProperties();
		for( Map.Entry<Object, Object> entry : props.entrySet() ){
			Object key = entry.getKey();
			Object value = entry.getValue();
			logger.debug( key + " = " + value );
		}
		logger.debug("=====>> end show properties: " + AppConstants.APP_PROPERTY_CONFIG );
		
		
		logger.debug("将SQL执行服务注册到事件总线" );
		ISqlService sqlService = new SimpleSqlServiceImpl();
		MethodObjectListener listener = null;
		try {
			listener = new MethodObjectListener( sqlService, ISqlService.class.getMethod("executeSQL", DbSqlBean.class ) );
			MapSetsEventManager.getInstance().addListener(DbSqlBean.class, listener);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		logger.debug("=================Yese Project StartupServlet Init end================");
	}


	
	
}