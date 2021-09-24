create table zxl_script
(
    id             int(10) auto_increment comment '脚本文件主键ID'
        primary key,
    script_name    varchar(300)                         not null comment '脚本名称',
    script_path    varchar(300)                         not null comment '脚本路径',
    script_content text                                 not null comment '脚本内容',
    script_desc    varchar(300)                         not null comment '脚本描述',
    status         tinyint(1) default 1                 null comment '状态：1 未同步 2 已同步',
    created_at     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment 'SH脚本表';

INSERT INTO `zxl-hadoop`.zxl_script (id, script_name, script_path, script_content, script_desc, status, created_at, updated_at) VALUES (1, 'ssh_no_pass_login.sh', '/opt/zxl-hadoop/script/', '# 安装自动化交互套件
yum -y install expect

# 删除当前已生成的ssh文件
rm -rf ~/.ssh/{known_hosts,id_rsa*}

# 生成新的ssh文件
ssh-keygen -t rsa -P "" -f ~/.ssh/id_rsa

# 遍历传入的机器分别进行连通
for host in $1
do
expect << EOF
spawn ssh-copy-id $host
expect "Are you sure you want to continue connecting (yes/no)?" {send "yes\\r"}
expect "password:" {send "$2\\r"}
expect "#" {send "exit\\r"}
EOF
done', 'ssh免密登录配置，需传入服务器列表和密码。', 1, '2021-08-14 11:30:01', '2021-08-14 12:30:18');
INSERT INTO `zxl-hadoop`.zxl_script (id, script_name, script_path, script_content, script_desc, status, created_at, updated_at) VALUES (2, 'xsync.sh', '/opt/zxl-hadoop/script/', '#!/bin/bash
#1. 判断参数个数
if [ $# -lt 2 ]
then
  echo Not Enough Arguement!
  exit;
fi
#2. 遍历从参数传入的机器
for host in $1
do
  #3. 遍历除第一个以外的目录参数
  for file in ${@:2}
  do
    #4. 判断文件是否存在
    if [ -e $file ]
    then
      #5. 获取父目录
      pdir=$(cd -P $(dirname $file); pwd)
      #6. 获取当前文件的名称
      fname=$(basename $file)
      ssh $host "mkdir -p $pdir"
      rsync -av $pdir/$fname $host:$pdir
    else
      echo $file does not exists!
    fi
  done
done', 'xsync同步文件工具，同步指定的文件夹或文件到指定的机器里', 1, '2021-08-14 15:28:39', '2021-08-14 15:28:39');