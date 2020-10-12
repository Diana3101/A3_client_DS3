package entities;

import java.util.UUID;

public class Storage {
    private UUID id;
    private Thing thing;
    private int quantity;

    public Storage() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", thing=" + thing +
                ", quantity=" + quantity +
                '}';
    }
}
