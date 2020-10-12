package entities;

import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private Size size;

    public Customer(){

    }

    public Customer(String firstName, String lastName, Size size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.size = size;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", size=" + size +
                '}';
    }
}
