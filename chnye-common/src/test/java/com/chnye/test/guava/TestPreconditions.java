package com.chnye.test.guava;

import org.junit.Test;

import com.google.common.base.Preconditions;

public class TestPreconditions {

	private static final String CLASS_NAME = TestPreconditions.class.getSimpleName();
	
	private void assertPrecondtions(){
		String param1 = null;
		
		//checkArgument 抛出IllegalArgumentException
//		Preconditions.checkArgument( param1 != null, "[%s] %s must not be null", CLASS_NAME, "param1" );
		
		//checkNotNull  抛出NullPointerException
//		Preconditions.checkNotNull( param1, "[%s] %s must not be null", CLASS_NAME, "param1" );
		
		//checkState   抛出IllegalStateException
		Preconditions.checkState( param1 != null, "[%s] %s must not be null", CLASS_NAME, "param1" );
		
	}
	
	@Test
	public void testPreconditions(){
		assertPrecondtions();
	}
	
}
