package com.chnye.common.callback;
/*
* @Author: anchen
* @Date:   2016-01-19 20:02:26
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:18:38
*/

import java.util.List;
import java.util.ArrayList;


public class CallbackList<R> implements Callback<R>{
    private List<Callback<R>> callbacks = new ArrayList<Callback<R>>();

    public static <R>  CallbackList<R> create( Callback<R> callback ){
        CallbackList<R> obj = new CallbackList<R>();
        obj.add( callback );
        return obj;
    }

    public CallbackList<R> add( Callback<R> callback ){
        callbacks.add( callback );
        return this;
    }

    public void remove( Callback<R> callback ){
        callbacks.remove( callback );
    }

    @Override
    public void onSuccess( R result ){
        for( Callback<R> callback : callbacks ){
            callback.onSuccess( result );
        }
    }

    @Override
    public void onFailure( Throwable cause ){
        for( Callback<R> callback : callbacks ){
            callback.onFailure( cause );
        }
    }
}