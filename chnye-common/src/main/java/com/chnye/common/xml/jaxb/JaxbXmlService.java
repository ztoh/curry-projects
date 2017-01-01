package com.chnye.common.xml.jaxb;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.kuali.common/kuali-util/4.4.17/org/kuali/common/util/xml/jaxb/JAXBXmlService.java?av=f

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;


import com.chnye.common.file.impl.DefaultStringLoader;
import com.chnye.common.xml.IXmlService;

public class JaxbXmlService implements IXmlService{

	private static JaxbXmlService instance;
	
	public static JaxbXmlService getInstance(){
		if( instance == null ){
			synchronized( JaxbXmlService.class ){
				if( instance == null ){
					instance = new JaxbXmlService();
				}
			}
		}
		return instance;
	}
	
	
	
	@Override
	public <T> T getObject(InputStream is, Class<T> type) {
		// TODO Auto-generated method stub
		try {
			String xml = IOUtils.toString( is );
			return getObjectFromXml( xml, type );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T getObject(File file, Class<T> type) {
		// TODO Auto-generated method stub
		try {
			String xml = IOUtils.toString( new FileInputStream( file ) );
			return getObjectFromXml( xml, type );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T getObject(String location, Class<T> type) {
		// TODO Auto-generated method stub
		try {
//			String xml = IOUtils.toString( new FileInputStream( location ) );
//			String xml = PropertiesUtil.loadString( location, Charset.defaultCharset().toString() );
			String xml = DefaultStringLoader.newLoader().load(location, Charset.defaultCharset().toString() );
			return getObjectFromXml( xml, type );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T getObjectFromXml(String xml,  Class<T> type) {
		// TODO Auto-generated method stub
		return JaxbUtil.converyToJavaBean(xml, type );
	}

	@Override
	public void write(File file, Object obj) {
		// TODO Auto-generated method stub
		OutputStream os = null;
		try {
			os = FileUtils.openOutputStream(file);
			write( os, obj );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly( os );
		}
		
	}

	@Override
	public void write(OutputStream os, Object obj) {
		// TODO Auto-generated method stub
		try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false );
            marshaller.marshal(obj, os);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}

	@Override
	public String xml(Object obj, String encoding) {
		// TODO Auto-generated method stub
		return JaxbUtil.convertToXml(obj, encoding);
	}

}
