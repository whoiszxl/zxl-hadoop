create table zxl_admin_log
(
    id          int(10) auto_increment comment '主键'
        primary key,
    admin_id    bigint(18)                         null comment '用户Id',
    type        tinyint(1)                         null comment '日志类型 1查询 2修改 3新增 4删除 5导出 6审核',
    method      varchar(255)                       null comment '方法',
    params      text                               null comment '参数',
    time        bigint                             null comment '时间',
    ip          varchar(64)                        null comment 'IP地址',
    description varchar(255)                       null comment '描述',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '管理员操作日志表';

