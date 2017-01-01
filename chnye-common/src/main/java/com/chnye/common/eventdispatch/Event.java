package com.chnye.common.eventdispatch;

/*
* @Author: anchen
* @Date:   2016-01-19 19:28:11
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-19 19:32:56
*/

public interface Event<T extends Enum<T>> {
    T getType();
}