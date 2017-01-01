这个包里设计了什么？为什么这么设计？怎么用？


Consumer
  public interface Consumer<T> {
    void consume( T input );
  }

  接口： 消费者，具有消费能力的 
  特点：  函数无返回值

