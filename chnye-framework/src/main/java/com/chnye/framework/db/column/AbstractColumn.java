package com.chnye.framework.db.column;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/element/Column.java

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.JSON;

import com.chnye.common.enums.AbstractType;
import com.chnye.framework.db.column.AbstractColumn.EType;



//public abstract class AbstractColumn {
//
//	private EType type;
//	private Object rowData;
//	private int byteSize;
//	
//	public enum EType{
//		BAD,NULL,INT,LONG,DOUBLE,STRING,BOOL,DATE,BYTES
//	}
//
//}

public abstract class AbstractColumn extends AbstractType<EType>{
	
	private Object rowData;
	private int byteSize;
	
	public enum EType{
		BAD,NULL,INT,LONG,DOUBLE,STRING,BOOL,DATE,BYTES
	}
	
	
	public AbstractColumn(EType type, Object rowData, int byteSize ){
		super( type );
		this.rowData = rowData;
		this.byteSize = byteSize;
	}

	public Object getRowData() {
		return rowData;
	}

	public void setRowData(Object rowData) {
		this.rowData = rowData;
	}

	public int getByteSize() {
		return byteSize;
	}

	public void setByteSize(int byteSize) {
		this.byteSize = byteSize;
	}
	
	public abstract Long asLong();
	public abstract Double asDouble();
	public abstract String asString();
	public abstract Date asDate();
	public abstract byte[] asBytes();
	public abstract Boolean asBoolean();
	public abstract BigDecimal asBigDecimal();
	public abstract BigInteger asBigInteger();
	
	@Override
	public String toString(){
		return JSON.toJSONString( this );
	}
	
	
}