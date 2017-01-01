package com.chnye.common.function;

/**
 * 按照面向函数式编程的思想，Java中任何操作都可以归化为一个函数调用
 * 甚至任何常用的逻辑操作，也都可以归化为一个函数调用
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.chnye.common.predicate.Predicate;
import com.google.common.base.Preconditions;

//http://grepcode.com/file/repo1.maven.org/maven2/io.projectreactor/reactor-core/2.0.4.RELEASE/reactor/fn/Functions.java?av=f
//http://www.grepcode.com/file/repo1.maven.org/maven2/com.threerings/react/1.5.2/react/Functions.java?av=f
//http://www.grepcode.com/file/repo1.maven.org/maven2/com.google.guava/guava/19.0-rc1/com/google/common/base/Functions.java#Functions
//http://www.grepcode.com/file/repo1.maven.org/maven2/org.hobsoft.symmetry/symmetry-ui/0.2.0/org/hobsoft/symmetry/ui/functor/Functions.java#Functions

public class Functions {



	/*-----------------------类型: 对于值的某种运算的函数功能-------------------------------*/
	/**
	 * 这类函数的特点：
	 *    不需要根据参数来构造功能。直接在输入，输出之间操作，直接就是函数调用，类似于无状态Bean
	 *    泛型必须是实际类型，不能是通用类型，因为这个函数就new一次，此时必须是实际类型。
	 */
	
	/** 似乎没有什么卵用
	 *  但是站在，面向函数式编程的思想上考虑，用于将取反操作归化为一个函数
	 *  
	 *  如何用 
	 *    boolean bResult = Functions.NOT.apply( boolean );
	 */
	public static Function<Boolean, Boolean> NOT = new Function<Boolean, Boolean>(){
		@Override
		public Boolean apply( Boolean value ){
			Preconditions.checkNotNull( value, "value must not be null");
			return !value;
		}
	};
	
	/**
	 * 如何用
	 *   boolean bResult = Functions.IS_NULL.apply( Object );	
	 */
	public static Function<Object, Boolean> IS_NULL = new Function<Object, Boolean>(){
		@Override
		public Boolean apply(Object value ) {
			// TODO Auto-generated method stub
			return ( value == null );
		}
	};
	
	/**
	 * 如何用
	 *   boolean bResult = Functions.NON_NULL.apply( Object );
	 */
	public static Function<Object, Boolean> NON_NULL = new Function<Object, Boolean>(){
		@Override
		public Boolean apply(Object value ) {
			// TODO Auto-generated method stub
			return ( value != null );
		}
	};
	
	/*------------------------字符串相关常用操作函数化---------------------------*/
	
	/**
	 * 如何用
	 *   String str = Functions.TO_STRING.apply( Object );
	 */
	public static Function<Object, String> TO_STRING = new Function<Object, String>(){
		@Override
		public String apply(Object value ) {
			// TODO Auto-generated method stub
			Preconditions.checkNotNull( value, "value must not be null");
			return String.valueOf( value );
		}
	};
	
	/**
	 * 如何用
	 *   String str = Functions.TRIM.apply( String );
	 */
	public static Function<String,String> TRIM = new Function<String,String>(){
		@Override
		public String apply(String value) {
			// TODO Auto-generated method stub
			Preconditions.checkNotNull( value, "value is null");
			return value.trim();
		}
	};
	
	public static Function<String, String> TO_LOWER_CAST = new Function<String,String>(){
		@Override
		public String apply( String input ){
			return input.toLowerCase();
		}
	};
	
	public static Function<String, Boolean> STRING_BOOLEAN = new Function<String, Boolean>(){
		@Override
		public Boolean apply(String input) {
			return Boolean.valueOf( input );
		}
	};
	
	/**
	 * 如何用 
	 *   Integer iResult = Functions.STRING_INT.apply( String );
	 */
	public static Function<String, Integer> STRING_INT = new Function<String, Integer>(){
		@Override
		public Integer apply(String value ) {
			// TODO Auto-generated method stub
			return (value != null) ? Integer.valueOf( value ) : null;
		}
	};
	
	/**
	 * 如何用, 注意返回类型必须为Long而不是long
	 *   Long lResult = Functions.STRING_LONG.apply( String );
	 */
	public static Function<String, Long> STRING_LONG = new Function<String, Long>(){
		@Override
		public Long apply(String value ) {
			// TODO Auto-generated method stub
			return (value != null) ? Long.valueOf( value ) : null;
		}
	};
	
	
	/*------------------------数字相关常用操作函数化---------------------------*/
	
	/**
	 * 如何用
	 *   Integer iResult = Functions.INT_VALUE.apply( Long );
	 */
	public static Function<Number, Integer> INT_VALUE = new Function<Number, Integer>(){
		@Override
		public Integer apply(Number value) {
			// TODO Auto-generated method stub
			Preconditions.checkNotNull( value, "value must not be null");
			return value.intValue();
		}
	};
	
	
	/*------------------------对象和类相关常用操作函数化---------------------------*/
	
	/**
	 * 如何用
	 *   Class clazz = Functions.OBJ_CLS.apply( Object );
	 */
	public static Function<Object, Class<?>> OBJ_CLS = new Function<Object, Class<?>>(){
		@Override
		public Class<?> apply(Object value) {
			// TODO Auto-generated method stub
			Preconditions.checkNotNull( value, "value is null");
			return value.getClass();
		}
	};
	
	
	
	
	/*-----------------------类型: 对于值的某种运算的函数功能,泛型版-------------------------------*/
	
	/**
	 * 将Map的Key转换为List
	 * 
	 * 如何用
	 *   Map<Integer,String> map = Immutables.of( 1, "V1", 2, "V2" );
	 *   Function<Map<Integer,String>, List<Integer>> func = Functions.mapKeysList();
	 *   List<Integer> list = func.apply( map );
	 */
	public static <K,V> Function<Map<K, V>, List<K>> mapKeysList(){
		return new Function<Map<K, V>, List<K>>(){
			@Override
			public List<K> apply( Map<K, V> map ){
				List<K> list = new ArrayList<K>();
//				for( Map.Entry<K, V> entry : map.entrySet() ){
//					list.add( entry.getKey() );
//				}
				for( K key : map.keySet() ){
					list.add( key );
				}
				return list;
			}
		};
	}
	
	/**
	 * 将Map的Value转换为List
	 * 
	 * 如何用
	 *   同上
	 */
	public static <K,V> Function<Map<K, V>, List<V>> mapValuesList(){
		return new Function<Map<K, V>, List<V>>(){
			@Override
			public List<V> apply( Map<K, V> map ){
				List<V> list = new ArrayList<V>();
				for( Map.Entry<K, V> entry : map.entrySet() ){
					list.add( entry.getValue() );
				}
				return list;
			}
		};
	}
	
	
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/de.xwic.appkit/appkit-core/5.2.33/de/xwic/appkit/core/util/Functions.java#Functions
	//代理了某个某个函数,  有何卵用？
