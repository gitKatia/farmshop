package com.kat.farmshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class BBStockItem extends StockItem implements BestBefore {

	@Column(unique=true)
	private LocalDate bestBeforeDate;
	
	public BBStockItem(StockItem stockItem, LocalDate bestBeforeDate) {
		super(stockItem.getName(), stockItem.getPrice(), stockItem.getQuantity());
		this.bestBeforeDate = bestBeforeDate;
	}		

	@Override
	public LocalDate getBestBeforeDate() {
		return bestBeforeDate;
	}

}
