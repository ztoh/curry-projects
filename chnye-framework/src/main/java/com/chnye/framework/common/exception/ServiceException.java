package com.chnye.framework.common.exception;


public class ServiceException extends FrameworkException{
	
//	public ServiceException(FrameworkException exception) {
//        super(exception);
//    }

   
    public ServiceException(int code, String msg, Object... params) {
        super(code, msg, params);
    }

   
    public ServiceException(int code, String arg0, Throwable arg1, Object... params) {
        super(code, arg0, arg1, params);
    }

 
    public ServiceException(int code, String arg0, Throwable arg1) {
        super(code, arg0, arg1);
    }

    /**
     * ServiceException
     * @param code <br>
     * @param msg <br>
     */
    public ServiceException(int code, String msg) {
        super(code, msg);
    }

   
    public ServiceException(int code, Throwable arg0) {
        super(code, arg0);
    }

    
    public ServiceException(int code) {
        super(code);
    }

  
    public ServiceException(String arg0, FrameworkException arg1, Object... params) {
        super(arg0, arg1, params);
    }


    public ServiceException(String arg0, FrameworkException exception) {
        super(arg0, exception);
    }
}
