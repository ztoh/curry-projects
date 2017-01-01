package com.chnye.framework.xml;


import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import com.chnye.framework.context.xml.impl.Dom4jXmlContext;
import com.chnye.framework.utils.ResourceUtil;


public class TestXml01 {

	@Before
	public void init(){
		
	}
	
	
	@Test
	public void todo() throws Exception{
//		Resource res = ResourceUtil.resLoader.getResource( "classpath:com.chnye.framework.context.xml.examples01.xml");
//		System.out.println( res.getInputStream() );
		Resource res = new ClassPathResource("com/chnye/framework/context/xml/examples01.xml");
		EncodedResource enRes = new EncodedResource( res, "UTF-8" );
		String content = FileCopyUtils.copyToString( enRes.getReader() );
		System.out.println( content );
		
	}
}
