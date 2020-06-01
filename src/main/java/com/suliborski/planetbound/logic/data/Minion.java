package com.suliborski.planetbound.logic.data;

import java.io.Serializable;

public abstract class Minion implements Serializable {
    private int health;
    private int x;
    private int y;

    public Minion(int health, int x, int y) {
        this.health = health;
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
