package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.checkout.checkout.model.PercentageVoucher;
import ch.zhaw.checkout.checkout.model.Product;

public class PercentageVoucherTest {
    
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 5, 20, 49, 50 })
    public void testVoucherSingleProduct(int percentage) {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Tennis Ball", "Sports", 10.0);
        products.add(prod1);
        PercentageVoucher voucher = new PercentageVoucher(percentage);
        double disc = voucher.getDiscount(products);
        assertEquals(
            (prod1.getPrice() / 100) * percentage, 
            disc);
    }

    @Test
    public void testVoucherTwoProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Tennis Ball (10x)", "Sports", 42.0);
        Product prod2 = new Product("2", "Tennis Racket", "Sports", 77.0);
        products.add(prod1);
        products.add(prod2);
        PercentageVoucher voucher = new PercentageVoucher(42);
        double disc = voucher.getDiscount(products);
        assertEquals(
            ((prod1.getPrice() + prod2.getPrice()) / 100) * 42, 
            disc);
    }
}
