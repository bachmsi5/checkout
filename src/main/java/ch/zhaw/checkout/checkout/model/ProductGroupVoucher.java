package ch.zhaw.checkout.checkout.model;

import java.util.List;

import ch.zhaw.checkout.checkout.interfaces.Voucher;

public class ProductGroupVoucher implements Voucher {

    private int discount;
    private String productGroup;
    
    public ProductGroupVoucher(int discount, String productGroup) {
        if(discount <= 0)
            throw new RuntimeException("Error: Discount value must be greater than zero.");
        if(productGroup == null || productGroup.isBlank())
            throw new RuntimeException("Error: productGroup cannot be empty or null");
        this.discount = discount;
        this.productGroup = productGroup;
    }

    @Override
    public double getDiscount(List<Product> products) {
        if(products.isEmpty())
            return 0.0;
        double sumDiscount = 0.0;
        double sumAll = 0.0;
        for(Product p : products) {
            if(p.getProductGroup().equals(productGroup)) {
                sumDiscount = this.discount;
                sumAll += p.getPrice();
            }
        }
        if(sumAll < sumDiscount)
            sumDiscount = sumAll;
        return sumDiscount;
    }

}
