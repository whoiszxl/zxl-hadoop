create table zxl_config
(
    id           int(10) auto_increment comment '配置主键ID'
        primary key,
    config_key   varchar(100)                         not null comment '配置键',
    config_value varchar(1000)                        not null comment '配置值',
    status       tinyint(1) default 1                 null comment '状态：0失效 1有效',
    created_at   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '基础配置表';

INSERT INTO `zxl-hadoop`.zxl_config (id, config_key, config_value, status, created_at, updated_at) VALUES (1, 'software_path', '/opt/zxl-hadoop/software/', 1, '2021-08-12 22:19:30', '2021-08-15 13:39:56');
INSERT INTO `zxl-hadoop`.zxl_config (id, config_key, config_value, status, created_at, updated_at) VALUES (2, 'hosts_path', '/etc/hosts', 1, '2021-08-14 00:19:19', '2021-08-14 19:14:54');
INSERT INTO `zxl-hadoop`.zxl_config (id, config_key, config_value, status, created_at, updated_at) VALUES (3, 'script_path', '/opt/zxl-hadoop/script/', 1, '2021-08-14 13:07:18', '2021-08-14 13:07:31');