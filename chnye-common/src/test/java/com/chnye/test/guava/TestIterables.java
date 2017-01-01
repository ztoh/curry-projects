package com.chnye.test.guava;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TestIterables {

	
	private void assertIterables(){
		
	}
	
	/**
	 * 过滤一个集合
	 */
	private void assertFilter(){
		System.out.println( "---------filter----------" );
		List<String>  columns = Lists.newArrayList("name", "age", "title", "transdate", "transtime", "amount", "account", "status", "flag", "comments");
		List<String>  hiddenColumns = Lists.newArrayList( "transdate", "transtime", "status");
		
		Iterable<String> resultLists = Iterables.filter( columns, Predicates.not(Predicates.in(hiddenColumns)) );
		System.out.println( resultLists );
	}
	
	/**
	 * 将集合做function运算，得到结果集的集合
	 */
	private void assertTransform(){
		System.out.println( "---------transform----------" );
		List<String>  columns = Lists.newArrayList( "transdate", "transtime", "status");
		Function<String, String> colConvertVar = new Function<String, String>() {
			@Override
			public String apply(String input) {
				// TODO Auto-generated method stub
				return "${" + input + "}";
			}
		};
		
		Iterable<String> varColumns = Iterables.transform( columns, colConvertVar );
		System.out.println( varColumns );
	}
	
	/**
	 * 集合中元素是否均满足Predicate
	 */
	private void assertAny(){
		System.out.println( "---------any----------" );
		List<String>  columns = Lists.newArrayList( "transdate", "transtime", "status");
		Predicate<String> predicate = new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				// TODO Auto-generated method stub
				return input.contains("s");
			}
		};
		
		System.out.println( Iterables.any( columns, predicate) );
	}
	
	@Test
	public void testIterables(){
	
	}
	
	
	@Test
	public void testFilter(){
		assertFilter();
	}
	
	
	@Test
	public void testTransform(){
		assertTransform();
	}
	
	@Test
	public void testAny(){
		assertAny();
	}
	
}
