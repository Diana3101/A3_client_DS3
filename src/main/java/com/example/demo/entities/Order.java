package com.example.demo.entities;

import java.util.UUID;


public class Order {
    private UUID id;
    private UUID customerId;

    public Order() {
    }

    public Order(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                '}';
    }
}
