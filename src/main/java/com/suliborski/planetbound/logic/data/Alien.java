package com.suliborski.planetbound.logic.data;

public class Alien extends Minion {
    private String type;

    public Alien(String type, int health, int positionX, int positionY) {
        super(health, positionX, positionY);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
