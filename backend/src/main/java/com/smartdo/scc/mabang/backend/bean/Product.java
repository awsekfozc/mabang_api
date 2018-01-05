package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 商品实体
 */
@Data
public class Product {
    //商品编号
    private String stockId;
    private String stockSku;
    private String originalSku;
    private String nameCN;
    private String nameEN;
    private String stockQuantity;
    private String status;
    private String parentCategoryId;
    private String categoryId;
    private String stockPicture;
    private String remark;
    private String timeCreated;
    private String updateTime;
    private String isMachining;
    private String retailId;
    private String salePrice;
    private String defaultCost;
    private String waitingQuantity;
    private String shippingQuantity;
    private String isNewType;
    private String livenessType;
    private String declareName;
    private String declareEname;
    private String declareValue;
    private String weight;
    private String declareCode;
    private String hasBattery;
    private String isTort;
    private String magnetic;
    private String noLiquidCosmetic;
    private String powder;
    private String length;
    private String width;
    private String height;
    private String volume;
    private String developerId;
    private String salesId;
    private String packageId;
    private String packageCount;
    private String quantityInterval7;
    private String quantityInterval28;
    private String quantityInterval42;

    public Product() {
    }

    public Product(String stockId, String stockSku, String originalSku, String nameCN, String nameEN, String stockQuantity, String status, String parentCategoryId, String categoryId, String stockPicture, String remark, String timeCreated, String updateTime, String isMachining, String retailId, String salePrice, String defaultCost, String waitingQuantity, String shippingQuantity, String isNewType, String livenessType, String declareName, String declareEname, String declareValue, String weight, String declareCode, String hasBattery, String isTort, String magnetic, String noLiquidCosmetic, String powder, String length, String width, String height, String volume, String developerId, String salesId, String packageId, String packageCount, String quantityInterval7, String quantityInterval28, String quantityInterval42) {
        this.stockId = stockId;
        this.stockSku = stockSku;
        this.originalSku = originalSku;
        this.nameCN = nameCN;
        this.nameEN = nameEN;
        this.stockQuantity = stockQuantity;
        this.status = status;
        this.parentCategoryId = parentCategoryId;
        this.categoryId = categoryId;
        this.stockPicture = stockPicture;
        this.remark = remark;
        this.timeCreated = timeCreated;
        this.updateTime = updateTime;
        this.isMachining = isMachining;
        this.retailId = retailId;
        this.salePrice = salePrice;
        this.defaultCost = defaultCost;
        this.waitingQuantity = waitingQuantity;
        this.shippingQuantity = shippingQuantity;
        this.isNewType = isNewType;
        this.livenessType = livenessType;
        this.declareName = declareName;
        this.declareEname = declareEname;
        this.declareValue = declareValue;
        this.weight = weight;
        this.declareCode = declareCode;
        this.hasBattery = hasBattery;
        this.isTort = isTort;
        this.magnetic = magnetic;
        this.noLiquidCosmetic = noLiquidCosmetic;
        this.powder = powder;
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = volume;
        this.developerId = developerId;
        this.salesId = salesId;
        this.packageId = packageId;
        this.packageCount = packageCount;
        this.quantityInterval7 = quantityInterval7;
        this.quantityInterval28 = quantityInterval28;
        this.quantityInterval42 = quantityInterval42;
    }
}
