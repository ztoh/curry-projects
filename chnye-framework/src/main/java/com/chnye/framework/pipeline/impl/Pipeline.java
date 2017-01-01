package com.chnye.framework.pipeline.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.chnye.common.collection.ListSupport;
import com.chnye.framework.pipeline.IChannel;
import com.chnye.framework.pipeline.IPipe;
import com.chnye.framework.pipeline.IPipeListener;
import com.chnye.framework.pipeline.IPipeline;
import com.chnye.framework.pipeline.exception.AbortException;
import com.chnye.framework.pipeline.exception.PipelineException;

public class Pipeline extends LinkedList<IPipe> implements IPipeline{

	protected Iterable<IPipeListener> listeners = ListSupport.newLinkedList();
	
	public Pipeline(){
		super();
	}
	
	public Pipeline( Collection<? extends IPipe> pipes ){
		super( pipes );
	}
	
	@Override
	public boolean check(IChannel channle) throws AbortException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public IChannel process(IChannel channel) throws AbortException, PipelineException {
		for( IPipe pipe : this ){
			boolean proceed = true;
			try{
				fireBeforeCheck( channel );
				proceed = pipe.check( channel );
			} finally {
				fireAfterCheck( channel );
			}
			if( proceed ){
				try{
					fireBeforePerform( channel );
					pipe.process( channel );
				} finally {
					fireAfterPerform( channel );
				}
			}
		}
		return channel;
	}

	@Override
	public void addPipelineListener(IPipeListener listener) {
		listeners = ListSupport.addListener(listener, listeners);
	}

	@Override
	public void removePipelineListener(IPipeListener listener) {
		listeners = ListSupport.removeListener(listener, listeners);
	}

	protected void fireBeforeCheck( IChannel channel ){
		Iterator<IPipeListener> iter = listeners.iterator();
		while( iter.hasNext() ){
			iter.next().onBeforeCheck( channel );
		}
	}
	
	protected void fireAfterCheck( IChannel channel ){
		Iterator<IPipeListener> iter = listeners.iterator();
		while( iter.hasNext() ){
			iter.next().onAfterCheck( channel );
		}
	}
	
	protected void fireBeforePerform( IChannel channel ){
		Iterator<IPipeListener> iter = listeners.iterator();
		while( iter.hasNext() ){
			iter.next().onBeforeProcess(channel);
		}
	}
	
	protected void fireAfterPerform( IChannel channel ){
		Iterator<IPipeListener> iter = listeners.iterator();
		while( iter.hasNext() ){
			iter.next().onAfterProcess( channel );
		}
	}
	
	
}
