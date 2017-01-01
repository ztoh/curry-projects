package com.chnye.common.collection;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.revolsys.open/com.revolsys.open.core/2014.09.10-MTEC1/com/revolsys/util/CollectionUtil.java#CollectionUtil.getList%28java.util.Map%2Cjava.lang.Object%29

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {

	public static <V> boolean addIfNotNull( final Collection<V> collection, final V value ){
		if( value == null || collection == null ){
			return false;
		}
		return collection.add( value );
	}
	
	public static <V> void addAllIfNotNull( final Collection<V> collection, final Collection<V> values ){
		if( values != null && collection != null ){
			collection.addAll( values );
		}
	}
	
	public static <K1,V> boolean addToList( final Map<K1, List<V>> map, final K1 key, final V value ){
		final List<V> values = safeGetList(map, key);
		return values.add( value );
	}
	
	public static <K1, K2, V> void addToMap( final Map<K1, Map<K2,V>> map, final K1 key1, final K2 key2, final V value ){
		final Map<K2, V> mapValue = safeGetMap( map, key1 );
		mapValue.put( key2, value );
	}
	
	public static <K1, V> boolean addToSet( final Map<K1,Set<V>> map, final K1 key1, final V value ){
		final Set<V> setValue = safeGetSet( map, key1 );
		return setValue.add( value );
	}
	
	/** ????????????????????????????
	 * 
	 */
	public static <K,V> boolean collectionContains( final Map<K, Collection<V>> map, final K key, final V value ){
		if( null == map ){
			return false;
		}
		final Collection<V> collection = map.get( key );
		if( null == collection ){
			return false;
		}
		return collection.contains( value );
	}
	
	/** ????????????????????????????
	 * 
	 */
	public static <V> boolean removeFromCollection( final Map<Object, Collection<V>> map, final Object key, final V value ){
		if( null == map ){
			return false;
		}
		final Collection<V> collection = map.get( key );
		if( null == collection ){
			return false;
		}
		boolean removed = collection.remove( value );
		if( collection.isEmpty() ){
			map.remove( key );
		}
		return removed;
	}
	
	
	private static <K,V> List<V> safeGetList( final Map<K, List<V>> map, final K key ){
		List<V> listValue = map.get( key );
		if( listValue == null ){
			listValue = new ArrayList<V>();
			map.put( key, listValue );
		}
		return listValue;
	}
	
	private static <K1, K2,V> Map<K2,V> safeGetMap( final Map<K1, Map<K2,V>> map, final K1 key ){
		Map<K2,V> mapValue = map.get( key );
		if( mapValue == null ){
			mapValue = new HashMap<K2,V>();
			map.put( key, mapValue );
		}
		return mapValue;
	}
	
	private static <K,V> Set<V> safeGetSet( final Map<K, Set<V>> map, final K key ){
		Set<V> setValue = map.get( key );
		if( setValue == null ){
			setValue = new HashSet<V>();
			map.put( key, setValue );
		}
		return setValue;
	}
}
