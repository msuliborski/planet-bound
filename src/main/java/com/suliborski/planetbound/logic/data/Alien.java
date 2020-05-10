package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Alien extends Minion {
    private String type;

    public Alien(String type, int health, int positionX, int positionY) {
        super(health, positionX, positionY);
        this.type = type;
    }
}
