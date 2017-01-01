
//http://www.grepcode.com/file/repo1.maven.org/maven2/org.kuali.common/kuali-util/4.4.17/org/kuali/common/util/SetUtils.java?av=f

package com.chnye.common.collection;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {

	/**
	 * 交集???
	 */
	public static <T> Set<T> intersection( Set<T> a, Set<T> b ){
		Set<T> result = new HashSet<T>();
		result.addAll( a );
		result.retainAll( b );
		return result;
	}
	
	/**
	 * 合集
	 */
	public static <T> Set<T> union( Set<T> a, Set<T> b ){
		Set<T> result = new HashSet<T>();
		result.addAll( a );
		result.addAll( b );
		return result;
	}
	
	/**
	 * 差集， 在a中不在b中的。
	 */
	public static <T> Set<T> difference( Set<T> a, Set<T> b ){
		Set<T> result = new HashSet<T>();
		result.addAll( a );
		result.removeAll( b );
		return result;
	}
	
}
