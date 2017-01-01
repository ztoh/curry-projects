package com.chnye.common.properties.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.chnye.common.properties.IPropertiesLoader;
import com.chnye.common.properties.PropertiesLoaderException;

public class FileSystemPropertiesLoader implements IPropertiesLoader{

	
	public static FileSystemPropertiesLoader newLoader(){
		return new FileSystemPropertiesLoader();
	}
	
	public FileSystemPropertiesLoader(){
	}
	
	@Override
	public Properties load(String location, String encoding ) throws PropertiesLoaderException {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		Properties props = null;
		try{
			fis = new FileInputStream( location );
			isr = new InputStreamReader( fis, encoding );
			props = new Properties();
			props.load( isr );
		} catch( Exception e ){
			throw new PropertiesLoaderException( e );
		}finally {
			try{
				if( fis != null ){ fis.close();		}
			} catch ( IOException e){}
			try{
				if( isr != null ){ isr.close();		}
			} catch ( IOException e){}
		}
		return props;
	}

}
