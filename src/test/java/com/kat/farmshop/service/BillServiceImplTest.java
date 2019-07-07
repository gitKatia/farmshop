package com.kat.farmshop.service;

import com.kat.farmshop.BillCalculator;
import com.kat.farmshop.model.Apple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceImplTest {

    @Mock
    private StockItemService stockItemService;

    @Mock
    private BillCalculator billCalculator;

    @InjectMocks
    private BillServiceImpl billServiceImpl;

    @Test
    public void shouldReturnCorrectTotal() {
        BigDecimal expectedResult = new BigDecimal("22.30");
        when(billCalculator.calculateTotal(anyBoolean(),any(LocalDate.class), any())).thenReturn(expectedResult);
        BigDecimal actualResult = billServiceImpl.getTotal();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldAddItemToTheBill() {
        Apple apple = BillServiceTestUtil.getApple(3, 100, LocalDate.now());
        billServiceImpl.addItem(apple);
        verify(stockItemService).decreaseStock(any());
        assertTrue(billServiceImpl.getItems().contains(apple));
    }

    @Test
    public void shouldNotAddItemToTheBillIfItemIsNotInStock() {
        Apple apple = BillServiceTestUtil.getApple(3, 0, LocalDate.now());
        billServiceImpl.addItem(apple);
        verify(stockItemService, times(0)).decreaseStock(any());
        assertFalse(billServiceImpl.getItems().contains(apple));
    }

}
