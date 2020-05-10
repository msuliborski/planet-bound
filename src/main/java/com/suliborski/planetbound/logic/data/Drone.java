package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Drone extends Minion {
    private String cargoType;
    private boolean isCargoLoaded;

    public Drone(int health, int positionX, int positionY, String cargoType) {
        super(health, positionX, positionY);
        this.cargoType = cargoType;
    }
}