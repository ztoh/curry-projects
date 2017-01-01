这个包里设计了什么？为什么这么设计？怎么用？还有哪些有待完善的地方？


这个包是用来设计跟配置文件相关的功能的。

各个类的功能
  PropertiesHolder.java
    根据类的名字就知道，其代表着它持有Properties变量。
    那么这个类的功能就是围绕着Properties变量，进行扩展，即它怎么来的，又怎么更友好的提供。
    首先它是怎么来的？
        它接受一个name来为自己取个名字。
        它接受一个字符串数组作为入参，传递配置文件的路径信息。
        它提供一个方法loadProperties()来进行配置信息的加载，
             扩展点: 也支持重新加载，这在配置文件修改后，热加载很有用。
        具体的加载，我们把这个功能独立出来，委托给现有更底层的框架来帮我们加载。
            这让我们这个类，拜托了直接对底层框架的依赖。只依赖我们的委托类。

    它怎么更友好的提供服务。  
        缺省的Properties只提供得到String,这样扩展了提供一系列类型如boolean/int/long
        
  LifeCyclePropertiesHolder.java
    根据类的名字就知道这是一个支持生命周期的Properties的持有者。
    在上面的类的功能的基础上，提供了对全生命周期的方法的支持。

  PropertiesManager.java
    根据类的名字就知道这是一个所有配置文件的管理器。
    即整个应用只面对他，向他要各种配置文件。
    管理器同时也支持生命周期。这样方便整个应用在某个时候全部重新加载所有的配置文件信息。


在整个项目中的应用点：
  如果是web应用，web应用在启动的时候会自动加载，应用中实现了ServletContextListener接口的类，并调用contextInitialized方法。
  在该方法中，应该首先去System.getProperty读取关于应用的缺省配置文件的路径。如果没有，则自己生成这个路径并写入系统配置System.getProperty
  应用从contextInitialized的参数ServletContextEvent中获得ServletContext，从ServletContext中通过getAttribute方法获取根配置文件的路径信息。
  如果没有，在自己根据上面的缺省配置文件路径+根配置文件名，自己生成根配置文件的路径信息
  这个时候，就创建一个LifeCyclePropertiesHolder对象，并加载配置文件。
  并将配置文件加入到PropertiesManager中去。


带完善
  PropertiesHolder类中提供更多的类型转换。

