package com.chnye.framework.db.column.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.chnye.framework.db.column.AbstractColumn;

public class DateColumn extends AbstractColumn{

	private EDateType dateType = EDateType.DATETIME;
			
	public static enum EDateType{
		DATE, TIME, DATETIME
	}
	
	public DateColumn( final Long stamp ){
		super( EType.DATE, stamp, null == stamp ? 0 : 8 );
	}

	public DateColumn( final java.sql.Time time ){
		this( time == null ? null : time.getTime() );
		this.setDateType( EDateType.TIME );
	}
	
	public DateColumn( final java.sql.Timestamp ts ){
		this( ts == null ? null : ts.getTime() );
		this.setDateType( EDateType.DATETIME );
	}

	public EDateType getDateType() {
		return dateType;
	}

	public void setDateType(EDateType dateType) {
		this.dateType = dateType;
	}

	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		return (Long)this.getRowData();
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String asString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date asDate() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return new Date( (Long)this.getRowData() );
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("date不能转换为bytes");
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("date不能转换为boolean");
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("date不能转换为BigDecimal");
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("date不能转换为BigInteger");
	}
	
	
}
