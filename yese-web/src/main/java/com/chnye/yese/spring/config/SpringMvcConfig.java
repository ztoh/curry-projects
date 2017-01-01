package com.chnye.yese.spring.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.chnye.yese.spring.viewresolver.JsonViewResolver;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.chnye","com.ext"})
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	/** 资源访问处理器
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println( "SpringMvcConfig.addResourceHandlers" );
		// TODO Auto-generated method stub
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
		super.addResourceHandlers(registry);
		
	}

	 /** 添加拦截器
	  * 
	  */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
	
	/**
	 * 
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
		super.configureDefaultServletHandling(configurer);
	}

	

	/** <mvc:argument-resolvers>
	 * 
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		super.addArgumentResolvers(argumentResolvers);
	}

	
	/** 防止乱码  <mvc:message-converters>
	 * 
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		System.out.println( "*************************SpringMvcConfig.configureMessageConverters" );
		// TODO Auto-generated method stub
		converters.add( strHttpMessageConverter() );
		converters.add( jacksonMessageConverter() );
		super.configureMessageConverters(converters);
	}

	@Bean 
	StringHttpMessageConverter strHttpMessageConverter(){
		/* 避免中文乱码  */
		StringHttpMessageConverter converter = new StringHttpMessageConverter( Charset.forName("UTF-8") );
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(  new MediaType("text", "plain",  Charset.forName("UTF-8") ) );
		mediaTypes.add(  new MediaType("*", "*", Charset.forName("UTF-8") ) );
		converter.setSupportedMediaTypes( mediaTypes );
		return converter;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		/* 避免IE出现下载JSON文件的情况 */
		mediaTypes.add(  MediaType.valueOf("text/html;charset=UTF-8") );
		mediaTypes.add(  MediaType.valueOf("application/json;charset=UTF-8") );
		converter.setSupportedMediaTypes(mediaTypes);
		
		/* 定制json的解析  */
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setDateFormat( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") );
		objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES );
		objMapper.setSerializationInclusion( Include.NON_NULL );
		converter.setObjectMapper( objMapper );
		return converter;
	}
	

	/**  内容协商管理器
	 * 
	 * @param configurer
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		System.out.println( "*************************SpringMvcConfig.configureContentNegotiation" );
		// TODO Auto-generated method stub
		/* 扩展名 到 mimeType的映射,
		 *  即 user.json   =>  application/json 
		 */
		configurer.favorPathExtension( true );
		/* 
		 * 用于开启 /users/123?formate=json 的支持
		 */
		configurer.favorParameter( true );
		configurer.parameterName("format");
		/*
		 * 是否忽略Accept Header
		 */
		configurer.ignoreAcceptHeader(false);
		/*
		 * 没有扩展名时，默认的content type
		 */
		configurer.defaultContentType(MediaType.TEXT_HTML);
//		configurer.defaultContentType(MediaType.TEXT_XML );
		
		/*
		 * 扩展名 到 MIME的映射
		 */
		Map<String, MediaType> mediaTypes = new HashMap<String,MediaType>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON );
		mediaTypes.put("xml", MediaType.APPLICATION_XML );
		mediaTypes.put("html", MediaType.TEXT_HTML );
		configurer.mediaTypes(mediaTypes);
		super.configureContentNegotiation(configurer);
	}
	
	/** 内容协商视图解析器
	 * 
	 * @param manager
	 * @return
	 */
	 @Bean
	 public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	     ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	     resolver.setContentNegotiationManager(manager);
	     
	     // Define all possible view resolvers
	     List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
	 
//	     resolvers.add(jaxb2MarshallingXmlViewResolver());
	     resolvers.add(jsonViewResolver());
	     resolvers.add(defaultViewResolver());
//	     resolvers.add(pdfViewResolver());
//	     resolvers.add(excelViewResolver());
	         
	     resolver.setViewResolvers(resolvers);
	     
	     /*
	      * 当请求中没有扩展名时,的解析器
	      */
	     List<View> defaultViews = new ArrayList<View>();
	     defaultViews.add( jacksonView() );
//	     defaultViews.add( marshallingView() );
	     resolver.setDefaultViews(defaultViews);
	     return resolver;
	 }
	
//	 @Bean
//	 public ViewResolver jaxb2MarshallingXmlViewResolver() {
//	     Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//	     marshaller.setClassesToBeBound(Pizza.class);
//	     return new Jaxb2MarshallingXmlViewResolver(marshaller);
//	 }
	 
	 @Bean
	 public ViewResolver jsonViewResolver() {
		 return new JsonViewResolver();
	 }
	 
//	 @Bean
//	 public ViewResolver pdfViewResolver() {
//	     return new PdfViewResolver();
//	 }
	 
	 @Bean
	 public InternalResourceViewResolver defaultViewResolver() {
	     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setViewClass( JstlView.class );
	     viewResolver.setContentType( "text/html" );
	     viewResolver.setPrefix("/WEB-INF/pages/");
	     viewResolver.setSuffix(".jsp");
	     return viewResolver;
	 }

	 @Bean 
	 MappingJackson2JsonView jacksonView(){
		 MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
		 mappingJackson2JsonView.setPrefixJson( false );
		 return mappingJackson2JsonView;
	 }
	 
	 /** XML
	  * 
	  * @return
	  */
//	 @Bean
//	 MarshallingView marshallingView(){
//		 MarshallingView marshallingView = new MarshallingView( jaxb2Marshaller() );
//		 return marshallingView;
//	 }
	 
//	 @Bean 
//	 Jaxb2Marshaller jaxb2Marshaller(){
//		 Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//		 jaxb2Marshaller.setClassesToBeBound( myclass );
//		 return jaxb2Marshaller;
//	 }
	 
	 @Bean
	 public MultipartResolver multipartResolver() {
	     return new StandardServletMultipartResolver();
	 }
}

























