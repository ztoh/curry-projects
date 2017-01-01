package com.chnye.common.fsm;

import com.chnye.common.fsm.IGuard;

public class DefaultGuard implements IGuard<Context> {

	@Override
	public boolean allowed(Context context) {
		// TODO Auto-generated method stub
		return true;
	}

}
