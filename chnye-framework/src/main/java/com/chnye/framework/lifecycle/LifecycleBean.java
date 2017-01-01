package com.chnye.framework.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.chnye.common.lifecycle.LifecycleException;

public class LifecycleBean extends AbstractLifecycleSupport implements InitializingBean, DisposableBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		start();
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		lifecycleDestroy();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lifecycleDestroy() throws LifecycleException {
		// TODO Auto-generated method stub
		stop();
	}

	@Override
	public void lifecycleClear() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}



	



}
