package com.chnye.common.thread.pool;

/**
 * http://grepcode.com/file/repo1.maven.org/maven2/com.helger/ph-commons/6.1.0/com/helger/commons/concurrent/ExtendedDefaultThreadFactory.java?av=f
 */
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

	protected static final AtomicInteger poolNumber = new AtomicInteger(1);
	protected final ThreadGroup group;
	protected AtomicInteger threadNumber = new AtomicInteger(1);
	protected String namePrefix;
	
	public NamedThreadFactory( String name ) {
		SecurityManager sm = System.getSecurityManager();
		this.group = ( sm != null ) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
		
		StringBuilder sb = new StringBuilder();
		if( name == null ){
			name = "pool";
		}
		sb.append( name );
		sb.append( "-" );
		sb.append( poolNumber.getAndIncrement() );
		sb.append( "-thread-");
		this.namePrefix = sb.toString();
		
	}
	
	@Override
	public Thread newThread(Runnable r) {
		String threadName = new StringBuilder().append( namePrefix ).append( threadNumber.getAndIncrement() ).toString();
		Thread t = new Thread( group, r, threadName, 0 );
		t.setDaemon( true );
		if( t.getPriority() != Thread.NORM_PRIORITY ){
			t.setPriority( Thread.NORM_PRIORITY );
		}
		return null;
	}

}
