package com.chnye.common.base;

//https://github.com/alibaba/DataX/blob/master/common/src/main/java/com/alibaba/datax/common/base/BaseObject.java

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseObject {

	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals( Object object ){
		return EqualsBuilder.reflectionEquals( this, object, false );
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
