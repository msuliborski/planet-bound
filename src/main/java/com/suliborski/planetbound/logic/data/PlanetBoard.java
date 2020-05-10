package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class PlanetBoard {
    private Alien alien;

    private Drone drone;

    private int landingPlaceX;
    private int landingPlaceY;

    private String resourceType;
    private int resourceX;
    private int resourceY;

    private int waitTimeForAlien = 999;


    public void prepareBoard(String planetType) {

        landingPlaceX = (int) Math.floor(Math.random() * 3);
        landingPlaceY = (int) Math.floor(Math.random() * 3);
        drone = new Drone(6, landingPlaceX, landingPlaceY);

        spawnAlien();


        resourceX = (int) Math.floor(Math.random() * 3 + 4);
        resourceY = (int) Math.floor(Math.random() * 3 + 4);

        List<String> resourceIds = new ArrayList<>();
        if (planetType.equals("red")) resourceIds.add("red"); resourceIds.add("blue");
        if (planetType.equals("green")) resourceIds.add("red"); resourceIds.add("green");
        if (planetType.equals("blue")) resourceIds.add("black"); resourceIds.add("green"); resourceIds.add("blue"); resourceIds.add("artifact");
        if (planetType.equals("black")) resourceIds.add("black"); resourceIds.add("blue");
        resourceType = resourceIds.get(new Random().nextInt(resourceIds.size()));
    }

    public void spawnAlien(){

        alien.setHealth(1);

        int alienX = drone.getX();
        int alienY = drone.getY();

        while (Math.sqrt(Math.pow(alienX - drone.getX(), 2) + Math.pow(alienY - drone.getY(), 2)) <= 2) {
            alienX = (int) Math.floor(Math.random() * 6);
            alienY = (int) Math.floor(Math.random() * 6);
        }

        double r = Math.floor(Math.random() * 4 + 1);
        if (r == 1) alien = new Alien("red", 1, alienX, alienY);
        else if (r == 2) alien = new Alien("green", 1, alienX,alienY);
        else if (r == 3) alien = new Alien("blue", 1, alienX,alienY);
        else alien = new Alien("black", 1, alienX,alienY);

    }

    public boolean move(String direction) {
        if (drone.getHealth() == 0) return false;
        if (direction.equals("up") && drone.getY() > 0)
            drone.setY(drone.getY() - 1);
        else if (direction.equals("down") && drone.getY() < 5)
            drone.setY(drone.getY() + 1);
        else if (direction.equals("left") && drone.getX() > 0)
            drone.setX(drone.getX() - 1);
        else if (direction.equals("right") && drone.getX() < 5)
            drone.setX(drone.getX() + 1);


        if (drone.getX() == resourceX && drone.getY() == resourceY) {
            drone.setCargoLoaded(true);
            drone.setCargoType(resourceType);
        }

        if (alien.getHealth() != 0) {
            if (checkIfInRange(drone.getX(), drone.getY(), alien.getX(), alien.getY()))
                fight("drone");
            else
                moveAlien();
        }
        return true;
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

            if (checkIfInRange(drone.getX(), drone.getY(), alien.getX(), alien.getY()))
                fight("alien");
        }
    }
    private boolean checkIfInRange(int x1, int y1, int x2, int y2){
        return (x1 == x2 && y1 == y2) || (x1 == x2 - 1 && y1 == y2) || (x1 == x2 + 1 && y1 == y2) ||
                (x1 == x2 && y1 == y2 - 1) || (x1 == x2 && y1 == y2 + 1);
    }

    private void fight(String initiator){

        int thrownValue = Dice.throwd6();

        if(initiator.equals("drone")){
            //dron attacks
        }
        while (true) {
            // alien attack

            // drone attack
        }
        if (!drone.isDestroyed()) {
            while(true){
                thrownValue = Dice.throwd6();
                if(alien.isAlienDead(thrownValue, logRecorder)) {
                    if(ship.getWeaponSystem().isAvailable() || ship.getWeaponSystem().getWeapons() != 0){
                        alien.destroy();
                        alienDestroyed();
                        ship.getWeaponSystem().spendAmmo(1);
                        break;
                    } else drone.damageDrone();
                }
                thrownValue = Dice.throwd6();
                droneAttacked(thrownValue);
                if (droneDestroyed) break;

            }
        }

    }

}
