package com.snolf.manager.cc.model;

import com.snolf.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: CcGoods.java </p>
 * <p>Description 商品信息 </p>
 * @author Wjj
 * @CreateDate 2018/3/19 13:49
 */
public class CcGoods extends BaseEntity{
    private int id;
    /**
     * 商品编号
     */
    private String goodsNum;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品品牌
     */
    private String brand;
    /**
     * 商品图片地址
     */
    private String imgUrl;
    /**
     * 市场价
     */
    private BigDecimal marketPrice;
    /**
     * 门店价
     */
    private BigDecimal shopPrice;
    /**
     * 预警库存
     */
    private Integer warnStock;
    /**
     * 总库存
     */
    private Integer goodsStock;
    /**
     * 单位
     */
    private String goodsUnit;
    /**
     * 是否上架（0：不上架 1：上架）
     */
    private Integer saleStatus;
    /**
     * 分类ID路径
     */
    private String categoryIds;
    /**
     * 总销售量
     */
    private Integer saleNum;
    /**
     * 上架时间
     */
    private Date saleTime;
    /**
     * 状态（0:启用 1:禁用）
     */
    private Integer status;
    /**
     * 描述
     */
    private String description;
	/**
	 * 排序
	 */
	private Integer sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getWarnStock() {
        return warnStock;
    }

    public void setWarnStock(Integer warnStock) {
        this.warnStock = warnStock;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}