package com.chnye.common.enums;

public interface IType<T extends Enum<?>> {
	T getType();
}
