package com.chnye.common.collection;

/**
 * import org.apache.commons.lang3.ArrayUtils;
 * 
 * 判断数据是否为空
 *   ArrayUtils.isEmpty( dataAry )
 * 判断数组是否包含某值
 *   ArrayUtils.contains(dataAry, "method=forwardAuthorityOpr")  
 * 判断两个数组是否相等
 *   ArrayUtils.isEquals(x,x);  
 * 从数组中移除某值
 *   ArrayUtils.removeElement(dataAry, str);
 * 数组排序， 倒序
 *   ArrayUtils.reverse( Object[] ary );
 * 数组合并
 *   String[] newAry = (String[])ArrayUtils.addAll( ary1, ary2 );
 * 数据复制
 *   ArrayUtils.clone( array );
 * 数组追加
 *   int[] array = {1,2};
 *   array = ArrayUtils.add( array, 3 );
 * 数组中查找某值
 *   if( ArrayUtils.indexOf( typeArray, "BB") >= 0 ){}
 *             
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class ArrayUtil {
	private ArrayUtil(){
	}
	
	public static <T> T[] create( final Class<T> clazz, final int size ){
		return (T[])Array.newInstance( clazz, size );
	} 
	
	public static <T> T[] toArray( T... args ){
		return args;
	}
	
	/** http://www.grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/5.1.1_r1/com/android/internal/util/PredicatesTest.java?av=f
	 * 
	 * @param args
	 * @return
	 */
	public static <T> List<T> newArrayList( T... args ){
		List<T> list = new ArrayList<T>();
		Collections.addAll( list, args );
		return list;
	}
	
	/**
	 * 生成一个数组，用来存放集合中已有的元素
	 */
	public static <T> T[] toArray( Collection<? extends T> list, Class<T> clazz ){
		T[] array = (T[])Array.newInstance( clazz, list.size() );
		return list.toArray( array );
	}
	
	public static <T> T[] toArray( Collection<? extends T> list, T[] empty ){
		if( list.isEmpty() ){
			return empty;
		}
		return toArray( list, (Class<T>)empty.getClass().getComponentType() );
	}
	
	public static List<Integer> toList( final int[] array ){
		final Integer[] result = new Integer[ array.length ];
		for( int i = 0; i < array.length; i++ ){
			result[i] = array[i];
		}
		return Arrays.asList( result );
	}
	
	public static List<Long> toList( final long[] array ){
		final Long[] result = new Long[ array.length ];
		for( int i = 0; i < array.length; i++ ){
			result[i] = array[i];
		}
		return Arrays.asList( result );
	}
	
	/**
	 * 参考T[]数组的类型，生成一个长度为length的空数组
	 */
	public static <T> T[] newArray( T[] original, int length ){
		T[] array = (T[])java.lang.reflect.Array.newInstance(
				original.getClass().getComponentType(), length );
		return array;
	}
	
	public static <T> T[] copy( T[] array ){
		T[] tmpArray = newArray( array, array.length );
		System.arraycopy( array, 0, tmpArray, 0, array.length );
		return tmpArray;
	}
	
	
	public static <T> Iterator<T> iterator( T[] array ){
		return new ArrayIterator<T>( array );
	}
	
	private static class ArrayIterator<T> implements Iterator<T>{
		private T[] array;
		private int i = 0;
		
		public ArrayIterator( T[] array ){
			this.array = array;
		}
		@Override
		public boolean hasNext(){
			return array.length > i;
		}
		
		@Override
		public T next(){
			return array[ i++ ];
		}
		
		@Override
		public void remove(){
		}
	}//end class
	
	
	
}
