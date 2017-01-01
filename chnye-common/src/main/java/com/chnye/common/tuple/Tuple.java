package com.chnye.common.tuple;

//http://grepcode.com/file/repository.cloudera.com/content/repositories/releases/com.cloudera.crunch/crunch/0.2.4/com/cloudera/crunch/Tuple.java?av=f
//http://grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-api/0.0.7/com/googlecode/qlink/api/tuple/Tuple.java?av=f

/*
* @Author: anchen
* @Date:   2016-01-17 12:39:41
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-18 00:06:49
*/

public interface Tuple {

    Object get( int index );

    int size();

    //Object[] toArray();
}