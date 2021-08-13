package com.whoiszxl.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 服务器表
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zxl_server")
@ApiModel(value="Server对象", description="服务器表")
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务器主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "服务器host名称")
    private String serverName;

    @ApiModelProperty(value = "服务器外网ip地址")
    private String serverOuterIp;

    @ApiModelProperty(value = "服务器内网ip地址")
    private String serverInnerIp;

    @ApiModelProperty(value = "服务器端口")
    private String serverPort;

    @ApiModelProperty(value = "服务器用户名")
    private String serverUsername;

    @ApiModelProperty(value = "服务器密码")
    private String serverPassword;

    @ApiModelProperty(value = "状态：0失效 1有效")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}
