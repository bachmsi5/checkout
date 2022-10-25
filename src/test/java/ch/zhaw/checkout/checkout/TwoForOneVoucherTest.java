package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import ch.zhaw.checkout.checkout.model.Product;
import ch.zhaw.checkout.checkout.model.TwoForOneVoucher;

public class TwoForOneVoucherTest {
    
    @Test
    public void testVoucherTwoProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Tennis Ball (10x)", "Sports", 42.0);
        Product prod2 = new Product("2", "Tennis Racket", "Sports", 77.0);
        products.add(prod1);
        products.add(prod2);
        TwoForOneVoucher voucher = new TwoForOneVoucher(prod1);
        double disc = voucher.getDiscount(products);
        assertEquals(0.0, disc);
        assertNotEquals(1.0, disc);
    }

    @ParameterizedTest
    @CsvSource(value = { "0,0", "1,0", "2,1", "3,1", "4,2" })
    public void testVoucherWithDiscount(ArgumentsAccessor accessor) {
        ArrayList<Product> products = new ArrayList<>();
        int countSame = accessor.getInteger(0);
        int countFree = accessor.getInteger(1);
        double price = 77.0;
        Product newProduct = new Product("1", "Toilet paper premium", "Toiletries", price);
        for(int i = 0; i < countSame; i++) {
            products.add(newProduct);
        }
        TwoForOneVoucher voucher = new TwoForOneVoucher(newProduct);
        double disc = voucher.getDiscount(products);
        assertEquals(countFree * price, disc);
    }
}
