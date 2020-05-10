package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanetBoard {
    private Alien alien;
    private Drone drone;

    void move(Minion m, int x, int y){

    }
}
