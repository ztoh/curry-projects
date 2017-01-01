package com.chnye.framework.db.column.impl;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/element/LongColumn.java

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.chnye.framework.db.column.AbstractColumn;


public class LongColumn extends AbstractColumn{

	public LongColumn( final String value ){
		super( EType.LONG, null, 0 );
		if( null == value ){
			return;
		}
		try{
			 BigInteger rowData = new BigDecimal( value ).toBigInteger();
			 this.setRowData( rowData );
			 this.setByteSize( value.length() );
		}catch( Exception e ){
			throw new UnsupportedOperationException( String.format("String[%s]不能转换为long", value ) );
		}
	}

	public LongColumn( Long value ){
		this( null == value ? (BigInteger)null : BigInteger.valueOf(value) );
	}
	
	public LongColumn( Integer value ){
		this( null == value ? (BigInteger)null : BigInteger.valueOf(value) );
	}
	
	public LongColumn( BigInteger value ){
		this( value, null == value ? 0 : 8 );
	}
	
	public LongColumn( BigInteger value, int byteSize ){
		super( EType.LONG, value,  byteSize );
	}

	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		BigInteger rowData = (BigInteger)this.getRowData();
		if( null == rowData ){
			return null;
		}
		return rowData.longValue();
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
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
		return ((BigInteger)this.getRowData()).toString();
	}

	@Override
	public Date asDate() {
		// TODO Auto-generated method stub
		if( null == this.getRowData()){
			return null;
		}
		return new Date( this.asLong() );
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("long不能转换为bytes");
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return this.asBigInteger().compareTo( BigInteger.ZERO ) != 0 ? true : false;
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return new BigDecimal( this.asBigInteger() );
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return (BigInteger)this.getRowData();
	}
	
	
}
