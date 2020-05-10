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

    public GalaxyLogic(int stateId) {
        galaxyData = new GalaxyData();
        state = new ShipSelectionState(galaxyData);
    }

    public void selectShip(String type){
        this.setState(this.state.selectShip(type));
    }

    public void  travelToLandable(){
        this.setState(this.state.travelToLandable());
    }

    public void  acceptTravelConsequences(){
        this.setState(this.state.acceptTravelConsequences());
    }

    public void  buyEnergyShield(){
        this.setState(this.state.buyEnergyShield());
    }

    public void  buyAmmo(){
        this.setState(this.state.buyAmmo());
    }

    public void  buyFuel(){
        this.setState(this.state.buyFuel());
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

    public void  explorePlanet(){
        this.setState(this.state.explorePlanet());
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

    public void  playAgain(){
        this.setState(this.state.playAgain());
    }


}
