package com.chnye.common.utils;

//http://grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-api/0.0.7/com/googlecode/qlink/api/functor/Reducer.java?av=f

public interface Reducer<R>{

	R reduce( R a, R b );	
}