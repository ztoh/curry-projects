package com.chnye.common.file.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import com.chnye.common.file.IStringLoader;

public class ClassPathStringLoader implements IStringLoader{

	public static ClassPathStringLoader newLoader(){
		return new ClassPathStringLoader();
	}
	
	public ClassPathStringLoader(){
	}
	
	@Override
	public String load(String location, String encoding) throws IOException {
		InputStream is = null;
		InputStreamReader isr = null;
		String str = null;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream( location );
			isr = new InputStreamReader( is, encoding );
			str = IOUtils.toString( isr );
		} finally {
			try{
				if( is != null ){ is.close();		}
			} catch ( IOException e){}
			try{
				if( isr != null ){ isr.close();		}
			} catch ( IOException e){}
		}
		return str;
	}

}
