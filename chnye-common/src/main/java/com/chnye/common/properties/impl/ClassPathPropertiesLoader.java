package com.chnye.common.properties.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.chnye.common.properties.IPropertiesLoader;
import com.chnye.common.properties.PropertiesLoaderException;

public class ClassPathPropertiesLoader implements IPropertiesLoader{

	
	public static ClassPathPropertiesLoader newLoader(){
		return new ClassPathPropertiesLoader();
	}
	
	public ClassPathPropertiesLoader(){
	}
	
	@Override
	public Properties load(String location, String encoding ) throws PropertiesLoaderException {
		InputStream is = null;
		InputStreamReader isr = null;
		Properties props;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream( location );
			isr = new InputStreamReader( is, encoding );
			props = new Properties();
			props.load( isr );
		} catch( Exception e ){
			throw new PropertiesLoaderException( e );
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
