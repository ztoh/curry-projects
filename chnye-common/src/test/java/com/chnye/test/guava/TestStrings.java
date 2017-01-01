package com.chnye.test.guava;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

import com.chnye.common.utils.BytesUtil;
import com.google.common.base.Strings;

public class TestStrings {

	private static final String TEMP_XML_01 = "<?xml version='1.0 encoding='UTF-8' ?><root><head><TransCode>2001</TransCode><TransDate>20160305</TransDate><TransTime>045300</TransTime><SeqNo>23123523</SeqNo></head><body><Result>0</Result><Message>交易成功</Message></body></root>";
	private static final String TRX_SUCCESS = "交易成功";
	
	/**
	 * 测试字节数组的十六进制展示
	 * @throws UnsupportedEncodingException
	 */
	private void assertBytesHex() throws UnsupportedEncodingException{
		byte[] defaultBytes = TestStrings.TEMP_XML_01.getBytes();
		String hexStr = BytesUtil.toDebugHexString(defaultBytes, defaultBytes.length, null );
		System.out.println( "Charset.defaultCharset()=" + Charset.defaultCharset() );
		System.out.println( hexStr );
		
		
		byte[] defaultTrxBytes = TRX_SUCCESS.getBytes();
		hexStr = BytesUtil.toDebugHexString(defaultTrxBytes, defaultTrxBytes.length, null );
		System.out.println( hexStr );
		byte[] gbkBytes = TRX_SUCCESS.getBytes( "GBK" );
		hexStr = BytesUtil.toDebugHexString(gbkBytes, gbkBytes.length, null );
		System.out.println( hexStr );
	}
	
	/**
	 * 字符串填充函数测试
	 *   需求: 一般需求为将一个字符串拼接为12为长度的字符串，不足时左边填充字符C(如果是数字型一般补0，如果是文字型一般补空格)
	 *       特别之处在于： 原字符串是否带有中文和协议传输时的字符集
	 *   如果老的协议是在C上开发的，一般字符集是GBK的，这种情况下中文对应2个字节，英文对应1个字节。
	 *   这个时候影响我们补零或补空格的多少。    
	 */
	private void assertStrings(){
		System.out.println( "----------assertStrings----------" );
		String CarNo = "鄂A00112";
		//padEnd(param1, length, param2 ) 将param1格式化为length,不足时右补param2
		System.out.println( "Strings.padEnd( \"鄂A00112\", 12, '0' )=[" + Strings.padEnd( CarNo, 12, '0' ) + "]");
		System.out.println( "Strings.padEnd( \"鄂A00112\", 5, '0' )=[" + Strings.padEnd( CarNo, 5, '0' ) + "]");
		
		//padStart(param1, length, param2 )
		System.out.println( "Strings.padStart( \"鄂A00112\", 12, '0')=[" + Strings.padStart( CarNo, 12, '0') + "]" );
		
		//nullToEmpty   将null值转为空字符串
		System.out.println( "Strings.nullToEmpty(null)=[" + Strings.nullToEmpty( null )  + "]" );
		//emptyToNull   将空字符串转为null
		System.out.println( "Strings.emptyToNull(\"\")=[" + Strings.emptyToNull( "" ) + "]" );
		
		//repeat  将字符串循环拼接多次
		System.out.println( "Strings.repeat( CarNo, 3)=[" + Strings.repeat( CarNo, 3) + "]");
		
		//commonSuffix  返回两个字符串相同的后缀部分
		//commonPrefix  返回两个字符串相同的前缀部分
		System.out.println( Strings.commonSuffix("鄂A00112", "鄂BXX112") );
		System.out.println( Strings.commonPrefix("鄂A00112", "鄂A06112") );
	}
	
	/**
	 * 字符匹配
	 */
	private void assertCharMatcher(){
		
	}
	
	@Test
	public void testBytesHex(){
		try {
			assertBytesHex();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStrings(){
		assertStrings();
	}
	
	@Test
	public void testCharMatcher(){
		assertCharMatcher();
	}
	
}
