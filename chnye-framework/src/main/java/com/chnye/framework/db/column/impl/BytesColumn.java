package com.chnye.framework.db.column.impl;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/element/BytesColumn.java

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

import com.chnye.framework.db.column.AbstractColumn;

public class BytesColumn extends AbstractColumn{

	public BytesColumn( byte[] bytes ){
		super( EType.BYTES, ArrayUtils.clone(bytes),  null == bytes ? 0 : bytes.length );
	}


	@Override
	public Long asLong() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为long");
	}

	@Override
	public Double asDouble() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为double");
	}

	@Override
	public String asString() {
		// TODO Auto-generated method stub
		try {
			return new String( (byte[])this.getRowData(), Charset.defaultCharset().toString() );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Date asDate() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为date");
	}

	@Override
	public byte[] asBytes() {
		// TODO Auto-generated method stub
		if( null == this.getRowData() ){
			return null;
		}
		return (byte[])this.getRowData();
	}

	@Override
	public Boolean asBoolean() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为boolean");
	}

	@Override
	public BigDecimal asBigDecimal() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为BigDecimal");
	}

	@Override
	public BigInteger asBigInteger() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("bytes不能转换为BigInteger");
	}


}
