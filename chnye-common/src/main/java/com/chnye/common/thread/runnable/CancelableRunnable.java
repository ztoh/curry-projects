package com.chnye.common.thread.runnable;
/*
* @Author: anchen
* @Date:   2016-01-18 01:14:52
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:19:29
*/

public interface CancelableRunnable extends Runnable {
    void cancel();

    boolean isCanceled();

}