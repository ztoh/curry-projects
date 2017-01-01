package com.chnye.common.json.fastjson;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/util/Configuration.java
/*
 * 已知的问题：2016-12-21
 *   现象：在设置值时，如果先设置Team.name路径，然后再设置Team[1].title路径。
 *        则会造成前面的路径失效，也即前面的数据丢失了。
 *   结论：尽量少用或不用数组形式; 另外本身输入就逻辑错误，怎么可能期望程序能正确呢。
 *   
 *   
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
//import com.google.common.base.Preconditions;

public class FastjsonHelper {

	/*---------------------------构造JSON对象-------------------------*/
	
	public static Object from( String json ){
		return JSON.parse( json );
	}
	
	public static Object from(){
		return from( "{}" ); 
	}
	
	public static Object from( InputStream is ) throws IOException{
		return from( IOUtils.toString( is ) );
	}
	
	public static Object from( File file ) throws FileNotFoundException, IOException{
		return from( new FileInputStream( file ) );
	}
	
	public static Object fromFilePath( String filePath ) throws FileNotFoundException, IOException{
		return from( new FileInputStream( filePath ) );
	}
	
	public static Object from( final Map<String,Object> map ){
		return from( JSON.toJSONString( map ) );
	}
	
	public static Object from( final List<Object> list ){
		return from( JSON.toJSONString( list ) );
	}
	
	/*---------------------------友好的读取JSON对象-------------------------*/
	
	public static String getString( Object obj, final String path ){
		Object result = findObject( obj, path );
		if( null == result ){
			return null;
		}
		return String.valueOf( result ).trim();
	}
	
	public static String getString( Object obj, final String path, String defaultValue ){
		String result = getString( obj, path );
		if( null == result ){
			return defaultValue;
		}
		return result;
	}
	
	public static Boolean getBoolean( Object obj, final String path ){
		String result = getString( obj, path );
//		if( null == result ){
//			return null;
//		}
		if( "true".equalsIgnoreCase( result ) ){
			return true;
		}
		if( "false".equalsIgnoreCase( result ) ){
			return false;
		}
		return null;
	}
	
	public static Boolean getBoolean( Object obj, final String path, boolean defaultValue ){
		Boolean bResult = getBoolean( obj, path );
		if( null == bResult ){
			return defaultValue;
		}
		return bResult;
	}
	
	public static Integer getInt( Object obj, final String path ){
		String result = getString( obj, path );
		if( null == result ){
			return null;
		}
		try{
			return Integer.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}
	
	public static Integer getInt( Object obj, final String path, int defaultValue ){
		Integer iResult = getInt( obj, path );
		if( null == iResult ){
			return defaultValue;
		}
		return iResult;
	}
	
	public static Long getLong( Object obj, final String path ){
		String result = getString( obj, path );
		if( null == result ){
			return null;
		}
		try{
			return Long.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}
	
	public static Long getLong( Object obj, final String path, long defaultValue ){
		Long lResult = getLong( obj, path );
		if( null == lResult ){
			return defaultValue;
		}
		return lResult;
	}
	
	public static Double getDouble( Object obj, final String path ){
		String result = getString( obj, path );
		if( null == result ){
			return null;
		}
		try{
			return Double.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}
	
	public static Double getDouble( Object obj, final String path, double defaultValue ){
		Double lResult = getDouble( obj, path );
		if( null == lResult ){
			return defaultValue;
		}
		return lResult;
	}
	
	
	/*---------------------------读取JSON对象-------------------------*/
	
	/**
	 * 
	 * @param obj	   为fastjson的Object对象 
	 * @param path   Map型路径   A.B.C    数组型路径   A[0].B.C[1]
	 * @return
	 */
	public static Object findObject( Object obj, String path ){
		List<String> paths = split2List( path );
		
		Object target = obj;
		
		for( final String each : paths ){
			if( isPathList( each ) ){
				target = findObjectInList( target, each );
			} else {
				target = findObjectInMap( target, each );
			}
		}
		return target;
	}
	

	private static Object findObjectInMap( Object obj, String each ){
		boolean isMap =  obj instanceof Map;
		if( !isMap ){
			throw new IllegalArgumentException( String.format("当前对象不是Map类型,其类型是%s.  %s", obj.getClass().toString() , obj ) );
		}
		Object result = ((Map<String,Object>)obj).get( each );
		if( result == null ){
			throw new IllegalArgumentException(String.format("当前对象没有%s键.  %s", each, obj ) );
		}
		return result;
	}
	
	private static Object findObjectInList( Object obj, String each ){
		boolean isList = obj instanceof List;
		if( !isList ){
			throw new IllegalArgumentException( String.format("当前对象不是List类型,其类型是%s.  %s", obj.getClass().toString(), obj ) );
		}
		return  ((List<Object>)obj).get( getIndex( each ) );
	}
	
	private static int getIndex( String each ){
		String index = each.replace("[", "").replace("]", "");
		if( !StringUtils.isNumeric( index ) ){
			throw new IllegalArgumentException(String.format("索引必须为数字. %s", each ) );
		}
		return Integer.valueOf( index );
	}
	
	/**
	 * 将数组型路径进一步拆分
	 *   如A[0].B.C[1]  拆分为A.[0].B.C.[1]
	 */
	private static List<String> split2List( String path ){
		String tmpPath = path.replace("[", ".[");
		return Arrays.asList( StringUtils.split( tmpPath, '.') );
	}
	
	/**
	 * 是否为数组型路径
	 */
	private static boolean isPathList( String path ){
		return path.contains("[") && path.contains("]" );
	}
	
	/**
	 * 是否是Map性路径
	 */
	private static boolean isPathMap( String path ){
		return StringUtils.isNotBlank( path) && !isPathList( path );
	}
	
	/*---------------------------更改JSON对象-------------------------*/
	
	/**
	 * 
	 * @param path
	 * @param obj 插入的对象务必保证是简单对象List<Object> / Map<String,Object>
	 * @return
	 */
	public static void set( Object rootObj, String path, final Object obj ){
//		Object result = findObject( rootObj, path );
		Object newObject = setObjectRecursive( rootObj, split2List(path), 0, obj );
		if( newObject != null ){
			rootObj = newObject;
		}
	}
	
	/**
	 * 
	 * @param current 当前JSON对象
	 * @param paths   要设置的全路径
	 * @param index   要遍历的路径索引
	 * @param value   要设置的值
	 * @return
	 */
	private static Object setObjectRecursive( Object current, final List<String> paths, int index, final Object value ){
		boolean isLastIndex = ( index == paths.size() );
		if( isLastIndex ){
			return value;
		}
		
		//当前要遍历的某节路径
		String path = paths.get( index ).trim();
		boolean isNeedList = isPathList( path );
		if( isNeedList ){
			List<Object> lists;
			int listIndexer = getIndex( path );
			boolean isCurrentList = current instanceof List;
			if( !isCurrentList ){
				lists = expand( new ArrayList<Object>(), listIndexer + 1 );
				lists.set(listIndexer, buildObject( paths.subList(index+1, paths.size()), value ) );
				return lists;
			}
			lists = (List<Object>)current;
			lists = expand( lists, listIndexer + 1 );
			boolean hasSameIndex = (lists.get(listIndexer) != null);
			if( !hasSameIndex ){
				lists.set(listIndexer, buildObject( paths.subList(index+1, paths.size()), value ) );
				return lists;
			}
			current = lists.get(listIndexer);
			lists.set(listIndexer, setObjectRecursive( current, paths, index+1, value) );
			return lists;
		}
		
		boolean isNeedMap = isPathMap( path );
		if( isNeedMap ){
			Map<String,Object> mapping;
			boolean isCurrentMap = (current instanceof Map);
			if( !isCurrentMap ){
				mapping = new HashMap<String,Object>();
				mapping.put( path, buildObject( paths.subList(index+1, paths.size()), value) );
				return mapping;
			}
			mapping = ((Map<String,Object>)current);
			boolean hasSameKey = mapping.containsKey( path );
			if( !hasSameKey ){
				mapping.put( path, buildObject( paths.subList(index+1, paths.size()), value) );
				return mapping;
			}
			current = mapping.get( path );
			mapping.put( path, setObjectRecursive( current, paths, index+1, value) );
			return mapping;
		}
		
		throw new IllegalArgumentException( String.format("path不合法%s", path) );
	}
	
	//扩展list的size
	private static List<Object> expand( List<Object> list, int size ){
		int expandSize = size - list.size();
		while( expandSize-- > 0 ){
			list.add( null );
		}
		return list;
	}
	
	/**
	 * 构造目标数据obj路径上的节点
	 * @param paths
	 * @param obj
	 * @return
	 */
	private static Object buildObject( final List<String> paths, final Object obj ){
		if( null == paths ){
			throw new IllegalArgumentException( "paths is null" );
		}
//		Preconditions.checkNotNull( paths, "path is null" );
		if( 1 == paths.size() && StringUtils.isBlank( paths.get(0) ) ){
			return obj;
		}
		Object child = obj;
		for( int i = paths.size() - 1; i >= 0; i-- ){
			String path = paths.get(i);
			if( isPathList( path) ){
				List<Object> lists = new ArrayList<Object>( getIndex(path) + 1 );
				expand( lists, getIndex( path ) + 1 );
				lists.set( getIndex(path), child );
				child = lists;
				continue;
			}
			if( isPathMap( path) ){
				Map<String,Object> mapping = new HashMap<String,Object>();
				mapping.put( path, child );
				child = mapping;
				continue;
			}
			throw new IllegalArgumentException( String.format("path不合法%s", path) );
		}//end for 
		return child;
	}
	
	/*---------------------------其他公共函数-------------------------*/
	
	public static void checkPath( final String path ){
		if( null == path ){
			throw new IllegalArgumentException( "path is null" );
		}
//		Preconditions.checkNotNull( path, "path is null" );
		for( final String each : StringUtils.split(".") ){
			if( StringUtils.isBlank( each ) ){
				throw new IllegalArgumentException( String.format("路径不合法，路径之间不能出现空白字符串.  %s", path ) );
//				Preconditions.checkArgument( StringUtils.isNotBlank( each ), "路径不合法，路径之间不能出现空白字符串.  %s", path );
			}
		}
	}
	
	public static String toJSONString( final Object obj ){
		return JSON.toJSONString( obj );
	}
}
