package com.chnye.common.spring.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.MethodInvoker;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


public class ReflectionUtils extends org.springframework.util.ReflectionUtils{

	public static List<Class<?>> getTypeHierarchy( Class<?> type ){
		List<Class<?>> list = Lists.newArrayList();
		if( type.getSuperclass() != null ){
			list.addAll( getTypeHierarchy( type.getSuperclass() ) );
		}
		list.add( type );
		return list;
	}

	public static Map<Class<?>, ParameterizedType> getAllParameterizedInterfaces( Class<?> type ){
		List<Type> list = getAllGenericInterfaces( type );
		Map<Class<?>, ParameterizedType> map = Maps.newHashMap();
		for( Type element : list ){
			if( element instanceof ParameterizedType ){
				ParameterizedType pType = (ParameterizedType)element;
				Class<?> interfaceClass = (Class<?>)pType.getRawType();
				map.put( interfaceClass, pType );
			}
		}
		return map;
	}
	
	public static List<Type> getAllGenericInterfaces( Class<?> type ){
		List<Class<?>> path = getTypeHierarchy( type );
		List<Type> list = Lists.newArrayList();
		for( Class<?> element : path ){
			Type[] interfaces = element.getGenericInterfaces();
			list.addAll( ImmutableList.copyOf( interfaces ) );
		}
		return list;
	}
	
	public static boolean isFinal( Class<?> type ){
		return Modifier.isFinal( type.getModifiers() );
	}
	
	public static boolean isFinal( Field field ){
		return Modifier.isFinal( field.getModifiers() );
	}
	
	public static boolean isCollection( Field field ){
		return Collection.class.isAssignableFrom( field.getType() );
	}
	
	public static boolean isStringCollection( Field field ){
		return isCollection( field ) && hashMatchingParameterizedArgTypes( field, String.class );
	}
	
	public static boolean isMap( Field field ){
		return Map.class.isAssignableFrom( field.getType() );
	}
	
	/**
	 * 识别Map<String,XXX>型
	 */
	public static boolean isStringKeyedMap( Field field ){
		return isMap( field ) && hashMatchingParameterizedArgTypes( field, String.class );
	}
	

	public static boolean isString( Field field ){
		return field.getType() == String.class;
	}
	
	public static boolean isCharSequence( Field field ){
		return isCharSequence( field.getType() );
	}

	public static boolean isCharSequence(Class<?> type) {
		return CharSequence.class.isAssignableFrom( type );
	}
	
	
	private static boolean hashMatchingParameterizedArgTypes(Field field, Class<?>... expectedTypeArguments ) {
		Type genericType = field.getGenericType();
		if( genericType instanceof ParameterizedType ){
			ParameterizedType parameterizedType = (ParameterizedType)genericType;
			return hashMatchingActualTypeArguments( parameterizedType, expectedTypeArguments );
		} else {
			return false;
		}
	}

	private static boolean hashMatchingActualTypeArguments( ParameterizedType type, Class<?>... expectedTypeArguments) {
		Type[] actualTypeArguments = type.getActualTypeArguments();
		for (int i = 0; i < expectedTypeArguments.length; i++) {
		    Class<?> expectedTypeArgument = expectedTypeArguments[i];
		    if (i >= actualTypeArguments.length) {
		        return false;
		    }
		    if (!(actualTypeArguments[i] instanceof Class<?>)) {
		        return false;
		    }
		    Class<?> actualTypeArgument = (Class<?>) actualTypeArguments[i];
		    if (actualTypeArgument != expectedTypeArgument) {
		        return false;
		    }
		}
		return true;
	}
	
	public static void validateIsSuperType( Class<?> superType, Class<?> type ){
		checkSuperType( superType, type );
	}

	private static Class<?> checkSuperType(Class<?> superType, Class<?> type) {
		boolean expression = isSuperType( superType, type );
		Preconditions.checkArgument( expression, "[%s] must descend from (or be) [%s]", type.getCanonicalName(), superType.getCanonicalName() );
		return superType;
	}

	private static boolean isSuperType(Class<?> superType, Class<?> type) {
		return superType.isAssignableFrom( type );
	}
	
	public static <T extends Annotation> T getAnnotation( Class<?> type, Class<T> annotationClass ){
		return type.getAnnotation( annotationClass );
	}
	
	public static List<Class<?>> getDeclarationHierarchy( Class<?> type ){
		List<Class<?>> hierarchy = new ArrayList<Class<?>>();
		Class<?> declaringClass = type.getDeclaringClass();
		if( declaringClass != null ){
			hierarchy.addAll( getDeclarationHierarchy( declaringClass ) );
		}
		hierarchy.add( type );
		return hierarchy;
	}
	
