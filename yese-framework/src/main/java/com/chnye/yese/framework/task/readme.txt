接口
  ITask
    interface ITask<R,E>{
      R execute() throws E;
    }


任务事件 TaskEvent
  

任务监听器，ITaskListener  主要用于任务的各个阶段回调
  interface ITaskListener{}

TaskRunnable
  任务执行过程封装,用于保证会回调。
