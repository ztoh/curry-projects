package com.chnye.test.guava;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class TestMaps {

	public static Function<String,String> func = new Function<String,String>(){
		@Override
		public String apply(String input) {
			// TODO Auto-generated method stub
			return input.toUpperCase();
		}
	};
	
	private void assertDifference(){
		System.out.println( "----------Maps.difference-----" );
		
		Map<String,String> map1 = ImmutableMap.of( "a", "1" );
		Map<String,String> map2 = ImmutableMap.of( "b", "2" );
		Map<String,String> map3 = ImmutableMap.of( "a", "3" );
		MapDifference<String,String> diffMap1 = Maps.difference( map1, map2 );
		System.out.println( diffMap1 );
		MapDifference<String,String> diffMap2 = Maps.difference( map1, map3 );
		System.out.println( diffMap2 );
	}
	
	/**
	 * 返回一个活动的map
	 */
	private void assertAsMap(){
		System.out.println( "----------Maps.asMap-----" );
		
//		Set<String> keys = Sets.newHashSet("a", "b", "c" );
		Set<String> keys = ImmutableSet.of( "a", "b", "c" );
		
		System.out.println( Maps.asMap( keys, func) );
	}
	
	/**
	 * 返回一个不可变的ImmutableMap实例
	 */
	private void assertToMap(){
		System.out.println( "----------Maps.toMap-----" );
		
		List<String> keys = ImmutableList.of( "a", "b", "c", "d" );
		
		System.out.println( Maps.toMap( keys, func) );
	}
	
	/**
	 * 
	 */
	private void assertUniqueIndex(){
		System.out.println( "----------Maps.uniqueIndex-----" );
		List<String> values = Lists.newArrayList( "a", "b", "c", "d" );
		
		System.out.println( Maps.uniqueIndex(values, func) );
		
	}
	
	/** 
	 * 返回一个map映射。     
	 *  键为给定的fromMap的键
     *	值为给定的fromMap的值通过function转换后的值。
	 */
	private void assertTransformValues(){
		System.out.println( "----------Maps.transformValues-----" );
		Map<String,String> map = ImmutableMap.of( "武汉", "027", "天门", "0728");
		Function<String,String> func = new Function<String,String>(){
			@Override
			public String apply(String input) {
				// TODO Auto-generated method stub
				return "${" + input + "}";
			}
		};
		
		Map<String,String> resultMap = Maps.transformValues( map, func);
		System.out.println( resultMap );
		Assert.assertEquals("{武汉=${027}, 天门=${0728}}", resultMap.toString() );
		
	}
	
	/**
	 * 
	 */
	private void assertTransformEntries(){
		System.out.println( "----------Maps.transformEntries-----" );
		
		Map<String,String> map = ImmutableMap.of( "武汉", "027", "天门", "0728");
		
		Maps.EntryTransformer<String, String, String> transformer = new Maps.EntryTransformer<String, String, String>(){
			@Override
			public String transformEntry(String key, String value) {
				// TODO Auto-generated method stub
				return key + value;
			}
		};
		
		System.out.println( Maps.transformEntries(map, transformer ) );
	}
	
	/**
	 * 
	 */
	private void assertFilterKeys(){
		System.out.println( "----------Maps.filterKeys-----" );
		Map<String,String> map = ImmutableMap.of( "Wuhan", "027", "Tianmen", "0728", "Xiaogan", "0711" );
		Predicate<String> predicate = new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				// TODO Auto-generated method stub
				return input.contains("i");
			}
		};
		
		Map<String,String> resultMap = Maps.filterKeys( map, predicate );
		System.out.println( resultMap );
		
	}
	
	/**
	 * 
	 */
	private void assertFilterValues(){
		System.out.println( "----------Maps.filterValues-----" );
		Map<String,String> map = ImmutableMap.of( "Wuhan", "027", "Tianmen", "0728", "Xiaogan", "0711" );
		Predicate<String> predicate = new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				// TODO Auto-generated method stub
				return input.contains("2");
			}
		};
		
		Map<String,String> resultMap = Maps.filterValues( map, predicate );
		System.out.println( resultMap );
	}
	
	/**
	 * 
	 */
	private void assertFilterEntries(){
		System.out.println( "----------Maps.filterEntries-----" );
		Map<String,String> map = ImmutableMap.of( "Wuhan", "027", "Tianmen", "0728", "Xiaogan", "0711" );
		Predicate<Map.Entry<String, String>>  predicate = new Predicate<Map.Entry<String,String>>() {
			@Override
			public boolean apply(Entry<String, String> entry ) {
				// TODO Auto-generated method stub
				if( entry.getKey().contains("i") && entry.getValue().contains("2") ){
					return true;
				}
				return false;
			}
		};
		
		Map<String,String> resultMap = Maps.filterEntries( map, predicate );
		System.out.println( resultMap );
	}
	
	@Test
	public void testDifference(){
		assertDifference();
	}

	@Test
	public void testAsMap(){
		assertAsMap();
	}
	
	@Test
	public void testToMap(){
		assertToMap();
	}
	
	@Test
	public void testUniqueIndex(){
		assertUniqueIndex();
	}
	
	@Test
	public void testTransformValues(){
		assertTransformValues();
	}

	@Test
	public void testTransformEntries(){
		assertTransformEntries();
	}
	
	@Test
	public void testFilterKeys(){
		assertFilterKeys();
	}
	
	@Test
	public void testFilterValues(){
		assertFilterValues();
	}
	
	@Test
	public void testFilterEntries(){
		assertFilterEntries();
	}
	
	
}
