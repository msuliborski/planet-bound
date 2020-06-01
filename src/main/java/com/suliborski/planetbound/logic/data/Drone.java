package com.suliborski.planetbound.logic.data;

public class Drone extends Minion {
    private String cargoType;
    private boolean isCargoLoaded;
    private boolean backWithCargo;

    public Drone(int health, int positionX, int positionY) {
        super(health, positionX, positionY);
        isCargoLoaded = false;
        backWithCargo = false;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public boolean isCargoLoaded() {
        return isCargoLoaded;
    }

    public void setCargoLoaded(boolean cargoLoaded) {
        isCargoLoaded = cargoLoaded;
    }

    public boolean isBackWithCargo() {
        return backWithCargo;
    }

    public void setBackWithCargo(boolean backWithCargo) {
        this.backWithCargo = backWithCargo;
    }
}
