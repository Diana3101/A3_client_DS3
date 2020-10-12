package entities.dto;

import entities.Thing;

import java.util.List;

public class ThingsDTO {

    private List<Thing> things;

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "ThingsDTO{" +
                "things=" + things +
                '}';
    }
}
