package com.chnye.common.thread.runnable;


public interface ICancelableRunnable extends Runnable {
    void cancel();

    boolean isCanceled();

}