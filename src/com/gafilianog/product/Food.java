package com.gafilianog.product;


public class Food extends Product {

    private final String expDate;

    public Food(String name, int price, String expDate) {
        super(name, price);
        this.expDate = expDate;
    }

    public String getExpDate() {
        return expDate;
    }
}
