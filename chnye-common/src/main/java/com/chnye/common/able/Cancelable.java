package com.chnye.common.able;
/*
* @Author: anchen
* @Date:   2016-01-18 00:36:01
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 20:22:59
*/

public interface Cancelable {

    boolean cancel( String reason );

    boolean isCanceled();

    String getCancelReason();

}