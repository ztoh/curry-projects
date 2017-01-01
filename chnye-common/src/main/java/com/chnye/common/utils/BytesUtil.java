package com.chnye.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class BytesUtil {
	
	public static String toDebugHexString( byte[] bytes, int length, String encoding ) throws UnsupportedEncodingException{
		if( encoding == null ){
			encoding = Charset.defaultCharset().toString();
		}
		StringBuilder sb = new StringBuilder();
		byte[] tmpBytes = new byte[16];
		for (int i = 1; i <= length; i++) {
			sb.append( String.format( "%02X ", bytes[i-1]) );
			tmpBytes[ i%16 ] = bytes[i-1];
			if( i%16 == 0 && i != 0 ){
				String tmpStr = new String( tmpBytes, encoding);
				tmpStr = tmpStr.replace( '\r', '.');
				tmpStr = tmpStr.replace( '\n', '.');
				sb.append( "; ").append( tmpStr ).append( "\r\n" );
				for( int j = 0; j <16; j++ ){
					tmpBytes[j] = 0;
				}
			}
		}
		if( length %16 != 0 ){
			//用空格补齐，最后一行
			for( int k = length%16 + 1; k <= 16; k++ ){
				sb.append( "   " );
			}
			String tmpStr = new String( tmpBytes, encoding);
			tmpStr = tmpStr.replace( '\r', '.');
			tmpStr = tmpStr.replace( '\n', '.');
			sb.append( "; ").append( tmpStr ).append( "\r\n" );
		}
		return sb.toString();
	}
}
