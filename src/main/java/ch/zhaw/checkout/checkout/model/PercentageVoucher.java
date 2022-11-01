package ch.zhaw.checkout.checkout.model;

import java.util.List;

import ch.zhaw.checkout.checkout.interfaces.Voucher;

public class PercentageVoucher implements Voucher {

    private int percentage;

    public PercentageVoucher(int percentage) {
        if(percentage > 50)
            throw new RuntimeException("Error: Discount value must less or equal 50.");
        if(percentage <= 0)
            throw new RuntimeException("Error: Discount value must be greater than zero.");
        this.percentage = percentage;
    }

    @Override
    public double getDiscount(List<Product> products) {
        if(products.isEmpty())
            return 0.0;
        double sum = 0.0;
        for(Product p : products)
            sum += p.getPrice();
        return percentage * (sum / 100);
    }
    
}
