package com.chnye.common.able;

/**
 * 
 * @author chnye
 *
 * 待商榷问题: 涉及到网络或者IO的场景，或许会期望能抛出异常
 */

public interface ICloseable {
	void close();
}
