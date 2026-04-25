package com.Haile_Peter_Abdisa.dto;

public class ProductResponse {

    private Long   id;
    private String name;
    private double price;
    private int    stockQty;
    private String category;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, double price,
                           int stockQty, String category) {
        this.id       = id;
        this.name     = name;
        this.price    = price;
        this.stockQty = stockQty;
        this.category = category;
    }

    // Getters
    public Long   getId()       { return id; }
    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public int    getStockQty() { return stockQty; }
    public String getCategory() { return category; }
}
