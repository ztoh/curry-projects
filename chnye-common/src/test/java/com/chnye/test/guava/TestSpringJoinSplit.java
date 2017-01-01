package com.chnye.test.guava;

import java.util.List;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Splitter.MapSplitter;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TestSpringJoinSplit {

	/**
	 * 拼接字符串
	 */
	private void assertJoiner(){
		System.out.println( "----------Joiner----------" );
		//最简写法
		System.out.println( Joiner.on("|").join( Arrays.asList("A1","B2","C3")) );
		
		Joiner joiner = Joiner.on("; ").skipNulls();
		System.out.println( joiner.join( "name", null, "age", "size", "title") );
		
		
	}
	
	/**
	 * trimResults       移除结果字符串的前导空白和尾部空白
	 * omitEmptyStrings  从结果中自动忽略空字符串
	 */
	private void assertSplitter(){
		System.out.println( "----------Splitter----------" );
		//最简写法
		Iterable<String> strs = Splitter.on(',').trimResults().omitEmptyStrings().split("A1,B2,C3,,D4");
		System.out.println( strs );
	}
	
	
	private void assertMapJoiner(){
		System.out.println( "----------MapJoiner----------" );
		Map<String,String> map = ImmutableMap.of(
				"kye1", "value1", 
				"key2", "value2", 
				"key3", "value3" );
		MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
		System.out.println(  mapJoiner.join( map ) );
	}
	
	/**
	 * 自定义拼接
	 */
	private void assertCustomJoiner(){
		System.out.println( "----------CustomJoiner----------" );
		Function<String, String> WRAPPER = new Function<String,String>(){
			@Override
			public String apply(String input) {
				// TODO Auto-generated method stub
				return "'" + input + "'";
			}
		};
		
		Set<String> values = ImmutableSet.of( "027", "0718", "0719", "0714" );
		String result = Joiner.on(',').join( Iterables.transform( values, WRAPPER) );
		System.out.println( result );
	}
	
	private void assertMapSplitter(){
		System.out.println( "----------MapSplitter----------" );
		String str = "IP=113.5.14.65,PORT=3463,USER=admin,PASSWORD=admin,";
		MapSplitter mapSplitter = Splitter.on(",").omitEmptyStrings().withKeyValueSeparator("=");
		Map<String, String> resultMap = mapSplitter.split( str );
		if( resultMap != null ){
			System.out.println( "map size:" + resultMap.size() );
			for( Map.Entry<String, String> entry : resultMap.entrySet() ){
				System.out.println( "key[" + entry.getKey() + "] value[" + entry.getValue() + "] ");
			}
		}
	}
	
	/**
	 * 自定义分割器
	 */
	private void assertCustomSplitter(){
		System.out.println( "----------CustomSplitter----------" );
		Function<Map<String, Object>, Long> EXTRAIDS = new Function<Map<String,Object>, Long>(){
			@Override
			public Long apply(Map<String, Object> input) {
				// TODO Auto-generated method stub
				return  ((BigDecimal)input.get("ID")).longValue();
			}
		};
		
		List<Map<String,Object>>  users = Lists.newArrayList();
		for( int i = 0; i < 5; i++ ){
			Map<String,Object>  user = Maps.newConcurrentMap();
			user.put( "ID", new BigDecimal("234326565600000" + i ) );
			user.put("name", "张三" + i );
			users.add( user );
		}
		
		List<Long> ids = Lists.transform( users, EXTRAIDS );
		if( ids != null ){
			System.out.println( "list size: " + ids.size() );
			for( Long id : ids ){
				System.out.println( "id:" + id );
			}
		}
	}
	
	
	@Test
	public void testJoiner(){
		assertJoiner();
	}
	
	@Test
	public void testMapJoiner(){
		assertMapJoiner();
	}
	
	@Test
	public void testCustomJoiner(){
		assertCustomJoiner();
	}
	
	@Test
	public void testSplitter(){
		assertSplitter();
	}
	
	@Test
	public void testMapSplitter(){
		assertMapSplitter();
	}
	
	@Test
	public void testCustomSplitter(){
		assertCustomSplitter();
	}
}
