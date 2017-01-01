package com.chnye.yese.spring.config;



import org.springframework.core.annotation.Order;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(2)
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/** 应用上下文,除web部分
	 * 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println( "*************************SpringMvcInitializer.getRootConfigClasses" );
		// TODO Auto-generated method stub
		return new Class[]{ SpringMvcConfig.class  };
	}



	/** web上下文
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println( "*************************SpringMvcInitializer.getServletConfigClasses" );
		// TODO Auto-generated method stub
		return new Class[]{ ServletConfig.class };
	}

	/** DispatcherServlet的映射路径
	 * 
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{ "/" };
	}
	
	/** 注册过滤器
	 * 
	 */
//	@Override
//	protected Filter[] getServletFilters() {
//		// TODO Auto-generated method stub
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding( true );
//		Filter[] singleton = { characterEncodingFilter };
//		return singleton;
////		return super.getServletFilters();
//	}

}
