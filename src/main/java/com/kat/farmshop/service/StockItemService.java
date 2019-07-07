package com.kat.farmshop.service;

import com.kat.farmshop.model.StockItem;

public interface StockItemService {

    public void decreaseStock(StockItem stockItem);
    public long countByName(String name);
}
