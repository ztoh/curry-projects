package com.chnye.common.utils;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.voltdb/voltdbclient/5.4/com/google_voltpatches/common/base/MoreObjects.java#MoreObjects.ToStringHelper.ValueHolder


public class ToStringHelper {

	private final String className;
	private ValueHolder holderHead = new ValueHolder();
	private ValueHolder holderTail = holderHead;
	private boolean omitNullValues = false;
	
	
	public ToStringHelper( String className ){
		this.className = className;
	}
	
	public ToStringHelper omitNullValues(){
		omitNullValues = true;
		return this;
	}
	
	public ToStringHelper add( String name,  Object value ){
		return addHolder( name, value );
	}
	
	public ToStringHelper add( String name, boolean value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper add( String name, char value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper add( String name, double value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper add( String name, float value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper add( String name, int value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper add( String name, long value ){
		return addHolder( name, String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( Object value ){
		return addHolder( value );
	}
	
	public ToStringHelper addValue( boolean value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( char value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( double value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( float value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( int value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public ToStringHelper addValue( long value ){
		return addHolder( String.valueOf( value ) );
	}
	
	public String toString(){
		boolean omitNullValuesSnapshot = omitNullValues;
		String nextSeparator = "";
		StringBuilder sb = new StringBuilder(32).append( className ).append( "{" );
		for( ValueHolder valueHolder = holderHead.next;  valueHolder != null;  valueHolder = valueHolder.next ){
			if( !omitNullValuesSnapshot || valueHolder.value != null ){
				sb.append(  nextSeparator );
				nextSeparator = ", ";
			}
			if( valueHolder.name != null ){
				sb.append( valueHolder.name ).append(  '=' );
			}
			sb.append( valueHolder.value );
		}//end for 
		return sb.append( '}' ).toString();
	}
	
	private ValueHolder addHolder(){
		ValueHolder valueHolder = new ValueHolder();
		holderTail = holderTail.next = valueHolder;
		return valueHolder;
	}
	
	private ToStringHelper addHolder( Object value ){
		ValueHolder valueHolder = addHolder();
		valueHolder.value = value;
		return this;
	}
	
	private ToStringHelper addHolder( String name, Object value ){
		ValueHolder valueHolder = addHolder();
		valueHolder.value = value;
		valueHolder.name = name;
		return this;
	}
	
	private static class ValueHolder{
		String name;
		Object value;
		ValueHolder next;
	}//end class

}
