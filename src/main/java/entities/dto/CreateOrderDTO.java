package entities.dto;


import entities.Customer;
import entities.Thing;

import java.util.List;

public class CreateOrderDTO {

    private Customer customer;
    private List<Thing> things;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }
}
