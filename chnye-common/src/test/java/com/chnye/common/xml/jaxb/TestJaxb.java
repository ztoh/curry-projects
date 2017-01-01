package com.chnye.common.xml.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.chnye.common.xml.jaxb.JaxbUtil;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions;

public class TestJaxb {

	private ListMapDefinitions entities;
	
	private void assertJaxb() throws FileNotFoundException, IOException{
//		String location = ".\\bin\\com\\chnye\\common\\utils\\xml\\jaxb\\config_rule1.xml";
		String location = ".\\bin\\com\\chnye\\common\\xml\\jaxb\\config_rule2.xml";
		String xmlStr = IOUtils.toString( new FileInputStream( new File(location) ) );
		System.out.println( xmlStr );
		entities = JaxbUtil.converyToJavaBean(xmlStr, ListMapDefinitions.class );
		System.out.println(  entities );
	
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
