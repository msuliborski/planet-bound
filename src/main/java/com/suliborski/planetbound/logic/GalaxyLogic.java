package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.data.Log;
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


    public void playAgain(){
        galaxyData.clearLogs();
        this.state = this.state.playAgain();
    }

    public void saveGame(String fileName){
        galaxyData.setState(this.state);
        try(ObjectOutputStream saveOut = new ObjectOutputStream(new FileOutputStream("saves/" + fileName + ".pbs"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("saves/" + fileName + ".log"))) {
            GalaxyData.addLog(new Log("Game has been saved to " + fileName + ".pbs file."));
            GalaxyData.addLog(new Log("Log has been saved to " + fileName + ".log file."));
            galaxyData.logsToPermanentLogs();
            saveOut.writeObject(galaxyData);
            writer.write(galaxyData.getPermanentLogsString());
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadGame(String fileName){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("saves/" + fileName + ".pbs"))) {
            galaxyData.clearLogs();
            this.galaxyData = (GalaxyData) in.readObject();
            GalaxyData.addLog(new Log("Game has been loaded from " + fileName + ".pbs"));
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.state =  galaxyData.getState();
    }

    public void deleteGame(String fileName){
        File pbsFile = new File("saves/" + fileName + ".pbs");
        File logFile = new File("saves/" + fileName + ".log");

        if(pbsFile.delete()) GalaxyData.addLog(new Log("Save file " + fileName + ".pbs deleted successfully"));
         else GalaxyData.addLog(new Log("Failed to delete the save file " + fileName + ".pbs"));

        if(logFile.delete()) GalaxyData.addLog(new Log("Log file " + fileName + ".log deleted successfully"));
         else GalaxyData.addLog(new Log("Failed to delete the log file " + fileName + ".log"));

    }
}
