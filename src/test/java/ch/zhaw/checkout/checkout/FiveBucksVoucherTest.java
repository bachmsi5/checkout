package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ch.zhaw.checkout.checkout.model.FiveBucksVoucher;
import ch.zhaw.checkout.checkout.model.Product;

class FiveBucksVoucherTest {

    @Test
    public void testEmpty() {
        ArrayList<Product> products = new ArrayList<>();
        FiveBucksVoucher voucher = new FiveBucksVoucher();
        double disc = voucher.getDiscount(products);
        assertEquals(0.0, disc);
        assertNotEquals(5.0, disc);
    }

    @Test
    public void testTen() {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Name 1", "Gruppe 1", 10.0);
        products.add(prod1);
        FiveBucksVoucher voucher = new FiveBucksVoucher();
        double disc = voucher.getDiscount(products);
        assertEquals(5.0, disc);
        assertNotEquals(0.0, disc);
    }

    @Test
    public void testTwenty() {
        ArrayList<Product> products = new ArrayList<>();
        Product prod1 = new Product("1", "Name 1", "Gruppe 1", 10.0);
        products.add(prod1);
        Product prod2 = new Product("2", "Name 2", "Gruppe 2", 10.0);
        products.add(prod2);
        FiveBucksVoucher voucher = new FiveBucksVoucher();
        double disc = voucher.getDiscount(products);
        assertEquals(5.0, disc);
        assertNotEquals(0.0, disc);
    }
    
}
