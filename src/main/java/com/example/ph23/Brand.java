package com.example.ph23;

public class Brand extends Item {
    private String brand;

    public Brand(){

    }
    public Brand(String type, String brand) {
        super(type);
        this.brand = brand;
    }

    public String getBrand(){
        return brand;
    }




}
