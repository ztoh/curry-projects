package com.chnye.common.dispatch;

import com.chnye.common.handler.IAcceptHandler;
import com.chnye.common.tuple.Tuple3;
import com.chnye.common.tuple.TupleUtil;

public class FttbAcceptHandler implements IAcceptHandler<Tuple3, Tuple3>{

	@Override
	public Tuple3 handle(Tuple3 input) {
		// TODO Auto-generated method stub
		System.out.println( "FttbHandler " + input + " Thread:" + Thread.currentThread().getId() );
		
		Tuple3 result = TupleUtil.create("resulth1", "resulth2", "resulth3" );
		return result;
	}

	@Override
	public boolean accept(Tuple3 input) {
		// TODO Auto-generated method stub
		String fttxType = input.first() != null ? input.first().toString() : "FTTB";
		String terminalType = input.second() != null ? input.second().toString() : "";
		String swtDomain = input.third() != null ? input.third().toString() : "";
		
		if( fttxType.equals("FTTB") ){
			return true;
		}
		
		return false;
	}

}
