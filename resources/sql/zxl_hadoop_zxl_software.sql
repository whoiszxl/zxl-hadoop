create table zxl_software
(
    id                 int(10) auto_increment comment '组件主键ID'
        primary key,
    software_name      varchar(300)                         not null comment '组件名',
    software_filename  varchar(300)                         not null comment '组件文件名',
    software_path      varchar(300)                         not null comment '组件文件路径',
    install_path       varchar(300)                         not null comment '组件安装路径',
    env_path           varchar(300)                         not null comment '环境变量路径',
    env_content        varchar(1000)                        not null comment '环境变量内容',
    install_status     tinyint(1) default 1                 null comment '安装状态：1 未安装 2 部分安装 3 全安装',
    install_server_ids varchar(300)                         null comment '安装了此组件的服务ID集合， 逗号分隔 : 1,2,3',
    created_at         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '基础组件表';

INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (1, 'hadoop', 'hadoop-3.1.3.tar.gz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_hadoop_env.sh', '##HADOOP_HOME
export HADOOP_HOME=/opt/zxl-hadoop/module/hadoop-3.1.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin', 3, '[1, 2, 3]', '2021-08-13 15:58:53', '2021-08-17 10:49:01');
INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (2, 'jdk', 'jdk-8u212-linux-x64.tar.gz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_jdk_env.sh', '#JAVA_HOME
export JAVA_HOME=/opt/zxl-hadoop/module/jdk1.8.0_212
export PATH=$PATH:$JAVA_HOME/bin', 3, '[1, 2, 3]', '2021-08-13 17:19:30', '2021-08-16 16:12:11');
INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (3, 'zookeeper', 'apache-zookeeper-3.5.7-bin.tar.gz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_zookeeper_env.sh', '#ZOOKEEPER_HOME
export ZOOKEEPER_HOME=/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin
export PATH=$PATH:$ZOOKEEPER_HOME/bin', 3, '[1, 2, 3]', '2021-08-15 23:28:17', '2021-08-16 16:13:06');
INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (4, 'kafka', 'kafka_2.11-2.4.1.tgz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_kafka_env.sh', '#KAFKA_HOME
export KAFKA_HOME=/opt/zxl-hadoop/module/kafka_2.11-2.4.1
export PATH=$PATH:$KAFKA_HOME/bin', 3, '[1, 2, 3]', '2021-08-16 13:02:08', '2021-08-16 16:13:12');
INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (5, 'flume', 'apache-flume-1.9.0-bin.tar.gz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_flume_env.sh', '#FLUME_HOME
export FLUME_HOME=/opt/zxl-hadoop/module/apache-flume-1.9.0-bin
export PATH=$PATH:$FLUME_HOME/bin', 3, '[1, 2, 3]', '2021-08-17 14:42:46', '2021-08-17 17:50:55');
INSERT INTO `zxl-hadoop`.zxl_software (id, software_name, software_filename, software_path, install_path, env_path, env_content, install_status, install_server_ids, created_at, updated_at) VALUES (6, 'spark', 'spark-3.0.0-bin-hadoop3.2.tgz', '/opt/zxl-hadoop/software/', '/opt/zxl-hadoop/module/', '/etc/profile.d/zxl_spark_env.sh', '#SPARK_HOME
export SPARK_HOME=/opt/zxl-hadoop/module/spark-3.0.0-bin-hadoop3.2
export PATH=$PATH:$SPARK_HOME/bin', 3, '[1, 2, 3]', '2021-08-17 17:50:55', '2021-08-17 18:05:52');