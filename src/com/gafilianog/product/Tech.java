package com.gafilianog.product;

public class Tech extends Product {

    private final String version;

    public Tech(String name, int price, String version) {
        super(name, price);
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
