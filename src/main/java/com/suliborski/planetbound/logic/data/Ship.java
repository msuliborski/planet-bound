package com.suliborski.planetbound.logic.data;

import java.io.Serializable;

public class Ship implements Serializable {
    private String type;
    private int crew;
    private int fuel;
    private int shields;
    private int ammo;
    private int crewCapacity;
    private int fuelCapacity;
    private int shieldsCapacity;
    private int ammoCapacity;
    private int artifacts;
    private int redCargo;
    private int greenCargo;
    private int blueCargo;
    private int blackCargo;
    private int redCargoCapacity;
    private int greenCargoCapacity;
    private int blueCargoCapacity;
    private int blackCargoCapacity;

    private int cargoLevel;
    private int maxCargoLevel;
    private int weaponSystemLevel;
    private int maxWeaponSystemLevel;

    private boolean isDroneWorking;

    public Ship(String type, int crew, int fuel, int shields, int ammo, int crewCapacity, int fuelCapacity, int shieldsCapacity, int ammoCapacity, int artifacts, int redCargo, int greenCargo, int blueCargo, int blackCargo, int redCargoCapacity, int greenCargoCapacity, int blueCargoCapacity, int blackCargoCapacity, int cargoLevel, int maxCargoLevel, int weaponSystemLevel, int maxWeaponSystemLevel, boolean isDroneWorking) {
        this.type = type;
        this.crew = crew;
        this.fuel = fuel;
        this.shields = shields;
        this.ammo = ammo;
        this.crewCapacity = crewCapacity;
        this.fuelCapacity = fuelCapacity;
        this.shieldsCapacity = shieldsCapacity;
        this.ammoCapacity = ammoCapacity;
        this.artifacts = artifacts;
        this.redCargo = redCargo;
        this.greenCargo = greenCargo;
        this.blueCargo = blueCargo;
        this.blackCargo = blackCargo;
        this.redCargoCapacity = redCargoCapacity;
        this.greenCargoCapacity = greenCargoCapacity;
        this.blueCargoCapacity = blueCargoCapacity;
        this.blackCargoCapacity = blackCargoCapacity;
        this.cargoLevel = cargoLevel;
        this.maxCargoLevel = maxCargoLevel;
        this.weaponSystemLevel = weaponSystemLevel;
        this.maxWeaponSystemLevel = maxWeaponSystemLevel;
        this.isDroneWorking = isDroneWorking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getShieldsCapacity() {
        return shieldsCapacity;
    }

    public void setShieldsCapacity(int shieldsCapacity) {
        this.shieldsCapacity = shieldsCapacity;
    }

    public int getAmmoCapacity() {
        return ammoCapacity;
    }

    public void setAmmoCapacity(int ammoCapacity) {
        this.ammoCapacity = ammoCapacity;
    }

    public int getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(int artifacts) {
        this.artifacts = artifacts;
    }

    public int getRedCargo() {
        return redCargo;
    }

    public void setRedCargo(int redCargo) {
        this.redCargo = redCargo;
    }

    public int getGreenCargo() {
        return greenCargo;
    }

    public void setGreenCargo(int greenCargo) {
        this.greenCargo = greenCargo;
    }

    public int getBlueCargo() {
        return blueCargo;
    }

    public void setBlueCargo(int blueCargo) {
        this.blueCargo = blueCargo;
    }

    public int getBlackCargo() {
        return blackCargo;
    }

    public void setBlackCargo(int blackCargo) {
        this.blackCargo = blackCargo;
    }

    public int getRedCargoCapacity() {
        return redCargoCapacity;
    }

    public void setRedCargoCapacity(int redCargoCapacity) {
        this.redCargoCapacity = redCargoCapacity;
    }

    public int getGreenCargoCapacity() {
        return greenCargoCapacity;
    }

    public void setGreenCargoCapacity(int greenCargoCapacity) {
        this.greenCargoCapacity = greenCargoCapacity;
    }

    public int getBlueCargoCapacity() {
        return blueCargoCapacity;
    }

    public void setBlueCargoCapacity(int blueCargoCapacity) {
        this.blueCargoCapacity = blueCargoCapacity;
    }

    public int getBlackCargoCapacity() {
        return blackCargoCapacity;
    }

    public void setBlackCargoCapacity(int blackCargoCapacity) {
        this.blackCargoCapacity = blackCargoCapacity;
    }

    public int getCargoLevel() {
        return cargoLevel;
    }

    public void setCargoLevel(int cargoLevel) {
        this.cargoLevel = cargoLevel;
    }

    public int getMaxCargoLevel() {
        return maxCargoLevel;
    }

    public void setMaxCargoLevel(int maxCargoLevel) {
        this.maxCargoLevel = maxCargoLevel;
    }

    public int getWeaponSystemLevel() {
        return weaponSystemLevel;
    }

    public void setWeaponSystemLevel(int weaponSystemLevel) {
        this.weaponSystemLevel = weaponSystemLevel;
    }

    public int getMaxWeaponSystemLevel() {
        return maxWeaponSystemLevel;
    }

    public void setMaxWeaponSystemLevel(int maxWeaponSystemLevel) {
        this.maxWeaponSystemLevel = maxWeaponSystemLevel;
    }

    public boolean isDroneWorking() {
        return isDroneWorking;
    }

    public void setDroneWorking(boolean droneWorking) {
        isDroneWorking = droneWorking;
    }

    public void addResource(String type, int amount) {
        switch (type) {
            case "red": redCargo = Math.min(redCargo + amount, redCargoCapacity); break;
            case "green": greenCargo = Math.min(greenCargo + amount, greenCargoCapacity); break;
            case "blue": blueCargo = Math.min(blueCargo + amount, blueCargoCapacity); break;
            case "black": blackCargo = Math.min(blackCargo + amount, blackCargoCapacity); break;
            case "artifact": artifacts += amount; break;
        }
        System.out.println("Resource(s) added");
    }

    public boolean removeResource(String type, int amount, boolean forceRemoval) { //if force true, you must have needed resources

        switch (type) {
            case "red":
                if (forceRemoval && redCargo - amount < 0) return false;
                else redCargo = Math.max(redCargo - amount, 0); { System.out.println("Red resource(s) removed"); return true; }
            case "green":
                if (forceRemoval && greenCargo - amount < 0) return false;
                else greenCargo = Math.max(greenCargo - amount, 0); { System.out.println("Green resource(s) removed"); return true; }
            case "blue":
                if (forceRemoval && blueCargo - amount < 0) return false;
                else blueCargo = Math.max(blueCargo - amount, 0); { System.out.println("Blue resource(s) removed"); return true; }
            case "black":
                if (forceRemoval && blackCargo - amount < 0) return false;
                else blackCargo = Math.max(blackCargo - amount, 0); { System.out.println("Black resource(s) removed"); return true; }
        }
        return false;
    }

    public void convertResource(String type, String into){
        if (removeResource(type, 1, true)) {
            addResource(into, 1);
            System.out.println("Resource(s) converted");
        }
    }

    public void produceEnergyShield() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getBlueCargo() >= 1 && getShields() < getShieldsCapacity()) {
            removeResource("black", 1, true);
            removeResource("green", 1, true);
            removeResource("blue", 1, true);
            setShields(getShields() + 1);
            System.out.println("Energy shield produced");
        }
    }
    
    public void produceAmmo() {
        if (getBlackCargo() >= 1 && getBlueCargo() >= 1 && getAmmo() < getAmmoCapacity()) {
            removeResource("black", 1, true);
            removeResource("blue", 1, true);
            setAmmo(getAmmo() + 1);
            System.out.println("Ammo produced");
        }
    }

    public void produceFuel() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getFuel() < getFuelCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            setFuel(getFuel() + 1);
            System.out.println("Fuel produced");
        }
    }

    public void buyDrone() {
        if (getBlackCargo() >= 2 && getGreenCargo() >= 2 && getRedCargo() >= 2 && getBlueCargo() >= 2 && !isDroneWorking()) {
            removeResource("black", 2, true);
            removeResource("red", 2, true);
            removeResource("blue", 2, true);
            removeResource("green", 2, true);
            setDroneWorking(true);
            System.out.println("Drone bought");
        }
    }

    public void upgradeCargo() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getCargoLevel() < getMaxCargoLevel()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            setCargoLevel(getCargoLevel() + 1);
            setRedCargoCapacity(getRedCargoCapacity() + 6);
            setGreenCargoCapacity(getGreenCargoCapacity() + 6);
            setBlueCargoCapacity(getBlueCargoCapacity() + 6);
            setBlackCargoCapacity(getBlackCargoCapacity() + 6);
            System.out.println("Cargo upgraded");
        }
    }

    public void upgradeWeaponSystem() {
        if (getBlackCargo() >= 2 && getGreenCargo() >= 2 && getRedCargo() >= 2 && getBlueCargo() >= 2 && getWeaponSystemLevel() < getMaxWeaponSystemLevel()) {
            removeResource("black", 2, true);
            removeResource("red", 2, true);
            removeResource("blue", 2, true);
            removeResource("green", 2, true);
            setWeaponSystemLevel(getCargoLevel() + 1);
            setAmmoCapacity(getAmmoCapacity() + 9);
            System.out.println("Weapon system upgraded");
        }
    }

    public void recruitCrewMember() {
        if (getBlackCargo() >=1  && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getCrew() < getCrewCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            setCrew(getCrew() + 1);
            System.out.println("New member recruited");
        }
    }

    public void fullFixEnergyShields() {
        if (getBlackCargo() >= 1 && getGreenCargo() >= 1 && getRedCargo() >= 1 && getBlueCargo() >= 1 && getShields() < getShieldsCapacity()) {
            removeResource("black", 1, true);
            removeResource("red", 1, true);
            removeResource("blue", 1, true);
            removeResource("green", 1, true);
            shields = shieldsCapacity;
            System.out.println("Shield fully fixed");
        }
    }
}
