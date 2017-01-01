package com.chnye.common.visitor;

public interface IVisitor<T> {
	boolean visit( T item );
}
