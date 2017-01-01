package com.chnye.common.collection;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.thymeleaf/thymeleaf/2.1.4.RELEASE/org/thymeleaf/util/ListUtils.java?av=f


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chnye.common.predicate.Predicate;
import com.google.common.base.Preconditions;



public class ListUtil {

	public static List<?> toList( final Object target ){
		Preconditions.checkNotNull( target, "target is null");
		if( target instanceof List<?> ){
			return (List<?>)target;
		}
		if( target.getClass().isArray() ){
			return new ArrayList<Object>( Arrays.asList( (Object[])target ) );
		}
		if( target instanceof Iterable<?> ){
			final List<Object> elements = new ArrayList<Object>();
			for( final Object element : elements ){
				elements.add( element );
			}
			return elements;
		}
		throw new IllegalArgumentException("Cannot convert of class[" + target.getClass().getName() + "] to a list");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
