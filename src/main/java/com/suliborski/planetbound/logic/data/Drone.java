package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Drone extends Minion {
    private String cargoType;
    private boolean isCargoLoaded;
    private boolean backWithCargo;

    public Drone(int health, int positionX, int positionY) {
        super(health, positionX, positionY);
        isCargoLoaded = false;
        backWithCargo = false;
    }
}
