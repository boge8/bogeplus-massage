package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 技师对客户评价表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_order_massagist_review")
@ApiModel(value = "OrderMassagistReview对象", description = "技师对客户评价表")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMassagistReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评价ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单编号")
    private Long orderId;

    @ApiModelProperty("劳累部位(使用数字来来定义；	0:腰部)")
    private Byte tiredBodyPart;

    @ApiModelProperty("受力(1:表示受力大；0:表示受力小)")
    private Byte forceLevel;

    @ApiModelProperty("地址类型(0：酒店，1：住宅）")
    private Byte addressType;

    @ApiModelProperty("客户性别(男:1；女:0)")
    private Byte customerGender;

    @ApiModelProperty("目测年龄")
    private Integer estimatedAge;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("评价时间")
    private LocalDateTime evaluationTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    private String createUser;

    @ApiModelProperty("更新者")
    private String updateUser;

    @ApiModelProperty("逻辑删除 0未删除：1已删除")
    private Integer isDeleted;


}
