package com.kat.farmshop.model;

public enum StockItemType {

    APPLE("APPLE"),WINE("WINE");

    private String code;

    StockItemType (String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
