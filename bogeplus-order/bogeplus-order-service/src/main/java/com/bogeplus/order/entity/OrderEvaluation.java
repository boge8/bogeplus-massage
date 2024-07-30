package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户对技师评价表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_order_evaluation")
@ApiModel(value = "OrderEvaluation对象", description = "用户对技师评价表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("评分")
    private BigDecimal rating;

    @ApiModelProperty("评语")
    private String reviews;

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
