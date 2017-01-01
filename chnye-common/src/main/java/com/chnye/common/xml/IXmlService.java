package com.chnye.common.xml;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.kuali.common/kuali-util/4.4.17/org/kuali/common/util/xml/service/XmlService.java#XmlService

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IXmlService {

//	T load( String xml ) throws IOException;
//	String convertToString( T t );
//	T getFirstChild( T t );
	
	<T>  T getObject( InputStream is, Class<T> type );
	
	<T>  T getObject( File file, Class<T> type );
	
	<T>  T getObject( String location, Class<T> type );
	
	<T>  T getObjectFromXml( String xml, Class<T> type );
	
	void write( File file, Object obj );
	
	void write( OutputStream os, Object obj );
	
	/**
	 * 将Pojo对象转换成xml字符串
	 * @param obj
	 * @param encoding
	 * @return
	 */
	String xml( Object obj, String encoding );
	
}
