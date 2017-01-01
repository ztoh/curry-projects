package com.chnye.common.properties;

import java.util.Properties;

public interface IPropertiesLoader {
	Properties load( String location, String encoding ) throws PropertiesLoaderException;
}
