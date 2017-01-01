package com.chnye.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestPattern {

	Pattern encodingPattern = Pattern.compile("encoding\\s*=\\s*[\\\"|']([^\\\"|']+)[\\\"|']");
	
	Pattern encodingPattern2 = Pattern.compile("<\\?xml.*encoding=('|\")([^'\"]*)\\1.*\\?>.*", Pattern.DOTALL);
	Pattern encodingPattern3 = Pattern.compile("<\\?xml.*encoding=[\"'](.*)[\"']\\?>");
	
	Pattern escapeCommentsPattern = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
	
	Pattern extPattern = java.util.regex.Pattern.compile("\\.(\\w+)$");
	
	Pattern tokenPattern = Pattern.compile("([\\w/]+)\\[(\\d+)-(\\d+)\\]");
	
	Pattern ARRAY_PATTERN = Pattern.compile("([\\w/]+)\\[([\\w/]+)\\]");
	
	Pattern CATEGORY_PATTERN = Pattern.compile("/categories/(\\w+)/subscribers");
	
	Pattern attrburitesPattern = Pattern.compile("(\\w+?)=['\"](.*?)['\"]");
	
	//变量
	Pattern varsPattern = Pattern.compile( "\\$\\{([^\\}]+)\\}" );
	
	Pattern VAR_PATTERN = Pattern.compile("(\\$)\\{?(\\w+)\\}?");
	
	Pattern SHELL_ENV_VAR_PATTERN = Pattern.compile("\\$([A-Za-z_]{1}[A-Za-z0-9_]*)");

	Pattern WIN_ENV_VAR_PATTERN = Pattern.compile("%(.*?)%");
	
	private void assertEncodingPattern(){
		System.out.println( "----------assertEncodingPattern----------" );
		String xmlStr = "<?xml version='1.0' encoding='GBK'?><root><head></head><body></body></root>";
		
		Matcher matcher = encodingPattern.matcher( xmlStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
		}
		
		Matcher matcher2 = encodingPattern2.matcher( xmlStr );
		while( matcher2.find() ){
			System.out.println( "groupCount:" + matcher2.groupCount() );
			System.out.println( "group:" + matcher2.group() );
			System.out.println( "group0:" + matcher2.group(0) );
			System.out.println( "group1:" + matcher2.group(1) );
			System.out.println( "group2:" + matcher2.group(2) );
		}
		
		Matcher matcher3 = encodingPattern3.matcher( xmlStr );
		while( matcher3.find() ){
			System.out.println( "groupCount:" + matcher3.groupCount() );
			System.out.println( "group:" + matcher3.group() );
			System.out.println( "group0:" + matcher3.group(0) );
			System.out.println( "group1:" + matcher3.group(1) );
//			System.out.println( "group2:" + matcher3.group(2) );
		}
	}
	
	private void assertCommentsPattern(){
		System.out.println( "----------assertCommentsPattern----------" );
		String htmlStr = "<root><head></head><body><!--测试数据--><h1>站在这里</h1><br><h2>大写</h2></body></root>";
		
		Matcher matcher = escapeCommentsPattern.matcher( htmlStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
		}
	}
	
	private void assertExtPattern(){
		System.out.println( "----------assertExtPattern----------" );
		String extStr = "\\bin\\com\\chnye\\utils\\example01.json";
		
		Matcher matcher = extPattern.matcher( extStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
		}
	}
	
	private void assertTokenPattern(){
		System.out.println( "----------assertTokenPattern----------" );
		String extStr = "root/head/name[1-3]";
		
		Matcher matcher = tokenPattern.matcher( extStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
			System.out.println( "group2:" + matcher.group(2) );
			System.out.println( "group3:" + matcher.group(3) );
		}
	}
	
	private void assertArrayPattern(){
		System.out.println( "----------assertArrayPattern----------" );
		String extStr = "field01[name]";
		
		Matcher matcher = ARRAY_PATTERN.matcher( extStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
			System.out.println( "group2:" + matcher.group(2) );
//			System.out.println( "group3:" + matcher.group(3) );
		}
	}
	
	private void assertCategoryPattern(){
		System.out.println( "----------assertCategoryPattern----------" );
		String extStr = "/categories/books/subscribers";
		
		Matcher matcher = CATEGORY_PATTERN.matcher( extStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
		}
	}
	
	private void assertAttributesPattern(){
		
		System.out.println( "----------assertAttributesPattern----------" );
		String attrsStr = "name='mike' age='6' address='wuhan wjw' ";
		
		Matcher matcher = attrburitesPattern.matcher( attrsStr );
		while( matcher.find() ){
//			System.out.println( "groupCount:" + matcher.groupCount() );
//			System.out.println( "group:" + matcher.group() );
//			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
			System.out.println( "group2:" + matcher.group(2) );
		}
	}
	
	private void assertVarsPattern(){
		
		System.out.println( "----------assertVarsPattern----------" );
		String attrsStr = "if( ${name} != null && ${title} > 23 ) ";
		
		Matcher matcher = varsPattern.matcher( attrsStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
//			System.out.println( "group2:" + matcher.group(2) );
		}
		System.out.println( "------xxxx-----");
		matcher = VAR_PATTERN.matcher( attrsStr );
		while( matcher.find() ){
			System.out.println( "groupCount:" + matcher.groupCount() );
			System.out.println( "group:" + matcher.group() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
			System.out.println( "group2:" + matcher.group(2) );
		}
		
		
	}
	
	@Test
	public void testPattern(){
		assertEncodingPattern();
		
		assertCommentsPattern();
		
		assertExtPattern();
		
		assertTokenPattern();
		
		assertArrayPattern();
		
		assertCategoryPattern();
		
		assertAttributesPattern();
		
		assertVarsPattern();
	}
	
	
}
