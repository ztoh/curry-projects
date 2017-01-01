package com.chnye.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;



public class NumberUtil {

	  private NumberUtil(){}

	  /*--------------------- Number->Number ---------------------*/

	  public static <T extends Number> byte toByte( T value ){
	    return ( value != null ? value.byteValue() : null );
	  }

	  public static <T extends Number> Integer toInteger( T value ){
	    return ( value != null ? value.intValue() : null );
	  }

	  public static <T extends Number> Long toLong( T value ){
	    return ( value != null ? value.longValue() : null );
	  }

	  public static <T extends Number> Float toFloat( T value ){
	    return ( value != null ? value.floatValue() : null );
	  }

	  public static <T extends Number> Double toDouble( T value ){
	    return ( value != null ?  value.doubleValue() : null );
	  }

	  public static <T extends Number> BigDecimal toBigdecimal( T value ){
	    return ( value != null ? BigDecimal.valueOf( value.doubleValue() ) : null );
	  }

	  public static <T extends Number> BigInteger toBigInteger( T value ){
	    return ( value != null ? BigInteger.valueOf( value.longValue() ) : null );
	  }

	  /*--------------------- Object->Number ---------------------*/

	  public static int toInteger( Object value ){
	    if( value == null ){
	      throw new IllegalArgumentException("Unable to convert null to int");
	    } else if( value instanceof String ){
	      if( "".equals(value) ){  return 0; }
	      return Integer.parseInt( (String)value );
	    } else if( value instanceof Character ){
	      return ((Character)value).charValue();
	    } else if( value instanceof Number ){
	      return ((Number)value).intValue();
	    }
	    throw new IllegalArgumentException("" + value.getClass().getName()); 
	  }

	  public static long toLong( Object value ){
	    if( value == null ){
	      throw new IllegalArgumentException("Unable to convert null to long");
	    } else if( value instanceof String ){
	      if( "".equals(value) ){ return 0; }
	      return Long.parseLong( (String)value );
	    } else if( value instanceof Character ){
	      return ((Character)value).charValue();
	    } else if( value instanceof Number ){
	      return ((Number)value).longValue();
	    }
	    throw new IllegalArgumentException("" + value.getClass().getName() );
	  }

	  public static BigInteger toBigInteger( Object value ){
	    if( value == null ){ 
	      throw new IllegalArgumentException("Unable to convert null to bigint");
	    } else if( value instanceof BigInteger ){
	      return (BigInteger)value;
	    } else if( value instanceof String ){
	      String str = (String)value;
	      if( "".equals( str.trim() ) ){ return BigInteger.ZERO; }
	      return new BigInteger( str );
	    } else if( value instanceof Number ){
	      return new BigInteger( value.toString() );
	    } else if( value instanceof Character ){
	      int i = ((Character)value).charValue();
	      return BigInteger.valueOf(i);
	    }
	    throw new IllegalArgumentException("" + value.getClass().getName());
	  }

	  public static BigDecimal toBigDecimal( Object value ){
	    if( value == null ){
	      throw new IllegalArgumentException("Unable to convert null to bigdecimal");
	    } else if( value instanceof BigDecimal ){
	      return (BigDecimal)value;
	    } else if( value instanceof String ){
	      String str = (String)value;
	      if( "".equals( str.trim() ) ){ return BigDecimal.valueOf(0); }
	      return new BigDecimal( str );
	    } else if( value instanceof Number ){
	      return new BigDecimal( value.toString() );
	    } else if( value instanceof Character ){
	      int i = ((Character)value).charValue();
	      return new BigDecimal(i);
	    }
	    throw new IllegalArgumentException("");
	  }

	  public static double toDouble( Object value ){
	    if( value == null ){
	      throw new IllegalArgumentException("");
	    } else if( value instanceof String ){
	      String str = (String)value;
	      if("".equals(str.trim()) ){  return 0; }
	      return Double.parseDouble( str );
	    } else if( value instanceof Character ){
	      int i = ((Character)value).charValue();
	      return i;
	    } else if( value instanceof Number ){
	      return toDouble( (Number)value );
	    } else if( value instanceof Boolean ){
	      throw new IllegalArgumentException("");
	    }
	    throw new IllegalArgumentException("" + value.getClass().getName());
	  }

	/*
	  public static double toDouble( Number number ){
	    if( number instanceof Float ){
	      return Double.parseDouble( number.toString() );
	    }
	    return number.doubleValue();
	  }
	*/

	  public static boolean isBigDecimal( Object value ){
	    return value instanceof BigDecimal || value instanceof java.math.BigDecimal;
	  }

	  /*是否浮点数*/
	  public static boolean isfloatNumber( Object value ){
	    if( value instanceof Float || value instanceof Double ){ return true; }
	    if( value instanceof String ){
	      String str = (String) value;
	      return str.indexOf('.') != -1 || str.indexOf('e') != -1 || str.indexOf('E') != -1;
	    }
	    return false;
	  }

	  public static boolean isNumberable( final Object value ){
	    return  value instanceof Integer 
	         || value instanceof Long
	         || value instanceof Byte
	         || value instanceof Short;
	  }

	  /*--------------------- String->Number ---------------------*/

	  public static boolean isInt( String value ){
	    if( value != null ){
	      try{
	        Integer.parseInt( value );
	        return true;
	      } catch ( NumberFormatException e ){}
	    }
	    return false;
	  }

	  public static boolean isLong( String value ){
	    if( value != null ){
	      try{
	        Long.parseLong( value );
	        return true;
	      } catch ( NumberFormatException e ){}
	    }
	    return false;
	  }

	  public static boolean isFloat( String value ){
	    if( value != null ){
	      try{
	        Float.parseFloat( value );
	        return true;
	      } catch ( NumberFormatException e ){}
	    }
	    return false;
	  }

	  public static boolean isDouble( String value ){
	    if( value != null ){
	      try{
	        Double.parseDouble( value );
	        return true;
	      } catch ( NumberFormatException e ){}
	    }
	    return false;
	  }

	  public static boolean isNumber( String value ){
	    return isDouble(value) || isLong(value) ? true : false;
	  }

	  public static int toInt( String value, int defaultValue ){
	    if( value == null ){  return defaultValue; }
	    try{
	      return Integer.parseInt( value );
	    } catch( NumberFormatException e ){
	      return defaultValue;
	    }
	  }

	  public static long toLong( String value, int defaultValue ){
	    if( value == null ){  return defaultValue; }
	    try{
	      return Long.parseLong( value );
	    } catch( NumberFormatException e ){
	      return defaultValue;
	    }
	  }

	  public static float toFloat( String value, int defaultValue ){
	    if( value == null ){  return defaultValue; }
	    try{
	      return Float.parseFloat( value );
	    } catch( NumberFormatException e ){
	      return defaultValue;
	    }
	  }

	  public static double toDouble( String value, int defaultValue ){
	    if( value == null ){  return defaultValue; }
	    try{
	      return Double.parseDouble( value );
	    } catch( NumberFormatException e ){
	      return defaultValue;
	    }
	  }


	
	
}
