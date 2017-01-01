package com.chnye.biz.opstep;

import com.chnye.framework.common.exception.ServiceException;
import com.chnye.framework.context.IContext;

public interface IOpStep {

	int execute( IContext context ) throws ServiceException;
}
