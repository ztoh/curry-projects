AsyncResult.java
  public interface AsyncResult<R> {
    R result();
    Throwable cause();
    boolean successed();
    boolean failed();
  }
  解释：
      R代表着所要产生的结果。
      与Callback的区别就是，一个代表着回调的结果，一个代表着回调的过程。
      
      class MyUserTask implements ITask<User>{
        void onSucess(User result){
          ...
        }
        void onFailure(Throwable cause){
          ...
        }
      }
      MyUserTask myuserTask = new MyUserTask();
      
      Callback<AsyncResult<User>> callback = new Callabck<AsyncResult<User>>( myuserTask ){
        private ITask<User> myuserTask;
        public Callback( ITask<User> myuserTask ){  this.myuserTask = myuserTask; }

        @Override
        void onSuccess( R result ){
        	myuserTask.onSuccess( result );  
        }
        
        @Override
        void onFailure(Throwable cause){
         	myuserTask.onFailure( cause );
        }
      };
 


Callback.java
  public interface Callback<R> {
    void onSuccess( R result );
    void onFailure( Throwable cause );
 }
 解释: 
      R代表着所要产生的结果。
      这两个函数代表着，在服务线程运行完了之后，由服务线程来负责调用函数，来负责将结果返回。
      那么问题是，结果写到哪里呢？
      那么在构造这个回调对象时的构造方法中传入要被写入的对象
      
 概念：
     只要是回调，就一定是异步。因为客户线程在调用某个方法后，就直接返回，不再阻塞在那里。
     那就一定是客户线程，和服务线程，至少两个线程协作。
 
 常见场景：
     1.客户线程面对的是一个线程池。由线程池在处理完成任务后，负责调用回调方法。
       这个时候处理能力取决于线程池的线程大小。
     2.客户线程面对的是一个引擎。引擎里是阻塞队列加上线程池。
       这个时候处理能力取决于阻塞队列的容量。
           
      