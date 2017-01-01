package com.chnye.framework.db.column.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;

import com.chnye.framework.db.column.AbstractColumn;
import com.chnye.framework.db.column.AbstractColumn.EType;

public class StringColumn extends AbstractColumn{

	public StringColumn( final String value ){
		super( EType.STRING, value, null == value ? 0 : value.length() );
	}

	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		try{
			BigInteger bigInteger = this.asBigInteger();
			return bigInteger.longValue();
		} catch( Exception e ){
			throw new UnsupportedOperationException( String.format("String[%s]不能转换为Long", this.asString() ) );
		}
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		String str = (String)this.getRowData();
		if( "NaN".equals(str) ){
			return Double.NaN;
		}
		if( "Infinity".equals(str) ){
			return Double.POSITIVE_INFINITY;
		}
		if( "-Infinity".equals(str) ){
			return Double.NEGATIVE_INFINITY;
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
		return null;
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		String str = this.asString();
		return str.getBytes( Charset.defaultCharset() );
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		if( "true".equalsIgnoreCase(this.asString()) ){
			return true;
		}
		if( "false".equalsIgnoreCase(this.asString()) ){
			return false;
		}
		throw new UnsupportedOperationException( String.format("String[%s]不能转换为boolean", this.asString() ) );
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		try{
			return new BigDecimal( this.asString() );
		} catch( Exception e ){
			throw new UnsupportedOperationException( String.format("String[%s]不能转换为BigDecimal", this.asString() ) );
		}
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		try{
			return this.asBigDecimal().toBigInteger();
		}catch( Exception e ){
			throw new UnsupportedOperationException( String.format("String[%s]不能转换为BigInteger", this.asString() ) );
		}
	}
	
}
