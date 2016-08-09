#Dubbo

### Dubbo简介

Dubbo是一个分布式服务框架,解决了上面的所面对的问题，Dubbo的架构如图所示:

![dubbo结构](http://doc.okbase.net/picture/addon/2014/11/14/A090026309-112508.png)

节点角色说明：
Provider: 暴露服务的服务提供方。
Consumer: 调用远程服务的服务消费方。
Registry: 服务注册与发现的注册中心。
Monitor: 统计服务的调用次调和调用时间的监控中心。
Container: 服务运行容器

调用关系说明：
0. 服务容器负责启动，加载，运行服务提供者。
1. 服务提供者在启动时，向注册中心注册自己提供的服务。
2. 服务消费者在启动时，向注册中心订阅自己所需的服务。
3. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
4. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
5. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心

Provider 向 Registry注册服务 Consumer向Registry订阅服务同时通知Consumer有可用服务,然后Consumer调用Provider的服务!

### Dubbo与Zookeeper、SpringMVC整合使用

####第一步：在Linux上安装Zookeeper
     Zookeeper作为Dubbo服务的注册中心，Dubbo原先基于数据库的注册中心，没采用Zookeeper，Zookeeper一个分布式的服务框架，
     是树型的目录服务的数据存储，能做到集群管理数据 ，这里能很好的作为Dubbo服务的注册中心，Dubbo能与Zookeeper做到集群部署，
     当提供者出现断电等异常停机时，Zookeeper注册中心能自动删除提供者信息，当提供者重启时，能自动恢复注册数据，以及订阅请求。
     我们先在linux上安装Zookeeper，我们安装最简单的单点，集群比较麻烦。
    （1）下载Zookeeper-3.4.6.tar.gz  地址:[下载地址](http://www.apache.org/dist/zookeeper/)
    （2）我们放到Linux下的一个文件夹，然后解压： 
         ```#tar zxvf zookeeper-3.4.6.tar.gz```
    （3）然后在对应的zookeeper-3.4.6/conf 下有一个文件zoo_sample.cfg的这个文件里面配置了监听客户端连接的端口等一些信息，
        Zookeeper 在启动时会找zoo.cfg这个文件作为默认配置文件,所以我们复制一个名称为zoo.cfg的文件，
  
     说明：
     clientPort：监听客户端连接的端口。
     tickTime：基本事件单元，以毫秒为单位。它用来控制心跳和超时，默认情况下最小的会话超时时间为两倍的 tickTime。
     我们可以对配置文件的端口等或者进行高级配置和集群配置例如：maxClientCnxns：限制连接到 ZooKeeper 的客户端的数量等
     (4)启动Zookeeper 的服务，zkServer.sh
     
     到这边Zookeeper的安装和配置完成
    
####第二步：配置dubbo-admin的管理页面，方便我们管理页面

    (1)下载dubbo-admin-2.4.1.war包，在Linux的tomcat部署，先把dubbo-admin-2.4.1放在tomcat的webapps/ROOT下，然后进行解压：
        #jar -xvf dubbo-admin-2.4.1.war
    (2)然后到webapps/ROOT/WEB-INF下，有一个dubbo.properties文件，里面指向Zookeeper ，使用的是Zookeeper 的注册中心，如图所示：
        
    (3)然后启动tomcat服务，用户名和密码：root,并访问服务，显示登陆页面，说明dubbo-