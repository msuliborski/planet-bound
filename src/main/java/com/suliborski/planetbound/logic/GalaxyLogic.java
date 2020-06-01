package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.states.IState;
import com.suliborski.planetbound.logic.states.ShipSelectionState;

import java.io.*;

public class GalaxyLogic {
    private IState state;

    private GalaxyData galaxyData;

    public GalaxyLogic() {
        galaxyData = new GalaxyData();
        state = new ShipSelectionState(galaxyData);
    }

    public IState getState() {
        return state;
    }

    public GalaxyData getGalaxyData() {
        return galaxyData;
    }

    public void selectShip(String type){
        this.state = this.state.selectShip(type);
    }


    public void  travelToNextPlanet(){
        this.state = this.state.travelToNextPlanet();
    }

    public void  acceptTravelConsequences(){
        this.state = this.state.acceptTravelConsequences();
    }


    public void  visitSpaceStation(){
        this.state = this.state.visitSpaceStation();
    }

    public void convertResource(String type, String into) {
        this.state = this.state.convertResource(type, into);
    }

    public void fullFixEnergyShields() {
        this.state = this.state.fullFixEnergyShields();
    }

    public void  buyDrone(){
        this.state = this.state.buyDrone();
    }

    public void  upgradeCargoCapacity(){
        this.state = this.state.upgradeCargoCapacity();
    }

    public void  upgradeWeaponSystem(){
        this.state = this.state.upgradeWeaponSystem();
    }

    public void  recruitCrewMember(){
        this.state = this.state.recruitCrewMember();
    }

    public void  leaveSpaceStation(){
        this.state = this.state.leaveSpaceStation();
    }


    public void goOnExpedition(){
        this.state = this.state.goOnExpedition();
    }

    public void  moveUp(){
        this.state = this.state.moveUp();
    }

    public void  moveDown(){
        this.state = this.state.moveDown();
    }

    public void  moveLeft(){
        this.state = this.state.moveLeft();
    }

    public void  moveRight(){
        this.state = this.state.moveRight();
    }


    public void produceEnergyShield(){
        this.state = this.state.produceEnergyShield();
    }

    public void produceAmmo(){
        this.state = this.state.produceAmmo();
    }

    public void produceFuel(){
        this.state = this.state.produceFuel();
    }


    public void  playAgain(){
        this.state = this.state.playAgain();
    }

    public void  saveGame(String fileName){
        galaxyData.setState(this.state);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("saves/" + fileName + ".pbs"))) {
            out.writeObject(galaxyData);
            System.out.println("Object has been serialized");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void  loadGame(String fileName){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("saves/" + fileName + ".pbs"))) {
            this.galaxyData = (GalaxyData) in.readObject();
            System.out.println("Object has been deserialized ");
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.state =  galaxyData.getState();
    }
}
