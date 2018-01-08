package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

/**
 * 1.1 商品信息
 */
@Data
public class Product {

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
}
