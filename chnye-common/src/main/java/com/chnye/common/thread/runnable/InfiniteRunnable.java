package com.chnye.common.thread.runnable;
/*
* @Author: anchen
* @Date:   2016-01-19 20:50:28
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:52:54
*/

public class InfiniteRunnable implements Runnable {

    private final Runnable runnable;

    public InfiniteRunnable( Runnable runnable ){
        this.runnable = runnable;
    }

    @Override
    public void run(){
        while( !Thread.currentThread().isInterrupted() ){
            try{
                runnable.run();
            } catch( Throwable t ){
                t.printStackTrace();
            }
        }//end while
    }
}