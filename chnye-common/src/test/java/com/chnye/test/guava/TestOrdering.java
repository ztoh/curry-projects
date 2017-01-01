package com.chnye.test.guava;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class TestOrdering {

	
	private void assertOrdering(){
		System.out.println( "----------Ordering----------" );
		List<String> names = Lists.newArrayList("name", "age", "size", "status", "comments", "flag", "title", "retcode", "retmsg");
		
		System.out.println( "原始List: " + names );
		Ordering<String> naturalOrdering = Ordering.natural();
		Ordering<Object> usingToStringOrdering = Ordering.usingToString();
		Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
		
		System.out.println( "Natural list:  " + naturalOrdering.sortedCopy( names ) );
		System.out.println( "UsingToString list:  " + usingToStringOrdering.sortedCopy( names ) );
		System.out.println( "Arbitrary list:  " + arbitraryOrdering.sortedCopy( names ) );
		
	}
	
	
	@Test
	public void testOrdering(){
		assertOrdering();
	}
	
}
