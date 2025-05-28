package models;

public class Product {
    private int id;
    private String productCode;
    private String productName;
    private double unitPrice;
    private String imageLink;

    public Product(int id, String productCode, String productName, double unitPrice, String imageLink) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.imageLink = imageLink;
    }

    // Getter and Setter
}
