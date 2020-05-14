package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.data.*;
import lombok.Data;

@Data
public class GalaxyData {

    Ship ship;

    Planet planet;
    Event event;

    Expedition expedition;

    private int artifactsNeeded = 5;

    public void preparePlanet() {
        double r = Math.floor(Math.random() * 4 + 1);
        if (r == 1) planet = new Planet("red");
        else if (r == 2) planet = new Planet("green");
        else if (r == 3) planet = new Planet("blue");
        else planet = new Planet("black");
        expedition = new Expedition();
    }


    public void prepareEvent() {
        double r = Math.random();
        if (r < 0.125) {
            event = null;
        } else {
            r = Math.floor(Math.random() * 6 + 1);
            if (r == 1) event = new Event("crew-death");
            else if (r == 2) event = new Event("salvage-ship");
            else if (r == 3) event = new Event("cargo-loss");
            else if (r == 4) event = new Event("fuel-loss");
            else if (r == 5) event = new Event("no-event");
            else event = new Event("crew-rescue");
        }
    }

    public void saveGame(){
        // to be implemented...
    }

    public void loadGame(){
        // to be implemented...
    }
}
