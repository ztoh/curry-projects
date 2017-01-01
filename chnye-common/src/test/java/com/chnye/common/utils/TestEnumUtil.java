package com.chnye.common.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.chnye.common.function.Functions;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;


public class TestEnumUtil {

	public  enum EState{
		INITIAL,
		STARTED,
		RUNNING,
		STOPPED,
		COMPLETE,
		DESTORY;
	}
	
	/**
	 * 过滤掉不想要的几个枚举值
	 */
	private void assertExcludes(){
		List<EState> results = EnumUtil.list( EState.class, EState.DESTORY, EState.RUNNING );
		if( results != null ){
			System.out.println( "results size:" + results.size() );
			System.out.println( results );
		}
		
		//使用Iterabls实现过滤
		Iterable<EState> resultIterable = Iterables.filter( Arrays.asList(EState.values() ),
				Predicates.not(Predicates.in( Arrays.asList(EState.DESTORY, EState.RUNNING)))
				 );
		System.out.println( resultIterable );
		
		
	}

	@Test
	public void testExcludes(){
		assertExcludes();
	}
}
