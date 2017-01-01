package com.chnye.common.tuple;

//http://grepcode.com/file/repo1.maven.org/maven2/eu.stratosphere/stratosphere-java/0.5.2/eu/stratosphere/api/java/tuple/builder/Tuple1Builder.java?av=f

import java.util.List;
import java.util.LinkedList;

public class Tuple4Builder<T1, T2, T3, T4>{

	private List<Tuple4<T1, T2, T3, T4>> tuples = new LinkedList<Tuple4<T1, T2, T3, T4>>();

	public Tuple4Builder<T1, T2, T3, T4> add( T1 arg1, T2 arg2, T3 arg3, T4 arg4 ){
		tuples.add( new Tuple4<T1, T2, T3, T4>( arg1, arg2, arg3, arg4 ) );
		return this;
	}

	public Tuple4<T1, T2, T3, T4>[] build(){
		return tuples.toArray( new Tuple4[ tuples.size() ] );
	}
}