package com.chnye.common.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chnye.common.json.fastjson.FastjsonHelper;
import com.google.common.collect.Maps;

public class TestFastjson {

	private Object root = null;
	
	private void buildJson( String json ){
		root = JSON.parse( json );
	}
	
	@Before
	public void init() throws FileNotFoundException, IOException{
		
		String fileName = "bin\\com\\chnye\\common\\json\\example01.json";
		File file = new File( fileName );
		String jsonStr = IOUtils.toString( new FileInputStream( file) );
		buildJson( jsonStr );
		System.out.println( jsonStr );
		System.out.println( root );
	}
	
	private void assertFastjson(){
		System.out.println( "------------------assertFastjson---------------");
		//访问Object
		if( root instanceof Map ){
			System.out.println( "-----> JsonObject is map.");
			Map<String,Object> rootMap = (Map)root;
			System.out.println( "size:" + rootMap.size() );
			//遍历map
			for( Map.Entry<String, Object> entry : rootMap.entrySet()){
				Object value = entry.getValue();
				System.out.println( "key=" + entry.getKey() + " value=" + value );
				System.out.println( entry.getValue().getClass() );
				if( value instanceof JSONArray ){
					System.out.println( "----is json array");
				}
				if( value instanceof List ){
					System.out.println( "----is List");
				}
				
			}
		}
	}
	
	private void assertFastjsonByPath(){
		System.out.println( "------------------assertFastjsonByPath---------------");
		//采用path访问json
		String jsonPath = "avString[0]";
		String jsonPath2 = "avBar[1].barAge";
		
		Object result = FastjsonHelper.findObject( this.root, jsonPath );
		System.out.println( "result:" + result );
		
		Object result2 = FastjsonHelper.findObject( this.root, jsonPath2 );
		System.out.println( "result2:" + result2 );
		
	}
	
	private void assertFastjsonUpdate(){
		System.out.println( "------------------assertFastjsonUpdate---------------");
		Map<String,Object> tmpObj = Maps.newHashMap();
		tmpObj.put("zs", "ls");
		//更新JSON对象
		String jsonPath = "avBar[1].barAge[1]";
		FastjsonHelper.set( this.root, jsonPath, tmpObj );
		System.out.println( "updated :" + this.root );
	}
	
	@Test
	public void testFastjson(){
		assertFastjson();
		
		assertFastjsonByPath();
		
		assertFastjsonUpdate();
	}
	
//	
//	//------------------------遍历功能函数-------------------------
//	private Object findObject( String path ){
//		List<String> paths = split2List( path );
//		
//		Object target = this.root;
//		
//		for( final String each : paths ){
//			if( isPathList( each ) ){
//				target = findObjectInList( target, each );
//			} else {
//				target = findObjectInMap( target, each );
//			}
//		}
//		return target;
//	}
//	
//	private Object findObjectInMap( Object obj, String each ){
//		boolean isMap =  obj instanceof Map;
//		if( !isMap ){
//			throw new IllegalArgumentException("yyyy");
//		}
//		Object result = ((Map<String,Object>)obj).get( each );
//		if( result == null ){
//			throw new IllegalArgumentException("yyyy2");
//		}
//		return result;
//	}
//	
//	private Object findObjectInList( Object obj, String each ){
//		boolean isList = obj instanceof List;
//		if( !isList ){
//			throw new IllegalArgumentException("xxxx");
//		}
//		String index = each.replace("[", "").replace("]", "");
//		if( !StringUtils.isNumeric( index ) ){
//			throw new IllegalArgumentException("xxxx2");
//		}
//		return  ((List<Object>)obj).get( Integer.valueOf(index) );
//	}
//	
//	private List<String> split2List( String path ){
//		String tmpPath = path.replace("[", ".[");
//		return Arrays.asList( StringUtils.split( tmpPath, '.') );
//	}
//	
//	private boolean isPathList( String path ){
//		return path.contains("[") && path.contains("]" );
//	}
}
