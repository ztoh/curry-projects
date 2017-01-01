package com.chnye.common.primitive;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.goldmansachs/gs-collections-api/6.2.0/com/gs/collections/api/IntIterable.java#IntIterable

public interface IPrimitiveIterable {
	int size();
	boolean isEmpty();
	boolean notEmpty();
	String toString();
	String makeString();
	String makeString( String separator );
	String makeString( String start, String separator, String end );
	void appendString( Appendable appendable );
	void appendString( Appendable appendable, String separator );
	void appendString( Appendable appendable, String start, String separator, String end );
	
}
