package com.example.demo.entities;

import java.util.UUID;

public class OrderThing {
    private UUID id;
    private UUID thingId;
    private UUID orderId;

    public OrderThing(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getThingId() {
        return thingId;
    }

    public void setThingId(UUID thingId) {
        this.thingId = thingId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
