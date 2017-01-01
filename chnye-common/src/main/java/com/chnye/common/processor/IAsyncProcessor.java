package com.chnye.common.processor;

import com.chnye.common.callback.Callback;

public interface IAsyncProcessor<I, O> {
    boolean process( I input, Callback<O> callback );
}