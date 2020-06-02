package com.suliborski.planetbound.logic.data;

import com.suliborski.planetbound.logic.GalaxyData;

import java.io.Serializable;

public class Expedition implements Serializable {
    private Alien alien;
    private Drone drone;

    private int landingPlaceX;
    private int landingPlaceY;

    private String resourceType;
    private int resourceX;
    private int resourceY;

    private int waitTimeForAlien = 999;

    public Alien getAlien() {
        return alien;
    }

    public Drone getDrone() {
        return drone;
    }

    public int getLandingPlaceX() {
        return landingPlaceX;
    }

    public int getLandingPlaceY() {
        return landingPlaceY;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getResourceX() {
        return resourceX;
    }

    public int getResourceY() {
        return resourceY;
    }

    public int getWaitTimeForAlien() {
        return waitTimeForAlien;
    }
    public void setWaitTimeForAlien(int waitTimeForAlien) {
        this.waitTimeForAlien = waitTimeForAlien;
    }

    public void prepareExpedition(Planet planet) {
        landingPlaceX = (int) Math.floor(Math.random() * 3);
        landingPlaceY = (int) Math.floor(Math.random() * 3);
        drone = new Drone(6, landingPlaceX, landingPlaceY);

        spawnAlien();

        resourceX = (int) Math.floor(Math.random() * 3 + 3);
        resourceY = (int) Math.floor(Math.random() * 3 + 3);
        resourceType = planet.getRandomResource();

        GalaxyData.addLog(new Log("Expedition prepared."));
    }

    public void spawnAlien() {
        int alienX = drone.getX();
        int alienY = drone.getY();

        while (Math.sqrt(Math.pow(alienX - drone.getX(), 2) + Math.pow(alienY - drone.getY(), 2)) <= 2) {
            alienX = (int) Math.floor(Math.random() * 6);
            alienY = (int) Math.floor(Math.random() * 6);
        }

        double r = Math.floor(Math.random() * 4 + 1);
        if (r == 1) alien = new Alien("red", 1, alienX, alienY);
        else if (r == 2) alien = new Alien("green", 1, alienX, alienY);
        else if (r == 3) alien = new Alien("blue", 1, alienX, alienY);
        else alien = new Alien("black", 1, alienX, alienY);
        alien.setHealth(1);
        GalaxyData.addLog(new Log("Alien spawned."));
    }

    public void moveDrone(String direction) {
        if (drone.getHealth() == 0) return;
        if (direction.equals("up") && drone.getY() > 0)
            drone.setY(drone.getY() - 1);
        else if (direction.equals("down") && drone.getY() < 5)
            drone.setY(drone.getY() + 1);
        else if (direction.equals("left") && drone.getX() > 0)
            drone.setX(drone.getX() - 1);
        else if (direction.equals("right") && drone.getX() < 5)
            drone.setX(drone.getX() + 1);

        GalaxyData.addLog(new Log("Drone moved"));

        if (drone.getX() == resourceX && drone.getY() == resourceY) {
            drone.setCargoLoaded(true);
            drone.setCargoType(resourceType);
            GalaxyData.addLog(new Log("Drone took resource"));
        }

        if (drone.getX() == landingPlaceX && drone.getY() == landingPlaceY && drone.isCargoLoaded()) {
            drone.setBackWithCargo(true);
            GalaxyData.addLog(new Log("Drone is back with resource"));
            return;
        }

        if (alien.getHealth() != 0) {
            if (checkIfInRange(drone.getX(), drone.getY(), alien.getX(), alien.getY()))
                fight("drone");
            else
                moveAlien();
        } else {
            waitTimeForAlien--;
            if (waitTimeForAlien == 0) spawnAlien();
        }
    }

    private void moveAlien() {
        if (alien.getHealth() == 0) {
            waitTimeForAlien--;
            if (waitTimeForAlien == 0) spawnAlien();
        } else {
            int distanceX = Math.abs(drone.getX() - alien.getX());
            int distanceY = Math.abs(drone.getY() - alien.getY());

            if (distanceX > distanceY) {
                if (drone.getX() > alien.getX())
                    alien.setX(alien.getX() + 1);
                else
                    alien.setX(alien.getX() - 1);
            } else {
                if (drone.getY() > alien.getY())
                    alien.setY(alien.getY() + 1);
                else
                    alien.setY(alien.getY() - 1);
            }
            GalaxyData.addLog(new Log("Alien moved"));

            if (checkIfInRange(drone.getX(), drone.getY(), alien.getX(), alien.getY()))
                fight("alien");
        }
    }

    private boolean checkIfInRange(int x1, int y1, int x2, int y2) {
        return (x1 == x2 && y1 == y2) || (x1 == x2 - 1 && y1 == y2) || (x1 == x2 + 1 && y1 == y2) ||
                (x1 == x2 && y1 == y2 - 1) || (x1 == x2 && y1 == y2 + 1);
    }

    private void fight(String initiator) {
        if (initiator.equals("drone")) {
            GalaxyData.addLog(new Log("Drone attacks"));
            if (Math.random() <= 0.333333d) {
                alien.setHealth(0);
                waitTimeForAlien = (int) Math.floor(Math.random() * 6 + 1);
                GalaxyData.addLog(new Log("Drone destroyed alien"));
                return;
            } else GalaxyData.addLog(new Log("Drone missed"));
        }

        while (drone.getHealth() != 0 && alien.getHealth() != 0) {
            GalaxyData.addLog(new Log("Alien attacks"));
            if (alien.getType().equals("black")) { //alien attacks
                if (Math.random() <= 0.166666d) { drone.setHealth(drone.getHealth() - 1); GalaxyData.addLog(new Log("Alien damaged drone")); }
                else GalaxyData.addLog(new Log("Alien missed"));
            } else {
                if (Math.random() <= 0.333333d) { drone.setHealth(drone.getHealth() - 1); GalaxyData.addLog(new Log("Alien damaged drone")); }
                else GalaxyData.addLog(new Log("Alien missed"));
            }
            if (drone.getHealth() == 0) {
                GalaxyData.addLog(new Log("Drone destroyed"));
                break;
            }

            GalaxyData.addLog(new Log("Drone attacks"));
            if (Math.random() <= 0.333333d) { //drone attacks
                alien.setHealth(0);
                GalaxyData.addLog(new Log("Drone destroyed alien"));
                waitTimeForAlien = (int) Math.floor(Math.random() * 6 + 1);
            } else GalaxyData.addLog(new Log("Drone missed"));
        }
    }
}

