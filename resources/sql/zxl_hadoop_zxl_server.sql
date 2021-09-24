create table zxl_server
(
    id              int(10) auto_increment comment '服务器主键ID'
        primary key,
    server_name     varchar(100)                         not null comment '服务器host名称',
    server_outer_ip varchar(100)                         not null comment '服务器外网ip地址',
    server_inner_ip varchar(100)                         not null comment '服务器内网ip地址',
    server_port     varchar(100)                         not null comment '服务器端口',
    server_username varchar(100)                         null comment '服务器用户名',
    server_password varchar(100)                         null comment '服务器密码',
    status          tinyint(1) default 1                 null comment '状态：0失效 1有效',
    created_at      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '服务器表';

INSERT INTO `zxl-hadoop`.zxl_server (id, server_name, server_outer_ip, server_inner_ip, server_port, server_username, server_password, status, created_at, updated_at) VALUES (1, 'hadoop101', '120.24.237.95', '172.18.15.71', '22', 'root', 'WANjiu1020', 1, '2021-08-12 22:18:17', '2021-08-12 22:18:17');
INSERT INTO `zxl-hadoop`.zxl_server (id, server_name, server_outer_ip, server_inner_ip, server_port, server_username, server_password, status, created_at, updated_at) VALUES (2, 'hadoop102', '120.24.160.213', '172.18.15.72', '22', 'root', 'WANjiu1020', 1, '2021-08-12 22:19:03', '2021-08-12 22:19:03');
INSERT INTO `zxl-hadoop`.zxl_server (id, server_name, server_outer_ip, server_inner_ip, server_port, server_username, server_password, status, created_at, updated_at) VALUES (3, 'hadoop103', '120.24.239.23', '172.18.15.73', '22', 'root', 'WANjiu1020', 1, '2021-08-12 22:19:03', '2021-08-12 22:19:03');