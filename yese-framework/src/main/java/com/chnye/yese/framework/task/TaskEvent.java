package com.chnye.yese.framework.task;


import com.chnye.yese.common.event.TypeEvent;

public class TaskEvent<T extends Enum<?>> extends TypeEvent<T>{

	private TaskResult result;
	
	public enum ETaskStatus{
		ON_BEGIN,
		ON_COMPLETE,
		ON_ERROR,
		ON_CANCEL
	}
	
	
	public TaskEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

    public TaskEvent( Object source, T type ){
    	super( source, type );
    }
	
    public void setResult( TaskResult result ){
    	this.result = result;
    }
    public TaskResult getResult(){
    	return this.result;
    }

}
