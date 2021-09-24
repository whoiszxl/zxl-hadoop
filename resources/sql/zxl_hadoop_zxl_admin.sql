create table zxl_admin
(
    id            int(10) auto_increment comment '主键'
        primary key,
    username      varchar(32)                           not null comment '账号',
    password      varchar(256)                          not null comment '密码',
    fullname      varchar(32)                           null comment '姓名',
    mobile        varchar(16)                           null comment '手机号',
    email         varchar(128)                          null comment '邮箱',
    google_code   varchar(32) default ''                null comment '谷歌验证码',
    google_status int(1)      default 0                 null comment '谷歌验证码是否开启，默认不开启 0-不开启； 1-开启',
    status        tinyint(1)  default 1                 not null comment '状态 0-无效； 1-有效；',
    created_at    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at    datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '管理员表';

INSERT INTO `zxl-hadoop`.zxl_admin (id, username, password, fullname, mobile, email, google_code, google_status, status, created_at, updated_at) VALUES (1, 'admin', 'admin', 'whoiszxl', '11178786969', 'whoiszxl@gmail.com', '', 0, 1, '2021-08-13 14:30:57', '2021-08-13 14:30:57');