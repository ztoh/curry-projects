package com.chnye.common.thread.runnable;


public abstract class AbsStopableRunnable implements Runnable{

	protected boolean bStoped = false;
	protected boolean bFinished = false;
	
	@Override
	public void run(){
		while( !this.bStoped ){
			try{
				execute();
			} catch( Throwable t ){
				break;
			}
		}//end while
		bFinished = true;	
	}
	
	protected abstract void execute() throws Exception;
	
	public void stop(){  this.bStoped = true; }
	public boolean isStoped(){  return this.bStoped; }
	public boolean isFinished(){	return this.bFinished; }
	
	
	
}