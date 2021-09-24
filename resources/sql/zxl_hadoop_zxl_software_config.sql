create table zxl_software_config
(
    id                     int(10) auto_increment comment '组件配置文件主键ID'
        primary key,
    software_name          varchar(300)                         not null comment '组件文件名',
    config_name            varchar(300)                         not null comment '配置文件名',
    config_path            varchar(300)                         not null comment '配置文件路径',
    config_template        text                                 not null comment '配置文件模板',
    config_template_params varchar(2000)                        not null comment '配置文件模板参数，JSON格式保存，example: {"namenode_port":10000}',
    install_status         tinyint(1) default 1                 null comment '安装状态：1 未安装 2 安装成功',
    created_at             datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at             datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '基础组件配置表';

INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (1, 'zookeeper', 'zoo.cfg', '/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin/conf/', '# 最小时间单位，默认2000，tickTime * other
tickTime=${tickTime}

# 默认10，initLimit * tickTime，在这个时间内，leader会最长等待initLimit * tickTime秒follower来同步
initLimit=${initLimit}

# 默认5，也就是5*tickTime，超过这个时间没有心跳就踢掉follower
syncLimit=${syncLimit}

# 存放zk数据快照
dataDir=${dataDir}

# 存放磁盘的事务日志
dataLogDir=${dataLogDir}

# 客户端连接端口
clientPort=${clientPort}

# 客户端最多和服务端连接的请求数
maxClientCnxns=${maxClientCnxns}
', '{
    "tickTime": "2000",
    "initLimit": "10",
    "syncLimit": "5",
    "dataDir": "/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin/zkData",
    "dataLogDir": "/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin/zkDataLog",
    "clientPort": "2181",
    "maxClientCnxns": "60"
}', 1, '2021-08-15 23:21:49', '2021-08-16 14:07:21');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (2, 'kafka', 'server.properties', '/opt/zxl-hadoop/module/kafka_2.11-2.4.1/config/', '# broker的id，每台机器需要唯一。（代码中动态添加）
# broker.id=${broker.id}

# 网络接收请求处理线程数量
num.network.threads=3

# 磁盘IO处理线程数量
num.io.threads=8

# 日志目录
log.dirs=/opt/zxl-hadoop/module/kafka_2.11-2.4.1/logs

# partition的数量
num.partitions=1

# zk集群连接地址
zookeeper.connect=hadoop101:2181,hadoop102:2181,hadoop103:2181/kafka

# 删除topic功能是否开启
delete.topic.enable=true

socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600
num.recovery.threads.per.data.dir=1
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1
log.retention.hours=168
log.segment.bytes=1073741824
log.retention.check.interval.ms=300000
zookeeper.connection.timeout.ms=6000
group.initial.rebalance.delay.ms=0', '{
    "numNetworkThreads": "3",
    "numIoThreads": "8",
    "logDirs": "/opt/zxl-hadoop/module/kafka_2.11-2.4.1/logs",
    "numPartitions": "1",
    "zookeeperConnect": "hadoop101:2181,hadoop102:2181,hadoop103:2181/kafka",
    "deleteTopicEnable": "true"
}', 1, '2021-08-16 13:39:55', '2021-08-16 14:15:37');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (4, 'hadoop', 'core-site.xml', '/opt/zxl-hadoop/module/hadoop-3.1.3/etc/hadoop/', '<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <!-- NameNode的地址 -->
    <property>
        <name>fs.defaultFS</name>
        <value>${fsDefaultFS}</value>
    </property>
    <!-- hadoop数据存储目录 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>${hadoopTmpDir}</value>
    </property>

    <!-- namenode web ui登录用户 -->
    <property>
        <name>hadoop.http.staticuser.user</name>
        <value>${hadoopHttpStaticuserUser}</value>
    </property>

    <!-- 配置zxl用户允许通过代理访问的主机节点 -->
    <property>
        <name>hadoop.proxyuser.${hadoopHttpStaticuserUser}.hosts</name>
        <value>*</value>
    </property>
    <!-- 配置zxl用户允许通过代理用户所属组 -->
    <property>
        <name>hadoop.proxyuser.${hadoopHttpStaticuserUser}.groups</name>
        <value>*</value>
    </property>
    <!-- 配置zxl用户允许通过代理的用户-->
    <property>
        <name>hadoop.proxyuser.${hadoopHttpStaticuserUser}.users</name>
        <value>*</value>
    </property>
</configuration>', '{
    "fsDefaultFS": "hdfs://hadoop101:8020",
    "hadoopTmpDir": "/opt/zxl-hadoop/module/hadoop-3.1.3/data",
    "hadoopHttpStaticuserUser": "zxl"
}', 1, '2021-08-16 16:45:36', '2021-08-17 00:10:52');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (5, 'hadoop', 'hdfs-site.xml', '/opt/zxl-hadoop/module/hadoop-3.1.3/etc/hadoop/', '<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
	<!-- namenode web ui访问的地址 -->
	<property>
        <name>dfs.namenode.http-address</name>
        <value>${dfsNamenodeHttpAddress}</value>
    </property>
    
	<!-- secondary namenode web ui 访问地址 -->
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>${dfsNamenodeSecondaryHttpAddress}</value>
    </property>
    
    <!-- HDFS文件系统每个副本的数量 -->
    <property>
        <name>dfs.replication</name>
        <value>${dfsReplication}</value>
    </property>
</configuration>', '{
    "dfsNamenodeHttpAddress": "hadoop101:9870",
    "dfsNamenodeSecondaryHttpAddress": "hadoop103:9868",
    "dfsReplication": "1"
}', 1, '2021-08-16 16:45:36', '2021-08-17 00:10:52');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (6, 'hadoop', 'yarn-site.xml', '/opt/zxl-hadoop/module/hadoop-3.1.3/etc/hadoop/', '<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
	<!-- MR走shuffle -->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    
    <!-- ResourceManager的地址-->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>${yarnResourcemanagerHostname}</value>
    </property>
    
    <!-- 环境变量的继承 -->
    <property>
        <name>yarn.nodemanager.env-whitelist</name>
        <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
    </property>
    
    <!-- yarn容器最小内存 -->
    <property>
        <name>yarn.scheduler.minimum-allocation-mb</name>
        <value>${yarnSchedulerMinimumAllocationMb}</value>
    </property>

    <!-- yarn容器最大内存 -->
    <property>
        <name>yarn.scheduler.maximum-allocation-mb</name>
        <value>${yarnSchedulerMaximumAllocationMb}</value>
    </property>
    
    <!-- yarn容器允许管理的物理内存大小 -->
    <property>
        <name>yarn.nodemanager.resource.memory-mb</name>
        <value>${yarnNodemanagerResourceMemoryMb}</value>
    </property>
    
    <!-- 关闭yarn对虚拟内存的限制检查 -->
    <property>
        <name>yarn.nodemanager.vmem-check-enabled</name>
        <value>false</value>
    </property>

    <!-- 开启日志聚集功能 -->
    <property>
        <name>yarn.log-aggregation-enable</name>
        <value>true</value>
    </property>

    <!-- 设置日志聚集服务器地址 -->
    <property>  
        <name>yarn.log.server.url</name>  
        <value>${yarnLogServerUrl}</value>
    </property>

    <!-- 设置日志保留时间为7天 -->
    <property>
        <name>yarn.log-aggregation.retain-seconds</name>
        <value>${yarnLogAggregationRetainSeconds}</value>
    </property>
</configuration>', '{
    "yarnResourcemanagerHostname": "hadoop102",
    "yarnSchedulerMinimumAllocationMb": "1024",
    "yarnSchedulerMaximumAllocationMb": "7000",
    "yarnNodemanagerResourceMemoryMb": "7000",
    "yarnLogServerUrl": "http://hadoop101:19888/jobhistory/logs",
    "yarnLogAggregationRetainSeconds": "604800"
}', 1, '2021-08-16 16:45:36', '2021-08-17 00:10:52');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (7, 'hadoop', 'mapred-site.xml', '/opt/zxl-hadoop/module/hadoop-3.1.3/etc/hadoop/', '<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
	<!-- 指定MapReduce程序运行在什么平台上 -->
    <property>
        <name>mapreduce.framework.name</name>
        <value>${mapreduceFrameworkName}</value>
    </property>

    <!-- 历史服务器地址 -->
    <property>
        <name>mapreduce.jobhistory.address</name>
        <value>${mapreduceJobhistoryAddress}</value>
    </property>

    <!-- 历史服务器web地址 -->
    <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>${mapreduceJobhistoryWebappAddress}</value>
    </property>
</configuration>', '{
    "mapreduceFrameworkName": "yarn",
    "mapreduceJobhistoryAddress": "hadoop101:10020",
    "mapreduceJobhistoryWebappAddress": "hadoop101:19888"
}', 1, '2021-08-16 16:45:36', '2021-08-17 00:10:52');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (8, 'hadoop', 'workers', '/opt/zxl-hadoop/module/hadoop-3.1.3/etc/hadoop/', 'hadoop101
hadoop102
hadoop103', ' ', 1, '2021-08-16 16:45:36', '2021-08-17 00:10:52');
INSERT INTO `zxl-hadoop`.zxl_software_config (id, software_name, config_name, config_path, config_template, config_template_params, install_status, created_at, updated_at) VALUES (9, 'flume', 'flume-env.sh', '/opt/zxl-hadoop/module/apache-flume-1.9.0-bin/conf/', 'export JAVA_HOME=${javaHome}', '{
    "javaHome": "/opt/zxl-hadoop/module/jdk1.8.0_212"
}', 1, '2021-08-17 15:00:38', '2021-08-17 15:00:38');