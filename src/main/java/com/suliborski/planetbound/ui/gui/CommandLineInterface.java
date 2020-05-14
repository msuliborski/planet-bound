package com.suliborski.planetbound.ui.gui;

import com.suliborski.planetbound.logic.GalaxyLogic;
import com.suliborski.planetbound.logic.data.Alien;
import com.suliborski.planetbound.logic.data.Drone;
import com.suliborski.planetbound.logic.states.*;

import java.util.Scanner;

public class CommandLineInterface {

    GalaxyLogic galaxyLogic;
    boolean exitGame = false;

    public CommandLineInterface() {
        galaxyLogic = new GalaxyLogic();
    }

    public void run() {
        while(!exitGame){
            if(galaxyLogic.getState() instanceof ShipSelectionState) shipSelection();
            else if(galaxyLogic.getState() instanceof AcceptTravelConsequencesState) acceptTravelConsequences();
            else if(galaxyLogic.getState() instanceof OnPlanetState) onPlanet();
            else if(galaxyLogic.getState() instanceof OnExpeditionState) onExpedition();
            else if(galaxyLogic.getState() instanceof OnSpaceStationState) onSpaceStation();
            else if(galaxyLogic.getState() instanceof GameWonState) gameOver(true);
            else if(galaxyLogic.getState() instanceof GameLostState) gameOver(false);
        }
    }


    private void shipSelection(){
        System.out.println("######################################");
        System.out.println("############ Planet Bound ############");
        System.out.println("######################################\n");
        System.out.println("Select type of the ship:");
        System.out.println("1. Mining");
        System.out.println("2. Military");
        System.out.println("0. Exit");

        switch(filterForIntegers(new Scanner(System.in))) {
            case 1: galaxyLogic.selectShip("mining"); break;
            case 2: galaxyLogic.selectShip("military"); break;
            case 0: exitGame = true;
        }
    }


    private void onPlanet() {
        System.out.println("You are on planet orbit.");
        System.out.println("Planet type is " + galaxyLogic.getGalaxyData().getPlanet().getType().toUpperCase() + ".");
        System.out.println
                ("Resources available: " + (galaxyLogic.getGalaxyData().getShip().getCrew() >= 2 ?
                            (galaxyLogic.getGalaxyData().getPlanet().getResourceIds().isEmpty() ? "none" : String.join(", ", galaxyLogic.getGalaxyData().getPlanet().getResourceIds()).toUpperCase())
                            : "??? (recruit Navigation Officer)"));
        if (galaxyLogic.getGalaxyData().getPlanet().isSpaceStationAvailable()) System.out.println("There is a space station here!");
        else System.out.println("There is no space station here!");

        displayStatus();

        System.out.println("1. Travel to next planet");
        System.out.println("2. Go on expedition");
        System.out.println("3. Produce 1 energy shield (1 GREEN, 1 BLUE, 1 BLACK)");
        System.out.println("4. Produce 1 ammo (1 BLUE, 1 BLACK)");
        System.out.println("5. Produce 1 fuel (1 RED, 1 GREEN, 1 BLACK)");
        if (galaxyLogic.getGalaxyData().getPlanet().isSpaceStationAvailable()) System.out.println("6. Go to Space Station");
        System.out.println("0. Exit game");

        switch(filterForIntegers(new Scanner(System.in))) {
            case 1: galaxyLogic.travelToNextPlanet(); break;
            case 2: galaxyLogic.goOnExpedition(); break;
            case 3: galaxyLogic.produceEnergyShield(); break;
            case 4: galaxyLogic.produceAmmo(); break;
            case 5: galaxyLogic.produceFuel(); break;
            case 6:
                if (galaxyLogic.getGalaxyData().getPlanet().isSpaceStationAvailable()) galaxyLogic.visitSpaceStation();
                break;
            case 0: exitGame = true;
        }
    }

