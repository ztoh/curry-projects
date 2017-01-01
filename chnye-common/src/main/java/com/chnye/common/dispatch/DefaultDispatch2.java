package com.chnye.common.dispatch;

import com.chnye.common.handler.IAcceptHandler;
import com.chnye.common.utils.WatcherList;

public class DefaultDispatch2<I,O> implements IDispatch<I, O> {

	private WatcherList<IAcceptHandler<I,O>>  lists = WatcherList.create();
	
	@Override
	public O dispatch(I input) {
		// TODO Auto-generated method stub
		for( IAcceptHandler<I,O> handler : this.lists.getList() ){
			if( handler.accept(input) ){
				return handler.handle( input );
			}
		}
		return null;
	}

	public void addAcceptHandler( IAcceptHandler<I,O> handler ){
		this.lists.add( handler );
	}
	
	public void removeAcceptHandler( IAcceptHandler<I,O> handler ){
		this.lists.remove( handler );
	}
	
}
