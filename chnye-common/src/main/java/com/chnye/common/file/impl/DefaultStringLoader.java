package com.chnye.common.file.impl;

import java.io.File;
import java.io.IOException;

import com.chnye.common.file.IStringLoader;


public class DefaultStringLoader implements IStringLoader{

	private FileSystemStringLoader fileSystemStringLoader;
	
	private ClassPathStringLoader classPathStringLoader;
	
	
	public static DefaultStringLoader newLoader(){
		return new DefaultStringLoader();
	}
	
	public DefaultStringLoader(){
		fileSystemStringLoader = FileSystemStringLoader.newLoader();
		classPathStringLoader = ClassPathStringLoader.newLoader();
	}
	
	@Override
	public String load(String location, String encoding ) throws IOException {
		if( isFileExists( location ) ){
			return fileSystemStringLoader.load( location, encoding );
		} else {
			return classPathStringLoader.load( location, encoding );
		}
	}
	
	private static boolean isFileExists( String location ){
		File file = new File( location );
		return file.exists();
	}

}
