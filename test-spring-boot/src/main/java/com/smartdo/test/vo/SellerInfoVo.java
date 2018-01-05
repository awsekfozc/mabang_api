package com.smartdo.test.vo;

import lombok.Data;

//采用Data注解， 为类提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@Data
public class SellerInfoVo {

    private Long id;
    private String sellerName;
    private String sellerId;
    private String awsAccessKeyId;
    private String secretKey;
    private short status;
    private String marketplaceId;

}
