package com.chnye.framework.db.column.impl;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/element/BoolColumn.java

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.chnye.framework.db.column.AbstractColumn;

public class BooleanColumn extends AbstractColumn{

	public BooleanColumn( Boolean value) {
		super(EType.BOOL, value, 1);
		// TODO Auto-generated constructor stub
	}

	public BooleanColumn( final String value ){
		this( true );
		if( value == null ){
			this.setRowData( null );
			this.setByteSize( 0 );
		} else {
			this.setRowData( Boolean.valueOf( value ) );
			this.setByteSize( 1 );
		}
	}
	
	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return this.asBoolean() ? 1L : 0L;
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return this.asBoolean() ? 1.0d : 0.0d;
	}

	@Override
	public String asString() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return this.asBoolean() ? "true" : "false";
	}

	@Override
	public Date asDate() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("boolean不能转换为date");
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("boolean不能转换为bytes");
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return (Boolean)this.getRowData();
		
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return BigDecimal.valueOf( this.asLong() );
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return BigInteger.valueOf( this.asLong() );
	}

	private void validate( final String value ){
		if( null == value ){
			return;
		}
		if( "true".equalsIgnoreCase( value) || "false".equalsIgnoreCase( value) ){
			return;
		}
		throw new UnsupportedOperationException( String.format("String[%s]不能转换为bytes", value ) );
	}

}












