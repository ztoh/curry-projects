package com.chnye.common.xml.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.chnye.common.xml.jaxb.JaxbUtil;
import com.chnye.common.xml.jaxb.definitions.MapMapBean;

public class TestJaxb2 {

	
	private void assertJaxb() throws FileNotFoundException, IOException{
//		String location = ".\\bin\\com\\chnye\\common\\utils\\xml\\jaxb\\config_rule1.xml";
		String location = ".\\bin\\com\\chnye\\common\\utils\\xml\\jaxb\\config_rule3.xml";
		String xmlStr = IOUtils.toString( new FileInputStream( new File(location) ) );
		System.out.println( xmlStr );
		MapMapBean  entity = JaxbUtil.converyToJavaBean(xmlStr, MapMapBean.class );
		System.out.println(  entity );
		
	}
	    
	@Test
	public void testJaxb(){
		try {
			assertJaxb();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
