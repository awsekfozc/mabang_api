package com.smartdo.scc.mabang.backend.dao;

import org.apache.ibatis.annotations.Delete;

public interface ICommonDao {
    @Delete(" delete from fba_info;")
    int deleteTableFbaInfo();

    @Delete(" delete from order_info;")
    int deleteTableOrderInfo();

    @Delete(" delete from order_item;")
    int deleteTableOrderItem();

    @Delete(" delete from product_purchase_info;")
    int deleteTableProductPurchaseInfo();

    @Delete(" delete from product_purchase_storage_in_info;")
    int deleteTableProductPurchaseStorageInInfo();

    @Delete(" delete from purchase_detail;")
    int deleteTablePurchaseDetail();

    @Delete(" delete from stock_info;")
    int deleteTableStockInfo();

    @Delete(" delete from stock_machining_info;")
    int deleteTableStockMachiningInfo();

    @Delete(" delete from stock_provider_info;")
    int deleteTableStockProviderInfo();

    @Delete(" delete from stock_storage_log;")
    int deleteTableStockStorageLog();

    @Delete(" delete from stock_warehouse_info;")
    int deleteTableStockWarehouseInfo();




}
