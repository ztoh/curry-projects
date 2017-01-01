package com.chnye.common.tuple;

/*
* @Author: anchen
* @Date:   2016-01-17 14:13:05
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-18 00:13:56
*/

public class TupleUtil {

 	@SuppressWarnings("unchecked")
	public static <T extends Tuple> T createTuple( Object[] data ){
 		switch( data.length ){
 			case 2:
 				return (T) new Pair( data[0], data[1] );
 			case 3:
 				return (T) new Tuple3( data[0], data[1], data[2] );
            case 4:
                return (T) new Tuple4( data[0], data[1], data[2], data[3] );
 			default:
 				throw new IllegalArgumentException("Creating a tuple with unknown size " + data.length );
 		}
 	}

    public static <T1, T2> Pair<T1,T2> create( T1 arg1, T2 arg2 ){
        return new Pair<T1, T2>( arg1, arg2 );
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> create( T1 arg1, T2 arg2, T3 arg3 ){
        return new Tuple3<T1, T2, T3>( arg1, arg2, arg3 );
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> create( T1 arg1, T2 arg2, T3 arg3, T4 arg4 ){
        return new Tuple4<T1, T2, T3, T4>( arg1, arg2, arg3, arg4 );
    }


}