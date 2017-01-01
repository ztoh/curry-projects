package com.chnye.common.collection;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	
	/*-----------------------MAP-------------------------------*/
	public static <K,V> Map<K, V> map(K k, V v, Object...objs ){
		final Map<K, V> result = new HashMap<>();
		result.put( k, v );
		for( int i = 0; i < objs.length; i++ ){
			result.put( (K)objs[i++], (V)objs[i++] );
		}
		return result;
	}
	
	public static <K,V> Map<V,K> inverse( Map<K,V> map ){
		HashMap<V, K> inverseMap = new HashMap<>();
		for( Map.Entry<K, V> entry : map.entrySet() ){
			inverseMap.put( entry.getValue(), entry.getKey() );
		}
		return inverseMap;
	}
	
	
	
}
