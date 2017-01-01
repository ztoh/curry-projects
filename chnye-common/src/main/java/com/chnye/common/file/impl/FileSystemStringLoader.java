package com.chnye.common.file.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import com.chnye.common.file.IStringLoader;

public class FileSystemStringLoader implements IStringLoader{

	public static FileSystemStringLoader newLoader(){
		return new FileSystemStringLoader();
	}
	
	public FileSystemStringLoader(){
	}
	
	@Override
	public String load(String location, String encoding) throws IOException {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		String str = null;
		try{
			fis = new FileInputStream( location );
			isr = new InputStreamReader( fis, encoding );
			str = IOUtils.toString( isr );
		} finally {
			try{
				if( fis != null ){ fis.close();		}
			} catch ( IOException e){}
			try{
				if( isr != null ){ isr.close();		}
			} catch ( IOException e){}
		}
		return str;
	}

}
