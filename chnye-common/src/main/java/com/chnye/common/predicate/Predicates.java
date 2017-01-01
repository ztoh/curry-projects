package com.chnye.common.predicate;

/**
 * 分成几种类型
 *    一种类型是, 提供静态方法的功能函数，
 *           直接调用返回boolean值
 *           直接调用，返回参数集合的子集，起到一个过滤的作用
 *    另一种类型是，构造更为复杂的Predicate, 返回的也当然是Predicate类型。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.chnye.common.function.Function;


public class Predicates {

	/*----------------------类型一: 提供静态方法的功能函数，直接调用返回boolean值 --------------------------*/

	//http://www.grepcode.com/file/repo1.maven.org/maven2/org.jcommons/jcommons-functional/1.0.0/org/jcommons/functional/Functions.java#Functions
		/**
		 * list中每个值都满足断言函数时，返回true
		 */
		public static <T> boolean every( final Predicate<T> predicate, final Collection<T> list ){
			if( list == null || predicate == null ){
				return false;
			}
			boolean matches = true;
			for( T item : list ){
				matches = predicate.evaluate( item );
				if( !matches ){
					break;
				}
			}
			return matches;
		}
		
		/**
		 * list中的值，只要存在满足断言函数的值就行。
		 */
		public static <T> boolean some( final Predicate<T> predicate, final Collection<T> list ){
			if( list == null || predicate == null ){
				return false;
			}
			boolean matches = false;
			for( T item : list ){
				matches = predicate.evaluate( item );
				if( matches ){
					break;
				}
			}
			return matches;
		}
		
		public static <T> boolean some( final Predicate<T> predicate, final T... list ){
			if( list == null || predicate == null ){
				return false;
			}
			return some( predicate, Arrays.asList( list ) );
		}
		
		/*----------------------类型一: 提供静态方法的功能函数，返回参数集合的子集，起到一个过滤的作用 --------------------------*/
		
		/** 过滤功能
		 *  返回list中满足断言函数的结果集
		 */
		public static <T> List<T> filter( final Predicate<T> predicate, final Collection<T> list ){
			if( list  == null ){
				return null;
			}
			List<T> result = new ArrayList<T>( list.size() );
			for( T item : list ){
				if( predicate != null ){
					if( predicate.evaluate( item ) ){
						result.add( item );
					}
				} else {
					result.add( item );
				}
			}
			return result;
		}
		
		public static <T> List<T> filter( final Predicate<T> predicate, final T... list ){
			if( list == null ){
				return null;
			}
			return filter( predicate,  Arrays.asList( list ) );
		}
		
	/*----------------------类型二: 构造更为复杂的Predicate --------------------------*/
	
	public static <T> Predicate<T>  and(  final Predicate<? super T>...  predicates ){
		return new Predicate<T>() {
			@Override
			public boolean  evaluate( T object ){
				for( Predicate<? super T> predicate : predicates ){
					if( !predicate.evaluate(object) ){
						return false;
					}
				}
				return true;
			}
		};
	}
	
	/**
	 * 反面
	 */
	public static <T> Predicate<T>  negate( final Predicate<? super T> predicate ){
		return new Predicate<T>() {
			@Override
			public boolean evaluate( T object ){
				return !predicate.evaluate(object);
			}
		};
	}
	
	public static <T> Predicate<T>  or( final Predicate<? super T> p1, final Predicate<? super T> p2 ){
		return new Predicate<T>() {
			@Override
			public boolean evaluate( T object ){
				return p1.evaluate(object) || p2.evaluate(object);
			}
		};
	}
	
	
	public static <T> Predicate<T>  xor( final Predicate<? super T> p1, final Predicate<? super T> p2 ){
		return new Predicate<T>() {
			@Override
			public boolean evaluate( T object ){
				return ( !p1.evaluate(object) && !p2.evaluate(object) )
						|| ( p1.evaluate(object) && p2.evaluate(object) );
			}
		};
	}
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/com.google.guava/guava/19.0-rc1/com/google/common/base/Predicates.java#Predicates
	
	public static <T> Predicate<T> in( Collection<? extends T> values ){
		return new InPredicate<T>( values );
	}
	
	private static class InPredicate<T> implements Predicate<T>{
		private final Collection<?> values;
		
		private InPredicate( Collection<?> values ){
			this.values = values;
		}

		@Override
		public boolean evaluate(T object ) {
			// TODO Auto-generated method stub
			try{
				return values.contains( object );
			} catch( Exception e ){
				return false;
			}
		}
	}
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/com.google.guava/guava/19.0-rc1/com/google/common/base/Predicates.java#Predicates
	/**
	 * Function与Predicate的组合形式
	 *   将只能接收O类型的Predicate，组合成了能接收I类型的Predicate
	 */
	public static <I,O> Predicate<I> compose( Predicate<O> predicate, Function<I,O> function ){
		return new FunctionPredicate<I,O>( predicate, function );
	}
	
	private static class FunctionPredicate<I,O> implements Predicate<I>{
		final Predicate<O> predicate;
		final Function<I,O> function;
		
		private FunctionPredicate( Predicate<O> predicate, Function<I,O> function ){
			this.predicate = predicate;
			this.function = function;
		}

		@Override
		public boolean evaluate(I object) {
			// TODO Auto-generated method stub
			return predicate.evaluate( function.apply( object ) );
		}
	}
	
	
	
}










