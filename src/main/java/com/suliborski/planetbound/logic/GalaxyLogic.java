package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.states.IState;
import com.suliborski.planetbound.logic.states.ShipSelectionState;
import lombok.Getter;
import lombok.Setter;


public class GalaxyLogic {

    @Getter @Setter
    private IState state;

    @Getter @Setter
    private GalaxyData galaxyData;

    public GalaxyLogic() {
        galaxyData = new GalaxyData();
        state = new ShipSelectionState(galaxyData);
    }

    public void selectShip(String type){
        this.setState(this.state.selectShip(type));
    }


    public void  travelToNextPlanet(){
        this.setState(this.state.travelToNextPlanet());
    }

    public void  acceptTravelConsequences(){
        this.setState(this.state.acceptTravelConsequences());
    }


    public void  visitSpaceStation(){
        this.setState(this.state.visitSpaceStation());
    }

    public void convertResource(String type, String into) {
        this.setState(this.state.convertResource(type, into));
    }

    public void fullFixEnergyShields() {
        this.setState(this.state.fullFixEnergyShields());
    }

    public void  buyDrone(){
        this.setState(this.state.buyDrone());
    }

    public void  upgradeCargoCapacity(){
        this.setState(this.state.upgradeCargoCapacity());
    }

    public void  upgradeWeaponSystem(){
        this.setState(this.state.upgradeWeaponSystem());
    }

    public void  recruitCrewMember(){
        this.setState(this.state.recruitCrewMember());
    }

    public void  leaveSpaceStation(){
        this.setState(this.state.leaveSpaceStation());
    }


    public void goOnExpedition(){
        this.setState(this.state.goOnExpedition());
    }

    public void  moveUp(){
        this.setState(this.state.moveUp());
    }

    public void  moveDown(){
        this.setState(this.state.moveDown());
    }

    public void  moveLeft(){
        this.setState(this.state.moveLeft());
    }

    public void  moveRight(){
        this.setState(this.state.moveRight());
    }


    public void produceEnergyShield(){
        this.setState(this.state.produceEnergyShield());
    }

    public void produceAmmo(){
        this.setState(this.state.produceAmmo());
    }

    public void produceFuel(){
        this.setState(this.state.produceFuel());
    }


    public void  playAgain(){
        this.setState(this.state.playAgain());
    }

    public void  saveGame(){
        this.setState(this.state.saveGame());
    }

    public void  loadGame(){
        this.setState(this.state.loadGame());
    }

    public void  exitGame(){
        this.setState(this.state.exitGame());
    }

}
