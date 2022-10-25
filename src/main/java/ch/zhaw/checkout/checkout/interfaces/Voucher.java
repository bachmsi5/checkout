package ch.zhaw.checkout.checkout.interfaces;

import java.util.List;

import ch.zhaw.checkout.checkout.model.Product;

public interface Voucher {
    public double getDiscount(List<Product> products);
}
