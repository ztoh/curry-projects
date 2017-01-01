package com.chnye.yese.framework.task;



import com.chnye.yese.common.able.INamable;
import com.chnye.yese.common.able.Identifiable;


public abstract class AbstractIdNamedTask<T> extends AbstractTask<T> implements INamable, Identifiable{

	private String id;
	private String name;
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
