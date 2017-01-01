package com.chnye.common.thread.runnable;
/*
* @Author: anchen
* @Date:   2016-01-19 20:47:09
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:50:12
*/

public abstract class AbstractRunnable implements Runnable {

    @Override
    public void run(){
        try{
            doRun();
        } catch( InterruptedException ex1 ){
            onFailure( ex1 );
        } catch( Throwable t ){
            onFailure( t );
        } finally {
            onAfter();
        }
    }

    public abstract void doRun() throws Exception;

    public abstract void onFailure( Throwable t );

    public void onAfter(){}
}