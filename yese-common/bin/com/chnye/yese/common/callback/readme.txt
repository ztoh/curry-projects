AsyncResult.java
  public interface AsyncResult<R> {
    R result();
    Throwable cause();
    boolean successed();
    boolean failed();
  }
  ���ͣ�
      R��������Ҫ�����Ľ����
      ��Callback��������ǣ�һ�������Żص��Ľ����һ�������Żص��Ĺ��̡�
      
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
 ����: 
      R��������Ҫ�����Ľ����
      ���������������ţ��ڷ����߳���������֮���ɷ����߳���������ú����������𽫽�����ء�
      ��ô�����ǣ����д�������أ�
      ��ô�ڹ�������ص�����ʱ�Ĺ��췽���д���Ҫ��д��Ķ���
      
 ���
     ֻҪ�ǻص�����һ�����첽����Ϊ�ͻ��߳��ڵ���ĳ�������󣬾�ֱ�ӷ��أ��������������
     �Ǿ�һ���ǿͻ��̣߳��ͷ����̣߳����������߳�Э����
 
 ����������
     1.�ͻ��߳���Ե���һ���̳߳ء����̳߳��ڴ����������󣬸�����ûص�������
       ���ʱ��������ȡ�����̳߳ص��̴߳�С��
     2.�ͻ��߳���Ե���һ�����档���������������м����̳߳ء�
       ���ʱ��������ȡ�����������е�������
           
      