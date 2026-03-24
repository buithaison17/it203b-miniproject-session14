package models;

public class Product {
    private int productId;
    private String productName;
    private int quanity;
    private double stock;

    public Product(int productId, String productName, int quanity, double stock) {
        this.productId = productId;
        this.productName = productName;
        this.quanity = quanity;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