//	public static <I,O> Function<I,O> OF( final Function<I,O> func ){
//		return new Function<I,O>(){
//			@Override
//			public O apply(I input) {
//				// TODO Auto-generated method stub
//				return func.apply(input);
//			}
//		};
//	}
	
	/*-----------------------类型: 构造一个Function-------------------------------*/
	/**
	 * 这类函数的特点：
	 *    需要根据参数来构造一个功能。即输出物，函数。
	 */
	
	/**  http://www.grepcode.com/file/repo1.maven.org/maven2/org.neo4j/neo4j-kernel/2.2.3/org/neo4j/helpers/Functions.java#Functions
	 * 类型强转函数
		Function<Object, User> castUserFunc = Functions.CAST( User.class );
		User user = castUserFunc.apply( obj );
	 */
	public static <From,To> Function<From, To> CAST( final Class<To> to ){
		return new Function<From,To>(){
			@Override
			public To apply(From from ) {
				// TODO Auto-generated method stub
				return to.cast( from );
			}
		};
	}
	
	//
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/edu.washington.cs.knowitall/common-java/2.0.2/edu/washington/cs/knowitall/commonlib/StringFunctions.java?av=f
	 * 
	 * 如何用
	 *   String params = "col01,col02,col03";
	 *   Function<String,String[]> dotSplitFunc = Functions.split( "," );
	 *   String[] strAry = dotSplitFunc.apply( params );
	 */
	public static Function<String,String[]> split( final String regex ){
		return new Function<String, String[]>(){
			@Override
			public String[] apply(String input) {
				// TODO Auto-generated method stub
				return input.split(regex);
			}
		};
	}
	
	/**
	 * 
	 * 如何用
	 * 	yyyy_mm_ddFunc = Functions.dateToString( "yyyy-MM-dd" );
	 *  String str = yyyy_mm_ddFunc.apply( new Date() );
	 */
	public static Function<Date, String> dateToString( final String pattern ){
		return new Function<Date, String>(){
			@Override
			public String apply( Date date ){
				return new SimpleDateFormat( pattern ).format(date);
			}
		};
	}
	
	
	/***将一个条件表达式转换为一个函数操作***/
	
	/**
	 * 用于构造大于某个固定值的函数
	  Function<Integer,Boolean> greaterThan10 = Functions.greaterThan( 10 );
	  if( greaterThan10.apply( value ) ){
	  }
	 */
	public static Function<Integer, Boolean> greaterThan( final int target ){
		return new Function<Integer, Boolean>(){
			@Override
			public Boolean apply(Integer value) {
				// TODO Auto-generated method stub
				return value > target;
			}
		};
	}

	public static Function<Integer, Boolean> greaterThanEqual( final int target ){
		return new Function<Integer, Boolean>(){
			@Override
			public Boolean apply(Integer value) {
				// TODO Auto-generated method stub
				return value >= target;
			}
		};
	}
	
	
	public static Function<Integer, Boolean> lessThan( final int target ){
		return new Function<Integer, Boolean>(){
			@Override
			public Boolean apply(Integer value) {
				// TODO Auto-generated method stub
				return value < target;
			}
		};
	}
	
	public static Function<Integer, Boolean> lessThanEqual( final int target ){
		return new Function<Integer, Boolean>(){
			@Override
			public Boolean apply(Integer value) {
				// TODO Auto-generated method stub
				return value <= target;
			}
		};
	}
	
	
	//似乎没有什么卵用，
	//细思：可以将一切Map操作转换为函数调用， 意即函数式编程，
	//对于脚本式编程，反射式编程， 将一切操作全部函数化，是有一定好处的。
	public static <K,V> Function<K,V> mapGetFunc( final Map<K, ? extends V> map ){
		return new Function<K,V>(){
			@Override
			public V apply( K key ) {
				// TODO Auto-generated method stub
				return map.get( key );
			}
		};
	}
	
	
	
	/**
	 * 从一个固定的字典形式数据，配置信息形式数据，中取对应的值，如果没有返回缺省值
	Map<String,String> REGION_MAP = ImmutableMap.of(
		"027", "武汉",
		"0714", "黄石",
		"0710", "襄樊",
		"0717", "宜昌" );
		Function<String,String> getRegionFunc = Functions.mapGetFunc( REGION_MAP, "未知" );
		String regionName = getRegionFunc.apply( "027" );
	 */
	public static <K,V> Function<K,V> mapGetFunc( final Map<K, ? extends V> map, final V defaultValue ){
		return new Function<K,V>(){
			@Override
			public V apply( K key ){
				V value = map.get( key );
				return ( value != null || map.containsKey(key) ) ? value : defaultValue;
			}
		};
	}
	

    //给任何类型的对象，转换成字符串时添加前缀
	public static <T> Function<T, String> prefix( final String prefix ){
		return new Function<T, String>(){
			@Override
			public String apply(T value) {
				// TODO Auto-generated method stub
				return prefix + value;
			}
		};
	}
	
	//给任何类型的对象，转换成字符串时添加后缀
	public static <T> Function<T, String> suffix( final String suffix ){
		return new Function<T, String>(){
			@Override
			public String apply(T value) {
				// TODO Auto-generated method stub
				return value + suffix;
			}
		};
	}
	
	/**
	 * 给任何类型的对象，转换成字符串时添加后缀
	 * 典型的场景是在构造变量，构造表达式，构造xml时
	   Function<Object,String> varToEl = Functions.prefixSuffix( "${", "}" );
	   String strVar = varToEl( name );
	 */
	public static <T> Function<T, String> prefixSuffix( final String prefix, final String suffix ){
		return new Function<T, String>(){
			@Override
			public String apply(T value) {
				// TODO Auto-generated method stub
				return prefix + value + suffix;
			}
		};
	}
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.detentor/codexCollections/0.0.21/com/github/detentor/codex/function/Functions.java#Functions
	/**
	 * 用于便利的组装出某个Function<I,Boolean>的反函数
	   Function<User,Boolean> isAdminFunc = new Function<User,Boolean>(){
	     @Override
	     public Boolean apply( User user ){
	     	Preconditions.checkNotNull( user, "user is null");
	     	if( user.getName().equals("admin") || user.isAdminstrator() ){  return true; }
	     	return false;
	     }
	   };
	   //这样就很轻松的得到上面函数的反函数了。
	   Function<User,Boolean> isNotAdminFunc = Functions.not( isAdminFunc );
	 */
	public static <I> Function<I, Boolean> not( final Function<I,Boolean> func ){
		return new Function<I, Boolean>(){
			@Override
			public Boolean apply(I input) {
				// TODO Auto-generated method stub
				Preconditions.checkNotNull( input, "input is null");
				return !func.apply(input);
			}
		};
	}
	
	/**
	 * 空输入安全的函数调用
		Function<Context,User> getUserFunc = Functions.nullSafeFunc( func, defaultUser );
		User user = getUserFunc.apply( context );
	 */
	public static <I,O> Function<? super I, ? extends O> nullSafeFunc( final Function<? super I, ? extends O> func, final O defaultValue ){
		return new NullSafeFunc<I,O>( func, defaultValue );
	}
	
	private static final class NullSafeFunc<I,O> implements Function<I,O>{
		private Function<? super I, ? extends O> func;
		private O defaultValue;
		
		public NullSafeFunc( Function<? super I, ? extends O> func, O defaultValue ){
			this.func = func;
			this.defaultValue = defaultValue;
		}

		@Override
		public O apply(I input ) {
			// TODO Auto-generated method stub
			return ( input == null ) ?  this.defaultValue :  this.func.apply( input );
		}
		
		
	}//end class
	
	/**
	 * 另一种写法
	 * http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.detentor/codexCollections/0.0.21/com/github/detentor/codex/function/Functions.java#Functions
	   public static <A,B,C> Function<A,C> composeFunc( final Function<A,B> func1, final Function<B,C> func2 ){
	     return new Function<A,C>(){
	       @Override
	       public C apply( A input ){
	         return func2.apply( func1.apply( input ) );
	       }
	     }
	   }
	 */
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/org.voltdb/voltdbclient/5.4/com/google_voltpatches/common/base/Functions.java#Functions
	//函数聚合， 函数链， 函数管道
	public static <A,B,C> Function<A,C> composeFunc( Function<A,? extends B> func1, Function<B,C> func2 ){
		return new CompositionFunc<A,B,C>( func1, func2 );
	}
	
	public static  class CompositionFunc<A,B,C> implements Function<A,C>{
		private final Function<A,? extends B> func1;
		private final Function<B,C> func2;
		
		public CompositionFunc( Function<A,? extends B> func1, Function<B,C> func2 ){
			this.func1 = func1;
			this.func2 = func2;
		}
		
		@Override
		public C apply(A input ) {
			// TODO Auto-generated method stub
			return this.func2.apply( this.func1.apply(input) );
		}
		
		@Override
		public boolean equals( Object obj ){
			if( obj instanceof CompositionFunc ){
				CompositionFunc<?,?,?> that = (CompositionFunc<?,?,?>)obj;
				return func1.equals( that.func1) && func2.equals( that.func2 );
			}
			return false;
		}
		
		@Override
		public int hashCode(){
			return func1.hashCode() ^ func2.hashCode();
		}
		
		@Override
		public String toString(){
			return func2 + "(" + func1 + ")";
		}
	}
	
	
	/**   用函数来代理了一个断言
	 * 断言函数 
		用断言函数predicate构造一个能断言任何类型I的断言函数
		class NonNullPredicate<I> implements Predicate<I>{
		  boolean evaluate( I input ){
		  	if( input != null ){
		  		return true;
		  	}
		  	return false;
		  }
		}
		
		Function<Object, Boolean> func = Functions.forPredicate( new NonNullPredicate<Object>() );
		boolean result = func.apply( obj );
	 */
	public static <I> Function<I, Boolean> forPredicate( Predicate<I> predicate ){
		return new PredicateFunc<I>( predicate );
	}
	
	private static class PredicateFunc<I> implements Function<I, Boolean>{
		private final Predicate<I> predicate;
		
		private PredicateFunc( Predicate<I> predicate ){
			this.predicate = predicate;
		}
		
		@Override
		public Boolean apply(I value ) {
			// TODO Auto-generated method stub
			return this.predicate.evaluate( value );
		}
	}
	
   
	/*-----------------------类型: 对函数应用的包装-------------------------------*/
	/**
	 * 这类函数的特点
	 *    for开头，代表了一种循环调用 
	 */
	
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/org.jcommons/jcommons-functional/1.0.0/org/jcommons/functional/Functions.java#Functions
	 * 将list中值，每个都用func执行一遍。
	 *  List<User> users = Immutables.of( user1, user2, user3 );
	 *  class UserService{
	 *      public void saveUser( User user ) throws ServiceException{}
	 *      public User getUser( Integer userId ) throws ServiceException{}
	 *  }
	 *  Function<User,Void> saveUserFunc = new Function<User,Void>( UserService userService ){
	 *  	Void apply( User user ){
	 *  		userService.saveUser( user );
	 *  	}
	 *  };
	 *  saveUserFunc.apply( users );
	 */
	public static <I,O> void forEach( final Function<I,O> func, List<I> list ){
		if( func == null || list == null ){	
			return;
		}
		for( I input : list ){
			func.apply( input );
		}
	}
	
	public static <I,O> void forEach( final Function<I,O> func, final I... list ){
		if( func == null || list == null ){	
			return;
		}
		forEach( func, Arrays.asList( list ) );
	}
	
	/**
	 * List<Integer> userIds = Immutables.of( "13","34","44","55" );
	 * Function<Integer,User> getUserFunc = new Function<Integer, User>( UserService userService ){
	 * 		User apply( Integer userId ){
	 * 			userService.getUser( userId );
	 * 		}
	 * }
	 * List<User> users = getUserFunc.apply( userIds );
	 * 
	 */
	public static <I,O> List<O> forEachResult( final Function<I,O> func, final List<I> list ){
		if( list == null ){
			return null;
		}
		List<O> result = new ArrayList<O>( list.size() );
		for( I item : list ){
			if( func != null ){
				result.add( func.apply( item ) );
			}
		}
		return result;
	}
	
	
	/*---------------------委托型-----------------------*/
	private static class Wrapper<I,O> implements Function<I,O>{
		private final com.google.common.base.Function<I, O> function;
		
		public Wrapper( com.google.common.base.Function<I, O> function ){
			this.function = function;
		}
		@Override
		public O apply(I input) {
			// TODO Auto-generated method stub
			return function.apply(input);
		}
		
	}
	
	public static <I,O> Function<I,O> of( com.google.common.base.Function<I, O> function ){
		return new Wrapper<>(function);
	}
	
	public static <K,V> Function<K,V> forMap( Map<K, V> map ){
		return of( com.google.common.base.Functions.forMap(map) );
	}
	
//	public static <E> Function<E,E> identity(){
//		return of( com.google.common.base.Functions.identity() );
//	}
	
}

