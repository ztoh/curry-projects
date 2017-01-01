package com.chnye.framework.db.column;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/element/Record.java

public interface IRecord {
	
	public void addColumn( AbstractColumn column );
	
	public void setColumn( int i, final AbstractColumn column );

	public AbstractColumn getColumn( int i );
	
	public String toString();
	
	public int getColumnNumber();
	
	public int getBytesSize();
	
}
