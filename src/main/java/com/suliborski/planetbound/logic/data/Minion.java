package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Minion {
    private int health;
    private int positionX;
    private int positionY;

    void attack(Minion m){

    }


}
