package com.chnye.biz.database;

import com.chnye.biz.entity.DbSqlBean;
import com.chnye.framework.common.exception.ServiceException;

public interface ISqlService {
	void executeSQL( DbSqlBean dbSqlBean ) throws ServiceException;
}
