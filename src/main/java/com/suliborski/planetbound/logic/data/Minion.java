package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Minion {
    private int health;
    private int x;
    private int y;

}
