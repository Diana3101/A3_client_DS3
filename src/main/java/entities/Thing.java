package entities;


import java.util.UUID;

public class Thing {
    private UUID id;
    private String name;
    private Size size;
    private ConditionOfThing condition;
    private double price;
    private Seller addedBy;

    public Thing() {

    }

    public Thing(String name, Size size, ConditionOfThing condition, double price, Seller addedBy) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.size = size;
        this.condition = condition;
        this.price = price;
        this.addedBy = addedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ConditionOfThing getCondition() {
        return condition;
    }

    public void setCondition(ConditionOfThing condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seller getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Seller addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "Thing{" +
                ", name=" + name +
                ", size=" + size +
                ", condition=" + condition +
                ", price=" + price +
                ", addedBy=" + addedBy +
                '}';
    }
}
