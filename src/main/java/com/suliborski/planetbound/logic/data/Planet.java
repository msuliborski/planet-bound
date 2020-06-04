package com.suliborski.planetbound.logic.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planet implements Serializable {
    private String type;
    List<String> resourceIds = new ArrayList<>();
    private boolean spaceStationAvailable;
    private boolean canUpgradeCargo;
    private boolean canUpgradeWeaponSystem;

    public Planet(String type) {
        this.type = type;
        this.spaceStationAvailable = Math.random() <= 0.3;
        this.canUpgradeCargo = this.spaceStationAvailable;
        this.canUpgradeWeaponSystem = this.spaceStationAvailable;
        if (type.equals("red")) {
            resourceIds.add("red");
            resourceIds.add("blue");
        }
        if (type.equals("green")) {
            resourceIds.add("red");
            resourceIds.add("green");
        }
        if (type.equals("blue")) {
            resourceIds.add("black");
            resourceIds.add("green");
            resourceIds.add("blue");
            resourceIds.add("artifact");
        }
        if (type.equals("black")) {
            resourceIds.add("black");
            resourceIds.add("blue");
        }
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public boolean isSpaceStationAvailable() {
        return spaceStationAvailable;
    }

    public void setSpaceStationAvailable(boolean spaceStationAvailable) {
        this.spaceStationAvailable = spaceStationAvailable;
    }

    public String getRandomResource(){
        String randomResource = resourceIds.get(new Random().nextInt(resourceIds.size()));
        resourceIds.remove(randomResource);
        return randomResource;
    }

    public void addResource(String resource){
        resourceIds.add(resource);
    }

    public boolean isExplorable(){
        return !resourceIds.isEmpty();
    }

    public boolean isCanUpgradeCargo() {
        return canUpgradeCargo;
    }

    public void setCanUpgradeCargo(boolean canUpgradeCargo) {
        this.canUpgradeCargo = canUpgradeCargo;
    }

    public boolean isCanUpgradeWeaponSystem() {
        return canUpgradeWeaponSystem;
    }

    public void setCanUpgradeWeaponSystem(boolean canUpgradeWeaponSystem) {
        this.canUpgradeWeaponSystem = canUpgradeWeaponSystem;
    }
}
