这个包里设计了什么？为什么这么设计？怎么用？

这个包是用来设计与生命周期对象相关的功能的。
对于有自己父类的类需要生命周期功能，只能通过实现生命周期接口来处理。
对于没有自己父类的需要生命周期功能的，就适合通过继承AbstractLifecycleSupport类来处理。
对于需要随web容器一起初始化，停止的，就适合通过继承LifecycleBean类来处理。


各个类的功能：
  ILifecycle
    提供了生命周期的状态的枚举字典。
    提供了生命周期监听的接口方法。
  ILifecycleListener
    生命周期监听器
  LifecycleEvent
    生命周期的事件或消息
  AbstractLifecycleSupport
    一个实现全部生命周期的抽象类。
    实现了LifecycleListener利用handleEvent接口方法来接收start/stop事件，来实现自身的开始和结束。
  AbstractCompositeLifecycleSupport
    一个复合生命周期类
  LifecycleManager
    一个所有生命周期对象的管理器。
  LifecycleBean
    与web容器关联，一同启动停止。


  