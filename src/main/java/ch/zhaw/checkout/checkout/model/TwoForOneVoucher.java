package ch.zhaw.checkout.checkout.model;

import java.util.List;

import ch.zhaw.checkout.checkout.interfaces.Voucher;

public class TwoForOneVoucher implements Voucher {
    
    private Product product;

    public TwoForOneVoucher(Product product) {
        this.product = product;
    }

    @Override
    public double getDiscount(List<Product> products) {
        int count = 0;
        for (Product p : products)
            if (p.equals(product))
                count++;
        return product.getPrice() * (count / 2);
    }
    
}
