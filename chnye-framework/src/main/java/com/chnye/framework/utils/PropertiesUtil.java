package com.chnye.framework.utils;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.dandelion/dandelion-core/1.1.0/com/github/dandelion/core/util/PropertiesUtils.java?av=f

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.Properties;

public class PropertiesUtil {

	public static Properties loadFromFileSystem( String filePath, String charset ) throws IOException{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		Properties props = null;
		try{
			fis = new FileInputStream( filePath );
			isr = new InputStreamReader( fis, charset );
			props = new Properties();
			props.load( isr );
		} finally {
			try{
				if( fis != null ){ fis.close();		}
			} catch ( IOException e){}
			try{
				if( isr != null ){ isr.close();		}
			} catch ( IOException e){}
		}
		return props;
	}
	
	public static Properties loadFromClasspath( String filePath, String encoding ) throws IOException{
		InputStream is = null;
		InputStreamReader isr = null;
		Properties props;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream( filePath );
			isr = new InputStreamReader( is, encoding );
			props = new Properties();
			props.load( isr );
		} finally {
			try{
				if( is != null ){ is.close();		}
			} catch ( IOException e){}
			try{
				if( isr != null ){ isr.close();		}
			} catch ( IOException e){}
		}
		return props;
	}
	
}
