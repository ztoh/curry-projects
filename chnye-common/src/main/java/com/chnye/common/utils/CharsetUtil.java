package com.chnye.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class CharsetUtil {

	public static final Charset UTF_8 = Charset.forName( "UTF-8" );
	
	public static final Charset GBK = Charset.forName( "GBK" );
	
	public static boolean isValidEncoding( String encoding ){
		try{
			"".getBytes( encoding );
			return true;
		} catch ( UnsupportedEncodingException e ){
		}
		return false;
	}
	
	public static String getDefaultEncoding(){
		return Charset.defaultCharset().name();
	}
	
	//字符串的utf-8的长度
	public static int bytesUsingUTF( String str ){
		if( str == null ){	
			return 0;
		}
		int length = 0;
		for( int i = 0; i < str.length(); i++ ){
			int cp = str.codePointAt( i );
			if( (1 <= cp) && (cp <=127) ){
				length++;
			} else if( (128 <= cp) && (cp <= 2047) ){
				length += 2;
			} else {
				length += 3;
			}
		}
		return length;
	}
	
	
}
