package com.kat.farmshop.service;

import com.kat.farmshop.model.Apple;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import com.kat.farmshop.model.Wine;
import com.kat.farmshop.repository.StockItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockItemServiceImplTest {

    @Mock
    private StockItemRepository stockItemRepository;

    @InjectMocks
    private StockItemServiceImpl stockItemServiceImpl;

    @Test
    public void shouldReturnItemsCountGivenTheItemsName() {
        when(stockItemRepository.countByName(anyString())).thenReturn(5L);
        long result = stockItemServiceImpl.countByName(StockItemType.APPLE.getCode());
        verify(stockItemRepository).countByName(anyString());
        assertEquals(5L, result);
    }

    @Test
    public void shouldDecreasewineBottlesInStock() {
        StockItem stockItem = StockItem.builder()
                .name(StockItemType.WINE.getCode())
                .price(BigDecimal.valueOf(8))
                .quantity(200)
                .build();
        Wine wineBottle = new Wine(stockItem, LocalDate.now().getYear());
        stockItemServiceImpl.decreaseStock(wineBottle);
        verify(stockItemRepository).updateStockItemQuantity(anyInt(), anyLong());
        verify(stockItemRepository, times(0)).updateStockItemQuantity(anyInt(), any(LocalDate.class));
    }

    @Test
    public void shouldDecreaseAppleBagsInStock() {
        StockItem stockItem = StockItem.builder()
                .name(StockItemType.APPLE.getCode())
                .price(BigDecimal.valueOf(3))
                .quantity(150)
                .build();
        Apple appleBag = new Apple(stockItem, LocalDate.now());
        stockItemServiceImpl.decreaseStock(appleBag);
        verify(stockItemRepository).updateStockItemQuantity(anyInt(), any(LocalDate.class));
        verify(stockItemRepository, times(0)).updateStockItemQuantity(anyInt(), anyLong());
    }
}
