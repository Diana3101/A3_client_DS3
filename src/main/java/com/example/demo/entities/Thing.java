package com.example.demo.entities;


import java.util.UUID;

public class Thing {
    private UUID id;
    private String name;
    private String size;
    private String condition;
    private double price;

    public Thing() {

    }

    public Thing(String name, String size, String condition, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.size = size;
        this.condition = condition;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", condition=" + condition +
                ", price=" + price +
                '}';
    }
}
