package com.springboot.test.data.dto;

public class ProductResponseDto {

    private Long number;
    private String name;
    private int price;
    private int stock;

    public ProductResponseDto() {}

    public ProductResponseDto(Long number, String name, int price, int stock)
    {
        this.name =name;
        this.number = number;
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Long getNumber() {
        return number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
