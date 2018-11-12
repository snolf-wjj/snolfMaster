package com.snolf.manager.cc.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * <p>Title: CcOrders.java </p>
 * <p>Description 订单信息 </p>
 * @author Wjj
 * @CreateDate 2018/3/19 15:41
 */ 
public class CcOrders {
    private String id;
    /**
     * 订单号
     */
    private String orderNum;

    private String userId;
    /**
     * 订单状态（0：未付款的订单 1：待发货 2：配送中 3：用户确认收货 4：用户拒收 5：用户取消）
     */
    private Integer orderStatus;
    /**
     * 商品总金额
     */
    private BigDecimal goodsMoney;
    /**
     * 收货方式（0：自提1：包邮 2：不包邮）
     */
    private Integer deliverType;
    /**
     * 运费
     */
    private BigDecimal deliverMoney;
    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;
    /**
     * 实际订单总金额
     */
    private BigDecimal realTotalMoney;
    /**
     * 支付方式（0:货到付款 1:在线支付）
     */
    private Integer payType;
    /**
     * 支付来源（1:支付宝，2：微信 3：银行卡）
     */
    private Integer payFrom;
    /**
     * 支付状态（0:未支付 1:已支付）
     */
    private Integer payStatus;
    /**
     * 收货人名称
     */
    private String receiverName;
    /**
     * 收件人地址
     */
    private String receiverAddress;
    /**
     * 收件人手机
     */
    private String receiverPhone;
    /**
     * 所得积分
     */
    private Integer orderScore;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiveTime;
    /**
     * 快递公司
     */
    private String expressName;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 删除标识（0：正常 1：删除）
     */
    private Integer del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(BigDecimal goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    public BigDecimal getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(BigDecimal deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRealTotalMoney() {
        return realTotalMoney;
    }

    public void setRealTotalMoney(BigDecimal realTotalMoney) {
        this.realTotalMoney = realTotalMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayFrom() {
        return payFrom;
    }

    public void setPayFrom(Integer payFrom) {
        this.payFrom = payFrom;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Integer getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Integer orderScore) {
        this.orderScore = orderScore;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}