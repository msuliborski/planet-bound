package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.data.*;
import lombok.Data;

@Data
public class GalaxyData {
    Ship ship;

    Landable landable;
    Event event;

    PlanetBoard planetBoard;

    public void prepareLandable() {
        double r = Math.random();
        if (r > 0.3) {
            r = Math.floor(Math.random() * 4 + 1);
            if (r == 1) landable = new Planet("red", 1, 0, 1, 0, 0, true, true);
            else if (r == 2) landable = new Planet("green", 1, 1, 0, 0, 0, true, true);
            else if (r == 3) landable = new Planet("blue", 0, 1, 1, 1, 1, true, true);
            else landable = new Planet("black", 0, 0, 1, 1, 0, true, true);
        } else
            landable = new SpaceStation(false);
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
}
