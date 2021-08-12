DROP TABLE IF EXISTS `zxl_server`;
CREATE TABLE `zxl_server` (
    `id`                            int(10) NOT NULL COMMENT '服务器主键ID',
    `server_name`                   varchar(100) NOT NULL COMMENT '服务器host名称',
    `server_outer_ip`               varchar(100) NOT NULL COMMENT '服务器外网ip地址',
    `server_inner_ip`               varchar(100) NOT NULL COMMENT '服务器内网ip地址',
    `server_port`                   varchar(100) NOT NULL COMMENT '服务器端口',
    `server_username`               varchar(100) DEFAULT NULL COMMENT '服务器用户名',
    `server_password`               varchar(100) DEFAULT NULL COMMENT '服务器密码',
    `status`                        tinyint(1) DEFAULT 1 COMMENT '状态：0失效 1有效',
    `created_at`                    datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`                    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务器表';


DROP TABLE IF EXISTS `zxl_config`;
CREATE TABLE `zxl_config` (
    `id`                            int(10) NOT NULL COMMENT '配置主键ID',
    `key`                           varchar(100) NOT NULL COMMENT '配置键',
    `value`                         varchar(1000) NOT NULL COMMENT '配置值',
    `status`                        tinyint(1) DEFAULT 1 COMMENT '状态：0失效 1有效',
    `created_at`                    datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`                    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础配置表';


DROP TABLE IF EXISTS `zxl_software`;
CREATE TABLE `zxl_software` (
    `id`                            int(10) NOT NULL COMMENT '配置主键ID',
    `key`                           varchar(100) NOT NULL COMMENT '配置键',
    `value`                         varchar(1000) NOT NULL COMMENT '配置值',
    `status`                        tinyint(1) DEFAULT 1 COMMENT '状态：0失效 1有效',
    `created_at`                    datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`                    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础配置表';