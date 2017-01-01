package com.chnye.common.enums;


public class AbstractType<T extends Enum<?>> implements IType<T> {

	private T type;
	
	public AbstractType( T type ) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	@Override
	public T getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	
}
