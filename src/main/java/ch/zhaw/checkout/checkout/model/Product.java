package ch.zhaw.checkout.checkout.model;

public class Product {
    private String id;
    private String name;
    private String productGroup;
    private double price;

    public Product(String id, String name, String productGroup, double price) {
        this.id = id;
        this.name = name;
        this.productGroup = productGroup;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getProductGroup() {
        return this.productGroup;
    }

    public double getPrice() {
        return this.price;
    }
}
