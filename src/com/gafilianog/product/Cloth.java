package com.gafilianog.product;


public class Cloth extends Product {

    private final String size;

    public Cloth(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
