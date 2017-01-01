package com.chnye.common.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.collection.CollectionUtil;


public class TestCollectionUtil {

	public static Map<String, List<Object>> mapList;
	public static Map<String, Map<String,Object>> maps;
	public static Map<String, Set<Object>> mapSet;
	
	@Before
	public void init(){
		mapList = new HashMap<>();
		maps = new HashMap<>();
		mapSet = new HashMap<>();
		
	}
	
	private void assertCollectionUtil(){
		System.out.println( "--------assertCollectionUtil--------");
		//添加数据
		CollectionUtil.addToList(mapList, "123", "A1");
		CollectionUtil.addToList(mapList, "123", "A2");
		CollectionUtil.addToList(mapList, "123", "A3");
		System.out.println( "mapList.size:" + mapList.size() );
		List<Object> list = mapList.get("123");
		for( int i = 0; i < list.size(); i++ ){
			System.out.print( list.get(i) + "\t" );
		}

	}
	
	@Test
	public void testCollectionUtil(){
		assertCollectionUtil();
	}
	
	
	
}
