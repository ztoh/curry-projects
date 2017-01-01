package com.chnye.restcli;



public class TestJavaCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String str1 = "123中国人";
		byte[] defaultBytes = str1.getBytes();
		showBytes( defaultBytes, "default" );
		
		byte[] utf8Bytes = str1.getBytes( "UTF-8" );
		showBytes( utf8Bytes, "utf8" );
		
		byte[] gbkBytes = str1.getBytes( "GBK" );
		showBytes( gbkBytes, "gbk" );
		
		
	}

	
	public static void showBytes( byte[] bytes, String encoding ){
		int len = bytes.length;
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < bytes.length; i++ ){
			sb.append(  String.format("%02X ", bytes[i])  );
		}
		System.out.println( "len=" + len + "[" + sb.toString() + "]" );
	}
}
