C:\lcy\apache-maven-3.6.3\bin 之前仅仅因为这不是一个maven项目，没有正确的项目结构没有被加载出来

分布式锁：（重复提交，商城高并发抢单） 网络传输出现信号延迟，不稳定，中断 要求： 1、排他性 2、避免死锁 3、高可用 4、可重入 5、公平锁（可选）

实现方式： 1、数据库级别，乐观锁，悲观锁 2、Redis原子操作，setnx跟expire原子操作 （只有key不存在时可以设置值） 3、ZooKeeper互斥排他锁，创建临时有序节点+Watcher机制 4、Redisson分布式锁

分布式锁参考：https://blog.csdn.net/qq_34706514/article/details/120294794

zookeeper:开发一个通用的无单点问题的分布式协调框架 功能（发布订阅/负载均衡/命名服务/分布式协调，通知/集群管理/分布式锁/分布式队列） 作用： 1
统一配置管理（每个子系统需要配置的文件统一放到ZooKeeper的ZNode节点） 2 统一命名服务（通过ZNode统一命名，各子系统通过名字获取到节点上对应资源） 3
分布式锁（通过创建与该共享资源相关的顺序临时节点与动态监听机制，控制多线程对共享资源的并发访问） 4 动态感知节点增加，删除，保证机器武宁县相关节点数据主副本一致性

2小时实战Apache顶级项目-RPC框架Dubbo分布式服务调度
https://www.imooc.com/learn/1096

zookeeper下载与安装
https://blog.csdn.net/wxw1997a/article/details/119998932?spm=1001.2101.3001.6650.8&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-8-119998932-blog-123755417.pc_relevant_antiscanv2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-8-119998932-blog-123755417.pc_relevant_antiscanv2&utm_relevant_index=12

Redisson： 布隆过滤器？？？

redis分布式锁缺陷：1 过期时间设置需要考虑具体业务场景 2 setnx获取分布式锁，多线程存在等待，或者失败情况，不可重入 3 setnx expire之前系统宕机，出现死锁现象

Redisson提供锁：1 一次性锁 2 可重入锁

--mysql 8.0 安装
[Server] A temporary password is generated for root@localhost: hi&U4y6glUeh 下载 mysql-8.0.29-winx64 二进制免安装版 配置环境变量：
%MYSQL%bin 新建一个my.ini文件
[mysql]

# 设置mysql客户端默认字符集

default-character-set=utf8
[mysqld]

# 设置mysql默认端口号

port=3306

# 设置mysql的安装目录

basedir=C:\\mysql

# 允许最大连接数

max_connections=20

# 服务端使用的字符集默认为utf-8

character-set-server=utf8

# 创建新表时将使用的默认存储引擎

default-storage-engine=INNODB

管理员打开cmd进入bin目录 mysqld --initialize --console mysqld install net start mysql mysqld --initialize-insecure

net start mysql为停止服务

--20220719
https://www.zhihu.com/search?type=content&q=docker%E5%AE%89%E8%A3%85Jenkins
linux maven jenkins安装 拉取镜像 docker pull jenkinsci/blueocean:latest

启动容器 docker run -u root  \
-d \
-p 9999:8080  \
-p 50000:50000  \
-v /var/jenkins_home:/var/jenkins_home \
-v /var/run/docker.sock -v /app/maven/apache-maven-3.8.6:/app/maven/apache-maven-3.8.6 \
-v /root/.m2:/root/.m2 \
--name dp-jenkins \
jenkinsci/blueocean

docker run -d -p 10240:8080 -p 10241:50000 --restart always -v /data/jenkins:/var/ -v /etc/localtime:/etc/localtime
--name jenkins jenkins/jenkins:lts //todo 本地镜像运行Jenkins docker run -d -p 10240:8080 -p 10241:50000 -v
/mysoftware/jenkins_mount:/var/jenkins_home -v /mysoftware/maven3.8/apache-maven-3.8.6:/usr/local/maven -v
/etc/localtime:/etc/localtime --name myjenkins docker.io/jenkinsci/blueocean

chown -R 1000:1000 /mysoftware/jenkins_mount

docker run -d -p 10240:8080 -p 10241:50000 -v /mysoftware/jenkins_mount:/var/jenkins_home -v
/mysoftware/maven3.8/apache-maven-3.8.6:/usr/local/maven -v /etc/localtime:/etc/localtime --name myjenkins66
6aa7e6ae5876

# 先不挂载#

docker run -d -p 10240:8080 -p 10241:50000 -v /etc/localtime:/etc/localtime --name myjenkins 6aa7e6ae5876
password：5494f7edcff44e9f85e5dcca293343a3

cd /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.332.b09-1.el7_9.x86_64/jre/bin/ /mysoftware/maven3.8/apache-maven-3.8.6
docker内部Jenkins工作目录： /var/jenkins_home/workspace docker cp 本地文件路径 容器ID/容器NAME:容器内路径

| Default: ${user.home}/.m2/repository
<localRepository>/path/to/local/repo</localRepository>

插入网线后，无线网分配的ip地址会有变化

查看mybatis-plus执行 ：MybatisMapperMethod.SqlCommand方法

mvn clean install -DskipTests 跳过测试

vmvare虚机重启后会重新开启一个虚拟ip地址

构造一个调用api的程序和实际场景的应用的程序是不一样的，实际应用会考虑更多安全，性能，可扩展，关联关系，复用 设计方面因素


# util.md 内容
链接: https://pan.baidu.com/s/1J34PLr0i2F7Nt3_aScdlrA  提取码: kydn Linux内核及各发行版镜像：

链接：https://pan.baidu.com/s/1kyD2dcMOdDAYf5tmBEtn9w 提取码：2h9v
