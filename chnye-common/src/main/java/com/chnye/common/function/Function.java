package com.chnye.common.function;

//http://grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-api/0.0.7/com/googlecode/qlink/api/functor/Function.java?av=f

public interface Function<I,O>{
	public O apply( I input );
}
