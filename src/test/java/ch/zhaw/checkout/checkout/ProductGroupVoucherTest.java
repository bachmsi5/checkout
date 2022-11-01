package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.checkout.checkout.model.Product;
import ch.zhaw.checkout.checkout.model.ProductGroupVoucher;

public class ProductGroupVoucherTest {
    
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49 })
    public void testVoucherGroupA(int discount) {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = mock(Product.class);
        Product prod2 = mock(Product.class);
        String productGroupToTest = "a";
        when(prod1.getPrice()).thenReturn(72.0);
        when(prod1.getProductGroup()).thenReturn(productGroupToTest);
        products.add(prod1);
        when(prod2.getPrice()).thenReturn(77.0);
        when(prod2.getProductGroup()).thenReturn(productGroupToTest);
        products.add(prod2);
        ProductGroupVoucher voucher = new ProductGroupVoucher(discount, productGroupToTest);
        double disc = voucher.getDiscount(products);
        assertEquals(discount, disc);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -Integer.MAX_VALUE })
    public void testVoucherGroupAWithNegativeDiscounts(int percentage) {
        String productGroupToTest = "a";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ProductGroupVoucher(percentage, productGroupToTest);
        });
        
        String expectedMessage = "Discount value must be greater than zero.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testVoucherEmptyProductGroup() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ProductGroupVoucher(42, "   ");
        });

        String expectedMessage = "productGroup cannot be empty or null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testVoucherNullProductGroup() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ProductGroupVoucher(42, null);
        });

        String expectedMessage = "productGroup cannot be empty or null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource(value = { 
        "p1,5,p1,7,p1,10,10",
        "p1,5,p1,3,p1,10,8",
        "p1,5,p2,7,p1,10,5",
        "p1,12,p1,7,p1,5,5",
        "p1,5,p2,7,p3,10,0"
    })
    public void testVoucherWithDiscount(ArgumentsAccessor accessor) {
        ArrayList<Product> products = new ArrayList<>();
        String productGroup1 = accessor.getString(0);
        double productPrice1 = accessor.getInteger(1);
        String productGroup2 = accessor.getString(2);
        double productPrice2 = accessor.getInteger(3);
        String productGroupVoucher = accessor.getString(4);
        int productGroupDiscount = accessor.getInteger(5);
        int expectedDiscount = accessor.getInteger(6);

        Product prod1 = mock(Product.class);
        Product prod2 = mock(Product.class);
        when(prod1.getProductGroup()).thenReturn(productGroup1);
        when(prod1.getPrice()).thenReturn(productPrice1);
        when(prod2.getProductGroup()).thenReturn(productGroup2);
        when(prod2.getPrice()).thenReturn(productPrice2);
        products.add(prod1);
        products.add(prod2);

        ProductGroupVoucher voucher = new ProductGroupVoucher(productGroupDiscount, productGroupVoucher);
        double calculatedDiscount = voucher.getDiscount(products);
        assertEquals(expectedDiscount, calculatedDiscount);
    }
    
}
