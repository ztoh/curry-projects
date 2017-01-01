package com.chnye.common.tuple;

//http://grepcode.com/file/repo1.maven.org/maven2/eu.stratosphere/stratosphere-java/0.5.2/eu/stratosphere/api/java/tuple/builder/Tuple1Builder.java?av=f

import java.util.List;
import java.util.LinkedList;

public class Tuple3Builder<T1, T2, T3>{

	private List<Tuple3<T1, T2, T3>> tuples = new LinkedList<Tuple3<T1, T2, T3>>();

	public Tuple3Builder<T1, T2, T3> add( T1 arg1, T2 arg2, T3 arg3 ){
		tuples.add( new Tuple3<T1, T2, T3>( arg1, arg2, arg3 ) );
		return this;
	}

	public Tuple3<T1, T2, T3>[] build(){
		return tuples.toArray( new Tuple3[ tuples.size() ] );
	}
}