package com.chnye.common.callback;
/*
* @Author: anchen
* @Date:   2016-01-19 20:00:14
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:01:32
* 
* How to use?
* 
* class RespResult{}
* 
* interface Handler<E>{
* 	handle( E event )
* }
* 
* 组合：  Handler<AsyncResult<E>>  效果相当于Callback
* 
* new Handler<AsyncResult<RespResult>>{
*   @Override
*   public void handle( AsyncResult<RespResult>> event ){
*     if( event.failed() ){
*     }
*   }
* }
* 
*/

public interface AsyncResult<R> {
    R result();
    Throwable cause();
    boolean successed();
    boolean failed();

}