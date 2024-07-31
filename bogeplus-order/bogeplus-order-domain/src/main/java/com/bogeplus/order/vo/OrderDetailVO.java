package com.bogeplus.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "订单详情视图对象")
public class OrderDetailVO {
    @ApiModelProperty(value = "订单状态码,1-待支付，2-支付超时，3-待接单，4-已接单，5-技师出发，6-技师到达，7-开始服务，8-服务完成，9-用户评价，10-售后中，11-售后结束，12-订单已关闭，13-订单已取消", example = "1")
    private Integer status; // 订单状态码,1-待支付，2-支付超时，3-待接单，4-已接单，5-技师出发，6-技师到达，7-开始服务，8-服务完成，9-用户评价，10-售后中，11-售后结束，12-订单已关闭，13-订单已取消
    @ApiModelProperty(value = "技师预计收入", example = "100.00")
    private BigDecimal expectedIncome; // 技师预计收入
    @ApiModelProperty(value = "客户预约时间", example = "2024-08-01T12:00:00")
    private LocalDateTime reservationTime; // 客户预约时间
    @ApiModelProperty(value = "接单时间", example = "2024-08-01T12:10:00")
    private LocalDateTime takeTime; // 接单时间
    @ApiModelProperty(value = "技师出发时间", example = "2024-08-01T12:20:00")
    private LocalDateTime departureTime; // 技师出发时间
    @ApiModelProperty(value = "技师到达时间", example = "2024-08-01T12:30:00")
    private LocalDateTime arrivalTime; // 技师到达时间
    @ApiModelProperty(value = "服务开始时间", example = "2024-08-01T12:40:00")
    private LocalDateTime serviceStartTime; // 服务开始时间
    @ApiModelProperty(value = "服务结束时间", example = "2024-08-01T13:40:00")
    private LocalDateTime serviceEndTime; // 服务结束时间

    @ApiModelProperty(value = "订单ID", example = "123456")
    private Long orderId; // 订单ID
    @ApiModelProperty(value = "订单编号", example = "ORDER20240801")
    private String orderNum; // 订单编号
    @ApiModelProperty(value = "支付方式", example = "WeChat")
    private String payType; // 支付方式

    @ApiModelProperty(value = "技师ID", example = "7890")
    private Long massagistId; // 技师ID
    @ApiModelProperty(value = "订单项目列表")
    // 订单项目列表VO
    private List<OrderItemVO> orderItems;
    @ApiModelProperty(value = "出行方式", example = "Car")
    private String travelMode; // 出行方式
    @ApiModelProperty(value = "出行距离", example = "10.5")
    private BigDecimal travelDistance; // 出行距离
    @ApiModelProperty(value = "出行费用", example = "15.00")
    private BigDecimal travelCost; // 出行费用
    @ApiModelProperty(value = "服务时间", example = "2024-08-01T12:00:00")
    //服务时间和预约时间一致
    private LocalDateTime serviceTime; // 服务时间
    @ApiModelProperty(value = "优惠券ID", example = "456")
    private Long couponId; // 优惠券ID
    @ApiModelProperty(value = "总服务时长（毫秒）", example = "3600000")
    private Long totalServiceDuration; // 总服务时长（毫秒）
    @ApiModelProperty(value = "价格汇总", example = "120.00")
    private BigDecimal totalPrice; // 价格汇总

    @ApiModelProperty(value = "用户地址列表")
    // 用户地址列表VO
    private List<UserAddressVO> userAddresses;
    //预约时间，同服务时间

    @ApiModelProperty(value = "用户ID", example = "654321")
    private Long userId; // 用户ID
    @ApiModelProperty(value = "用户地址ID", example = "123")
    private Long addressId; // 用户地址ID
    @ApiModelProperty(value = "联系人姓名", example = "John Doe")
    private String contactName; // 联系人姓名
    @ApiModelProperty(value = "联系电话", example = "12345678901")
    private String contactPhone; // 联系电话
    @ApiModelProperty(value = "服务地址", example = "123 Main St")
    private String address; // 服务地址


