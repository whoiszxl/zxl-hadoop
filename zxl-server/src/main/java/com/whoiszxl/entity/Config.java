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
 * 基础配置表
 * </p>
 *
 * @author whoiszxl
 * @since 2021-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zxl_config")
@ApiModel(value="Config对象", description="基础配置表")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配置主键ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    @ApiModelProperty(value = "配置键")
    private String key;

    @ApiModelProperty(value = "配置值")
    private String value;

    @ApiModelProperty(value = "状态：0失效 1有效")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}
