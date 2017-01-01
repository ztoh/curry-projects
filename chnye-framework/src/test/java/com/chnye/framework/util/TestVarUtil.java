package com.chnye.framework.util;

import java.util.regex.Matcher;

import org.junit.Test;

import com.chnye.framework.utils.VarUtil;

public class TestVarUtil {

	
	private void assertVarUtil(){
		System.out.println( "----------assertVarUtil----------");
		String param = "test ${name}=0 and %${age}!=null end";
		Matcher matcher = VarUtil.VAR_PATTERN.matcher( param );
		while( matcher.find() ){
			System.out.println( "------>");
			System.out.println( "count:" + matcher.groupCount() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
			System.out.println( "group2:" + matcher.group(2) );
			System.out.println( " start:" + matcher.start() + " end:" + matcher.end() );
		}
	}
	
	private void assertVarUtil2(){
		System.out.println( "----------assertVarUtil2----------");
		String param = "test $[name:user.getName()]a233 ";
		Matcher matcher = VarUtil.EXPRESSION_PATTERN.matcher( param );
		while ( matcher.find() ){
			System.out.println( "------>");
			System.out.println( "count:" + matcher.groupCount() );
			System.out.println( "group0:" + matcher.group(0) );
			System.out.println( "group1:" + matcher.group(1) );
//			System.out.println( "group2:" + matcher.group(2) );
			System.out.println( " start:" + matcher.start() + " end:" + matcher.end() );
			
			
			Matcher matcher2 = VarUtil.PREFIX_EXPRESSION_PATTERN.matcher( matcher.group(1) );
			while( matcher2.find() ){
				System.out.println( "\t------>");
				System.out.println( "\tcount:" + matcher2.groupCount() );
				System.out.println( "\tgroup0:" + matcher2.group(0) );
				System.out.println( "\tgroup1:" + matcher2.group(1) );
				System.out.println( "\tgroup2:" + matcher2.group(2) );
				System.out.println( "\tstart:" + matcher2.start() + " end:" + matcher2.end() );
			}
		}
	}
	
	@Test
	public void testVarUtil(){
		assertVarUtil();
		
		assertVarUtil2();
		
	}
	
}