    @ApiModelProperty(value = "危险等级", example = "2")
    private Integer dangerLevel; // 危险等级
    @ApiModelProperty(value = "用户往期概况列表")
    // 用户往期概况列表VO
    private List<UserHistoryVO> userHistories;

    @ApiModelProperty(value = "订单状态历史列表")
    // 订单状态历史列表VO
    private List<LocalDateTime> historicalPaymentTimes;

}

// 订单项目VO
@Data
@ApiModel(description = "订单项目视图对象")
class OrderItemVO {
    @ApiModelProperty(value = "项目ID", example = "987")
    private Long id; // 项目ID
    @ApiModelProperty(value = "订单ID", example = "123456")
    private Long orderId; // 订单ID
    @ApiModelProperty(value = "项目名称", example = "Full Body Massage")
    private String name; // 项目名称
    @ApiModelProperty(value = "项目费用", example = "50.00")
    private BigDecimal price; // 项目费用
    @ApiModelProperty(value = "项目数量", example = "1")
    private Integer quantity; // 项目数量
    @ApiModelProperty(value = "项目时长", example = "60")
    private Integer duration; // 项目时长
}

// 用户地址VO
@Data
class UserAddressVO {
    @ApiModelProperty(value = "地址ID", example = "123")
    private Long id; // 地址ID
    @ApiModelProperty(value = "用户ID", example = "654321")
    private Long userId; // 用户ID
    @ApiModelProperty(value = "联系人姓名", example = "John Doe")
    private String contactName; // 联系人姓名
    @ApiModelProperty(value = "联系电话", example = "12345678901")
    private String contactPhone; // 联系电话
    @ApiModelProperty(value = "经纬度", example = "39.915,116.404")
    private String longitudeLatitude; // 经纬度
    @ApiModelProperty(value = "地址", example = "123 Main St")
    private String address; // 地址
    @ApiModelProperty(value = "地址扩展信息", example = "Apt 456")
    private String addressExtra; // 地址扩展信息
    @ApiModelProperty(value = "是否为默认地址", example = "true")
    private Boolean isDefault; // 是否为默认地址
}

@Data
@ApiModel(description = "用户往期概况视图对象")
class UserHistoryVO {
    @ApiModelProperty(value = "客户概况ID", example = "321")
    private Long id; // 客户概况ID
    @ApiModelProperty(value = "客户ID", example = "654321")
    private Long userId; // 客户ID
    @ApiModelProperty(value = "危险等级", example = "2")
    private Integer dangerLevel; // 危险等级
    @ApiModelProperty(value = "行为备注", example = "Frequent no-shows")
    private String remarks; // 行为备注
}

@Data
@ApiModel(description = "订单状态历史视图对象")
class HistoryStatusVO {
    @ApiModelProperty(value = "下单时间", example = "2024-08-01T12:00:00")
    private LocalDateTime orderTime; // 下单时间
    @ApiModelProperty(value = "支付时间", example = "2024-08-01T12:05:00")
    private LocalDateTime payTime; // 支付时间
    @ApiModelProperty(value = "接单时间", example = "2024-08-01T12:10:00")
    private LocalDateTime takeTime; // 接单时间
    @ApiModelProperty(value = "出发时间", example = "2024-08-01T12:20:00")
    private LocalDateTime departureTime; // 出发时间
    @ApiModelProperty(value = "到达时间", example = "2024-08-01T12:30:00")
    private LocalDateTime arrivalTime; // 到达时间
    @ApiModelProperty(value = "服务开始时间", example = "2024-08-01T12:40:00")
    private LocalDateTime serviceStartTime; // 服务开始时间
    @ApiModelProperty(value = "服务结束时间", example = "2024-08-01T13:40:00")
    private LocalDateTime serviceEndTime; // 服务结束时间
}