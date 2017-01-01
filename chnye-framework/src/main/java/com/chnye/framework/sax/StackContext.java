package com.chnye.framework.sax;

import java.util.Stack;

public class StackContext {

	private Stack _stack = new Stack();
	
	private Path _path = new Path(null);
	
	public StackContext(){
		
	}

	public Stack getStack() {
		return _stack;
	}

	public void setStack(Stack _stack) {
		this._stack = _stack;
	}

	public Path getPath() {
		return _path;
	}

	public void setPath(Path _path) {
		this._path = _path;
	}
	
	

	
	
}
