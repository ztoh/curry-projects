package com.chnye.common.propertycontainer;

public interface IPropertyListener {
	void beforePropertyChange( String property, Object oldValue, Object newValue );
	void onPropertyChange( String property );
}
