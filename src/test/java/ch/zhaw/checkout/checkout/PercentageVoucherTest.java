package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.zhaw.checkout.checkout.model.PercentageVoucher;
import ch.zhaw.checkout.checkout.model.Product;

public class PercentageVoucherTest {

    // Just a comment to push to git, to check the app rebuild
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 5, 20, 49, 50 })
    public void testVoucherSingleProduct(int percentage) {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Tennis Ball", "Sports", 10.0);
        products.add(prod1);
        if (percentage == 0) {
            Exception exception = assertThrows(RuntimeException.class, () -> {
                new PercentageVoucher(percentage);
            });
            String expectedMessageLtZero = "Discount value must be greater than zero.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessageLtZero));
        } else {
            PercentageVoucher voucher = new PercentageVoucher(percentage);
            double disc = voucher.getDiscount(products);
            assertEquals(
                    (prod1.getPrice() / 100) * percentage,
                    disc);
        }
    }

    @Test
    public void testVoucherTwoProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = mock(Product.class);
        Product prod2 = mock(Product.class);
        when(prod1.getPrice()).thenReturn(42.0);
        when(prod2.getPrice()).thenReturn(77.0);
        products.add(prod1);
        products.add(prod2);
        PercentageVoucher voucher = new PercentageVoucher(42);
        double disc = voucher.getDiscount(products);
        assertEquals(
                ((prod1.getPrice() + prod2.getPrice()) / 100) * 42,
                disc);
    }

    @ParameterizedTest
    @ValueSource(ints = { -10, 0, 51 })
    public void testVoucherException(int percentage) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(percentage);
        });
        String expectedMessageLtZero = "Discount value must be greater than zero.";
        String expectedMessageGtFifty = "Discount value must less or equal 50.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageLtZero) || actualMessage.contains(expectedMessageGtFifty));
    }
}
