package com.chnye.common.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;




//http://www.grepcode.com/file/repo1.maven.org/maven2/com.revolsys.open/com.revolsys.open.core/2014.09.10-MTEC1/com/revolsys/util/Property.java?av=f

public class BeanUtil {

	/*-----------------------------类相关--------------------------*/
	
	public static boolean isAssignableFrom( final Collection<Class<?>> classes, final Class<?> objectClass ){
		for( final Class<?> allowedClass : classes ){
			if( allowedClass != null ){
				if( allowedClass.isAssignableFrom( objectClass ) ){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isAssignableFrom( final Collection<Class<?>> classes, final Object object ){
		Class<?> objectClass;
		if( object == null ){
			return false;
		} else if( object instanceof Class<?> ){
			objectClass = (Class<?>)object;
		} else {
			objectClass = object.getClass();
		}
		return isAssignableFrom(classes, objectClass);
		
	}
	
	
	
	/*-----------------------------属性相关--------------------------*/
	
	/**
	 * 得到类的某个属性的描述
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static PropertyDescriptor descriptor( final Class<?> clazz, final String fieldName ){
		if( null != clazz && StringUtils.isNotBlank(fieldName) ){
			try{
				BeanInfo beanInfo = Introspector.getBeanInfo( clazz );
				final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for( int i = 0; i < props.length; i++ ){
					final PropertyDescriptor propertyDescriptor = props[i];
					if( propertyDescriptor.getName().equals( fieldName ) ){
						return propertyDescriptor;
					}
				}
			} catch ( final IntrospectionException e ){
				throw new RuntimeException( e );
			}
		}
		return null;
	}
	
	/**
	 * 得到类的某个属性的类Class
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Class<?> getClass( final Class<?> clazz, final String fieldName ){
		final PropertyDescriptor propertyDescriptor = descriptor( clazz, fieldName );
		if( null == propertyDescriptor ){
			return null;
		}
		return propertyDescriptor.getPropertyType();
	}
	
	/**
	 * 得到某个对象的字段的类Class
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Class<?> getClass( final Object object, final String fieldName ){
		if( null == object ){
			return null;
		}
		final Class<?> objectClass = object.getClass();
		final Class<?> fieldClass = getClass( objectClass, fieldName );
		return fieldClass;
	}
	
	
	/*-----------------------------方法相关--------------------------*/
	
	/**
	 * 调用某个对象的方法
	 * @param object
	 * @param methodName
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <V> V invoke( final Object object, final String methodName, final Object... parameters ){
		try{
			if( object instanceof Class<?> ){
				final Class<?> clazz = (Class<?>)object;
				return (V)MethodUtils.invokeStaticMethod( clazz, methodName, parameters );
			} else {
				return (V)MethodUtils.invokeMethod( object, methodName, parameters );
			}
		} catch ( final InvocationTargetException e ){
			throw new RuntimeException( e );
		} catch( final Throwable e ){
			throw new RuntimeException( e );
		}
	}
	
	/**
	 * 调用某个对象的方法
	 * @param method
	 * @param object
	 * @param parameters
	 * @return
	 */
	public static <V> V method( final Method method, final Object object, final Object... parameters ){
		try{
			@SuppressWarnings("unchecked")
			final V result = (V)method.invoke( object, parameters );
			return result;
		} catch( Exception e ){
			throw new RuntimeException( e );
		}
	}
	
	/**
	 * 得到某个属性的读方法
	 */
	public static Method readMethod( final Class<?> clazz, final String fieldName ){
		final PropertyDescriptor propertyDescriptor = descriptor( clazz, fieldName );
		if( null == propertyDescriptor ){
			return null;
		}
		return propertyDescriptor.getReadMethod();
	}
	
	public static Method readMethod( final Object object, final String fieldName ){
		return readMethod( object.getClass(), fieldName );
	}
	
	/**
	 * 得到某个属性的写方法
	 */
	public static Method writeMethod( final Class<?> clazz, final String fieldName ){
		final PropertyDescriptor propertyDescriptor = descriptor( clazz, fieldName );
		if( null == propertyDescriptor ){
			return null;
		}
		return propertyDescriptor.getWriteMethod(); 
	}
	
	public static Method writeMethod( final Object object, final String fieldName ){
		return writeMethod( object.getClass(), fieldName );
	}
	
	
	/*-----------------------------构造方法相关--------------------------*/
	
	/**
	 * 调用构造函数
	 * @param constructor
	 * @param initargs
	 * @return
	 */
	public static <T> T invokeConstructor( final Constructor<? extends T> constructor, final Object... initargs ){
		try{
			final T object = constructor.newInstance( initargs );
			return object;
		} catch( final RuntimeException e ){
			throw e;
		} catch( final Error e ){
			throw e;
		} catch( InvocationTargetException e ){
			throw new RuntimeException( e );
		} catch( final Exception e ){
			throw new RuntimeException( e );
		}
	}
	
	
	
	
}
