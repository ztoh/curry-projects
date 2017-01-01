package com.chnye.common.producer;

import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Producers {

	public static <T> IProducer<T>  producer( final Class<T> type ){
		try{
			final Constructor<T>  ctor = type.getConstructor();
			return new IProducer<T>(){
				@Override
				public T produce(){
					try{
						return ctor.newInstance();
					} catch ( Exception e ){
						throw new IllegalStateException(e.getMessage(), e );
					}
				}
			};
		} catch ( NoSuchMethodException e ){
			throw new IllegalStateException(e.getMessage(), e );
		}
	}
	
	public static <T> IProducer<T> producer( final Callable<T> callable ){
		return new IProducer<T>() {
			@Override
			public T produce(){
				try{
					return callable.call();
				} catch ( Exception e ){
					throw new IllegalStateException( e );
				}
			}
		};
	}
	
	
	public static <T> IProducer<T>  producer( final Future<T> future ){
		return new IProducer<T>(){
			@Override
			public T produce() {
				try{
					return future.get();
				} catch ( Exception e ){
					throw new IllegalStateException( e );
				}
			}
		};
	}
	
	
	
	
	
}











