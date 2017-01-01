package com.chnye.framework.pipeline.impl;

import java.util.Map;

import com.chnye.framework.pipeline.IChannel;
import com.chnye.framework.pipeline.IChannelFactory;

public class ChannelFactory implements IChannelFactory{

	@Override
	public IChannel newChannel() {
		// TODO Auto-generated method stub
		return new Channel();
	}

	@Override
	public IChannel newChannel(Map<String, Object> load) {
		String name = "Channel_" + System.currentTimeMillis();
		return new Channel( name,  load );
	}

}
