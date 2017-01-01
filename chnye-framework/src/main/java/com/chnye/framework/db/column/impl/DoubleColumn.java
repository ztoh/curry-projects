package com.chnye.framework.db.column.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.chnye.framework.db.column.AbstractColumn;


public class DoubleColumn extends AbstractColumn{

	public DoubleColumn( final String value ){
		this( value, null == value ? 0 : value.length() );
		this.valudate( value );
	}
	

	public DoubleColumn( Long value ){
		this( value == null ? (String)null : String.valueOf(value) );
	}
	
	public DoubleColumn( Integer value ){
		this( value == null ? (String)null : String.valueOf( value ) );
	}
	
	public DoubleColumn( final Double value ){
		this( value == null ? (String)null : new BigDecimal( String.valueOf(value) ).toPlainString() );
	}

	public DoubleColumn( final BigDecimal value ){
		this( null == value ? (String)null : value.toPlainString() );
	}
	
	public DoubleColumn( final BigInteger value ){
		this( null == value ? (String)null : value.toString() );
	}
	
	private DoubleColumn( final String value, int byteSize ){
		super( EType.DOUBLE, value, byteSize );
	}

	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		BigDecimal result = this.asBigDecimal();
		return result.longValue();
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		String str = (String)this.getRowData();
		boolean isDoubleSpecific = str.equals("NaN") || str.equals("-Infinity") || str.equals("+Infinity");
		if( isDoubleSpecific ){
			return Double.valueOf( str );
		}
		BigDecimal result = this.asBigDecimal();
		return result.doubleValue();
		
	}

	@Override
	public String asString() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return (String)this.getRowData();
	}

	@Override
	public Date asDate() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("double不能转换为date");
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("double不能转换为bytes");
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("double不能转换为boolean");
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return new BigDecimal( (String)this.getRowData() );
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return this.asBigDecimal().toBigInteger();
	}

	private void valudate(String value) {
		// TODO Auto-generated method stub
		if( null == value ){
			return;
		}
		if( value.equalsIgnoreCase("NaN") || value.equalsIgnoreCase("-Infinity") || value.equalsIgnoreCase("Infinity") ){
			return ;
		}
		try{
			new BigDecimal( value );
		} catch( Exception e ){
			throw new UnsupportedOperationException( String.format("String[%s]不能转换为double", value ) );
		}
	}

	
}
