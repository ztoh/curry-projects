package com.chnye.common.callback;
/*
* @Author: anchen
* @Date:   2016-01-19 19:59:11
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:01:46
*/

public interface Callback<R> {
    void onSuccess( R result );
    void onFailure( Throwable cause );
}