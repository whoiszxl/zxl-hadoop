package com.whoiszxl.entity.template;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * {
 *     "tickTime": 2000,
 *     "initLimit": 10,
 *     "syncLimit": 5,
 *     "dataDir": "/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin/zkData"
 *     "dataLogDir": "/opt/zxl-hadoop/module/apache-zookeeper-3.5.7-bin/zkDataLog",
 *     "clientPort": 2181,
 *     "maxClientCnxns": 60
 * }
 */
@Data
public class ZookeeperTemplateParamsEntity {

    private Integer tickTime;

    private Integer initLimit;

    private Integer syncLimit;

    private String dataDir;

    private String dataLogDir;

    private Integer clientPort;

    private Integer maxClientCnxns;
}
