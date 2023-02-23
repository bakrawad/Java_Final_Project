package com.example.ph23;

public class Item implements Comparable {
    private String type;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void update(int qtyIncrease){
        setQuantity(qtyIncrease+getQuantity());
    }
    public void update(double adjustmentFactor){
        setPrice(adjustmentFactor+getPrice());
    }

    @Override
    public int compareTo(Object o) {
            return (int)(this.price-((Item) o).price);

    }
}
