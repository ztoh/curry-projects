package com.chnye.common.handler;


import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class HandlerList<I, O> implements IHandler<I,O> {
	
    private List<IHandler<I,O>>  handlers = new CopyOnWriteArrayList<IHandler<I,O>>();

    public static <I,O> HandlerList<I,O>  create( IHandler<I,O> handler ){
        HandlerList<I,O>  hLists = new HandlerList<I,O>();
        hLists.add( handler );
        return hLists;
    }

    public HandlerList<I,O> add( IHandler<I,O> handler ){
        handlers.add( handler );
        return this;
    }

    public void remove( IHandler<I,O> handler ){
        handlers.remove( handler );
    }

    @Override
    public O handle( I input ){
        for( IHandler<I,O> handler : handlers ){
            handler.handle( input );
        }
        return null;
    }

}
