package com.chnye.framework.env;



public abstract class AbstractEnv {
	Env DEFAULT_ENV = Env.PROD;
	
	enum Env{
		DEV,
		TEST,
		PROD
	}
	
	abstract Env getEnv();
}
