package com.chnye.yese.spring.config;

import javax.servlet.FilterRegistration;

/**
 * Servlet3启动时会自动扫描ServletContainerInitializer的实现类
 * Spring提供了一个实现类SpringServletContainerInitializer
 * 该类中Spring自动扫描WebApplicationInitializer接口的所有实现类
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;

@Order(1)
public class WebXmlInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container ) throws ServletException {
		System.out.println( "*************************WebXmlInitializer.onStartup" );
		// TODO Auto-generated method stub
		
		
		//log4j
//		container.setInitParameter("log4jConfigLocation", "classpath:config/log4j.properties" );
//		container.addListener( Log4jConfigListener.class );
		
		//slf4j
		container.setInitParameter("logbackConfigLocation", "classpath:logback.xml" );
		container.addListener( LogbackConfigListener.class );
				
		//XX Servlet 
//		XServlet xServlet = new XServlet();
//		ServletRegistration.Dynamic dynmaic = container.addServlet("xServlet",  xServlet );
//		dynmaic.setLoadOnStartup( 2 );
//		dynmaic.addMapping( "/x_servlet");
		
				
//		WebApplicationContext context = getRootContext( container );
		
//		container.addListener( new ContextLoaderListener( context ) );
//		ServletRegistration.Dynamic dispatcher = container.addServlet("DispatcherServlet", new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/*");
		
		FilterRegistration.Dynamic filterDynamic = container.addFilter("encodingFilter", new CharacterEncodingFilter() );
		filterDynamic.setInitParameter("encoding", "UTF-8");
		filterDynamic.setInitParameter("forceEncoding", "true");
		filterDynamic.addMappingForUrlPatterns(null, true, "/*");
	
//		DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
//		shiroFilter.setTargetFilterLifecycle( true );
//		container.addFilter("shiroFilter",  shiroFilter ).addMappingForUrlPatterns( null, false, "/*");
	}
	
//	private AnnotationConfigWebApplicationContext getRootContext( ServletContext container ) {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register( ServletConfig.class );
//        context.setServletContext( container );
//        return context;
//    }

}
