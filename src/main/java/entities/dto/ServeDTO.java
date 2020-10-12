package entities.dto;


import entities.Seller;
import entities.Thing;

import java.util.List;

public class ServeDTO {

    private List<Thing> things;
    private List<Integer> thingQuantities;
    private Seller seller;

    public ServeDTO() {
    }

    public ServeDTO(List<Thing> things, List<Integer> thingQuantities, Seller seller) {
        this.things = things;
        this.thingQuantities = thingQuantities;
        this.seller = seller;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThing(List<Thing> things) {
        this.things = things;
    }

    public List<Integer> getThingQuantities() {
        return thingQuantities;
    }

    public void setThingQuantities(List<Integer> thingQuantities) {
        this.thingQuantities = thingQuantities;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "ServeDTO{" +
                "things=" + things +
                ", thingQuantities=" + thingQuantities +
                ", seller=" + seller +
                '}';
    }
}
