package com.chnye.test.guava;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class TestFiles {

	
	private void assertFiles(){
		System.out.println( "----------Files----------" );
		 String content = null;
		 try {
			content = Files.toString( new File( "example01.xml"), Charset.forName("GBK") );
			System.out.println( content );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFiles(){
		assertFiles();
	}
	
}