	public static String getDeclarationPath( Class<?> type ){
		List<Class<?>> hierarchy = getDeclarationHierarchy( type );
		List<String> names = Lists.newArrayList();
		for( Class<?> element : hierarchy ){
			names.add( element.getSimpleName() );
		}
		return Joiner.on(',').join( names );
	}
	
	/**
	 * 设置值
	 */
	public static void set( Object instance, Field field, Object value ){
		synchronized( field ){
			boolean accessible = field.isAccessible();
			if( !accessible ){
				field.setAccessible( true );
			}
			try{
				field.set( instance , value );
			} catch (IllegalAccessException e ){
				throw new IllegalStateException( e );
			} finally {
				if( !accessible ){
					field.setAccessible( false );
				}
			}
		}//end sync
	}
	

	
	/**
	 * 返回当前类所在的所有属性
	 */
	public static Set<Field> getFields( Class<?> type ){
		return ImmutableSet.copyOf( type.getDeclaredFields() );
	}
	
	public static Set<Field> getFields( Class<?> type, boolean includeInheritedFields ){
		if( includeInheritedFields ){
			return getAllFields( type );
		} else {
			return getFields( type );
		}
	}
	
	public static Map<String,Field> getNameMap( List<Field> fields ){
		Map<String,Field> map = Maps.newHashMap();
		for( Field field : fields ){
			map.put( field.getName(), field );
		}
		return map;
	}
	
	/**
	 * 只返回Set集合中指定的属性
	 */
	public static Map<String,Field> getFields( Class<?> type, Set<String> fieldNames ){
		Map<String, Field> fields = Maps.newHashMap();
		for( String fieldName : fieldNames ){
			try{
				fields.put( fieldName, type.getDeclaredField(fieldName) );
			} catch( NoSuchFieldException e ){
				throw new IllegalStateException( e );
			} catch( SecurityException e ){
				throw new IllegalStateException( e );
			}
		}
		return fields;
	}
	
	/**
	 * 返回包含超类的属性
	 */
	public static Set<Field> getAllFields( Class<?> type ){
		Set<Field> fields = Sets.newHashSet();
		for( Class<?> clz = type; clz != null ; clz = clz.getSuperclass() ){
			Set<Field> set = getFields( clz );
			fields.addAll( set );
		}
		return ImmutableSet.copyOf( fields );
	}
	
	public static List<Field> getAllFieldsList( Class<?> type ){
		List<Field> fields = Lists.newArrayList();
		for( Class<?> clz = type; clz != null ; clz = clz.getSuperclass() ){
			Set<Field> set = getFields( clz );
			fields.addAll( set );
		}
		return ImmutableList.copyOf( Lists.reverse( fields ) );
	}
	
	
	public static Object invokeMethod(Class<?> targetClass, String targetMethod, Object... arguments) {
	    MethodInvoker invoker = new MethodInvoker();
	    invoker.setTargetClass(targetClass);
	    invoker.setTargetMethod(targetMethod);
	    invoker.setArguments(arguments);
	    return invoke(invoker);
	}

	public static Object invokeMethod(Object targetObject, String targetMethod, Object... arguments) {
	    MethodInvoker invoker = new MethodInvoker();
	    invoker.setTargetObject(targetObject);
	    invoker.setTargetMethod(targetMethod);
	    invoker.setArguments(arguments);
	    return invoke(invoker);
	}

	public static Object invoke(MethodInvoker invoker) {
	    try {
	        invoker.prepare();
	        return invoker.invoke();
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException(e);
	    } catch (NoSuchMethodException e) {
	        throw new IllegalStateException(e);
	    } catch (InvocationTargetException e) {
	        throw new IllegalStateException(e);
	    } catch (IllegalAccessException e) {
	        throw new IllegalStateException(e);
	    }
	}
	
	public static Class<?> getClass( String className ){
		try{
			return Class.forName( className );
		} catch ( ClassNotFoundException e ){
			throw new IllegalArgumentException( e );
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getTypedClass( String className ){
		try{
			return (Class<? extends T>)Class.forName(className);
		} catch ( ClassNotFoundException e ){
			throw new IllegalArgumentException( e );
		}
	}
	
	public static <T> T newInstance( Class<T> instanceClass ){
		try {
			return instanceClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IllegalArgumentException( e );
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IllegalArgumentException( e );
		}
	}
	
	
	
	
	
	
	
	
	
}