    private void onSpaceStation(){

        displayStatus();

        System.out.println("You are at the Space Station.\n");

        System.out.println("1. Convert resource");
        System.out.println("2. Fully fix energy shields (1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        System.out.println("3. Buy a new drone (3 RED, 3 GREEN, 3 BLUE, 3 BLACK)");
        System.out.println("4. Upgrade cargo (1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        System.out.println("5. Upgrade weapon system (2 RED, 2 GREEN, 2 BLUE, 2 BLACK)");
        System.out.println("6. Recruit new crew member (1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        System.out.println("7. Leave Space Station");
        System.out.println("0. Exit");

        switch(filterForIntegers(new Scanner(System.in))) {
            case 1:
                System.out.println("From resource: (1 - RED, 2 - GREEN, 3 - BLUE, 4 - BLACK)");
                int type = filterForIntegers(new Scanner(System.in));
                System.out.println("To resource: (1 - RED, 2 - GREEN, 3 - BLUE, 4 - BLACK)");
                int into = filterForIntegers(new Scanner(System.in));

                galaxyLogic.convertResource(getResourceStringFromInt(type), getResourceStringFromInt(into));
                break;
            case 2: galaxyLogic.fullFixEnergyShields(); break;
            case 3: galaxyLogic.buyDrone(); break;
            case 4: galaxyLogic.upgradeCargoCapacity(); break;
            case 5: galaxyLogic.upgradeWeaponSystem(); break;
            case 6: galaxyLogic.recruitCrewMember(); break;
            case 7: galaxyLogic.leaveSpaceStation(); break;
            case 0: exitGame = true; break;
        }
    }

    private String getResourceStringFromInt(int resourceInt) {
        String resourceString = "";
        switch (resourceInt) {
            case 1: resourceString = "red"; break;
            case 2: resourceString = "green"; break;
            case 3: resourceString = "blue"; break;
            case 4: resourceString = "black"; break;
        }
        return resourceString;
    }

    private void onExpedition(){
        System.out.println("You are on the surface");
        System.out.println("Planet has " + galaxyLogic.getGalaxyData().getExpedition().getResourceType().toUpperCase() + " resource type.");

        drawBoard();

        System.out.println("w - Move up");
        System.out.println("a - Move left");
        System.out.println("s - Move down");
        System.out.println("d - Move right");
        System.out.println("0. Exit");

        switch(new Scanner(System.in).next().charAt(0)) {
            case 'w': galaxyLogic.moveUp(); break;
            case 'a': galaxyLogic.moveLeft(); break;
            case 's': galaxyLogic.moveDown(); break;
            case 'd': galaxyLogic.moveRight(); break;
            case '0': exitGame = true; break;
        }
    }

    private void drawBoard() {

        Alien alien = galaxyLogic.getGalaxyData().getExpedition().getAlien();
        Drone drone = galaxyLogic.getGalaxyData().getExpedition().getDrone();

        System.out.println("==============");
        System.out.print("  ");
        for (int i = 0; i < 6; i++)
            System.out.print(i+1 + " ");
        System.out.print("\n");

        for (int i = 0; i < 6; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < 6; j++) {
                if (alien.getHealth() >= 1 && alien.getX() == j && alien.getY() == i) System.out.print("A ");
                else if (drone.getX() == j && drone.getY() == i) System.out.print(galaxyLogic.getGalaxyData().getExpedition().getDrone().getHealth() + " ");
                else if (galaxyLogic.getGalaxyData().getExpedition().getLandingPlaceX() == j && galaxyLogic.getGalaxyData().getExpedition().getLandingPlaceY() == i) System.out.print("L ");
                else if (galaxyLogic.getGalaxyData().getExpedition().getResourceX() == j && galaxyLogic.getGalaxyData().getExpedition().getResourceY() == i && !galaxyLogic.getGalaxyData().getExpedition().getDrone().isCargoLoaded() ) System.out.print("R ");
                else System.out.print("- ");
            }
            System.out.print("\n");
        }
        System.out.println("==============");
    }

    private void acceptTravelConsequences(){
        if (galaxyLogic.getGalaxyData().getEvent() == null)
            System.out.println("You are travelling through a wormhole!\n" +
                    "Travel cost is 3 fuel cells and 2 shield cells\n" +
                    "If you have the fuel, but not the shield cells, you will lose a crew member\n" +
                    "If you don’t have a Shield System Officer, costs increase by 1 fuel cell and 2 shield cells");
        else {
            System.out.println("You are not travelling through a wormhole! Travel cost is 1 fuel cell.");
            switch (galaxyLogic.getGalaxyData().getEvent().getType()){
                case "crew-death": System.out.println("In addition, a crew member is injured due to a system malfunction. You lose one crew member."); break;
                case "salvage-ship": System.out.println("In addition, your ship comes across an abandoned ship and you find 1-6 of a random resource."); break;
                case "cargo-loss": System.out.println("In addition, a cargo mishap causes you to lose 1-3 of one of your resources."); break;
                case "fuel-loss": System.out.println("In addition, you accidentally use too much fuel in a test run. You lose 1 fuel cell"); break;
                case "no-event": System.out.println("And your travel was without any troubles."); break;
                case "crew-rescue": System.out.println("In addition, you find a ship with a survivor and you gain 1 crew member if possible."); break;
            }
        }

        System.out.println("1. Accept travel consequences");
        System.out.println("0. Exit");

        switch(filterForIntegers(new Scanner(System.in))) {
            case 1: galaxyLogic.acceptTravelConsequences(); break;
            case 0: exitGame = true; break;
        }
    }


    private void gameOver(boolean won) {
        System.out.println(won ? "You won!" : "You lost");
        System.out.println("1. Play again");
        System.out.println("0. Exit");

        switch(filterForIntegers(new Scanner(System.in))) {
            case 1:  galaxyLogic.playAgain(); break;
            case 0:  exitGame = true; break;
        }
    }

    private int filterForIntegers(Scanner scanner) {
        while (!scanner.hasNextInt())
            scanner.next();
        return scanner.nextInt();
    }

    private void displayStatus() {
        System.out.println(
            "Cargo (level: " + galaxyLogic.getGalaxyData().getShip().getCargoLevel() + "/" + galaxyLogic.getGalaxyData().getShip().getMaxCargoLevel() + "):" +
            "  RED " + galaxyLogic.getGalaxyData().getShip().getRedCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getRedCargoCapacity() +
            "  GREEN " + galaxyLogic.getGalaxyData().getShip().getGreenCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getGreenCargoCapacity() +
            "  BLUE " + galaxyLogic.getGalaxyData().getShip().getBlueCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getBlueCargoCapacity() +
            "  BLACK " + galaxyLogic.getGalaxyData().getShip().getBlackCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getBlackCargoCapacity() +
            "  ARTIFACT: " + galaxyLogic.getGalaxyData().getShip().getArtifacts() + "/" + galaxyLogic.getGalaxyData().getArtifactsNeeded() + "\n" +
            "Fuel: " + galaxyLogic.getGalaxyData().getShip().getFuel() + "/" + galaxyLogic.getGalaxyData().getShip().getFuelCapacity() +
            "  Shield: " + galaxyLogic.getGalaxyData().getShip().getShields() + "/" + galaxyLogic.getGalaxyData().getShip().getShieldsCapacity() +
            "  Ammo (level: " + galaxyLogic.getGalaxyData().getShip().getWeaponSystemLevel() + "/" + galaxyLogic.getGalaxyData().getShip().getMaxWeaponSystemLevel() + "): " + galaxyLogic.getGalaxyData().getShip().getAmmo() + "/" + galaxyLogic.getGalaxyData().getShip().getAmmoCapacity() +
            "  Drone available: " + (galaxyLogic.getGalaxyData().getShip().isDroneWorking() ? "yes" : "no") + "\n" +
            "Crew: " + galaxyLogic.getGalaxyData().getShip().getCrew() + " (" +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 1 ? "Captain":"") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 2 ? "Captain, Navigation Officer":"") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 3 ? "Captain, Navigation Officer, Landing Officer":"") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 4 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer":"") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 5 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer, Shield Officer":"") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 6 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer, Shield Officer, Cargo Officer":"") + ")"
        );
    }
}
