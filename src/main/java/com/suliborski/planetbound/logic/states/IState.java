package com.suliborski.planetbound.logic.states;

public interface IState {

    IState selectShip(String type);

    IState travelToNextPlanet();
    IState acceptTravelConsequences();

    IState visitSpaceStation();
    IState convertResource(String type, String into);
    IState fullFixEnergyShields();
    IState buyDrone();
    IState upgradeCargoCapacity();
    IState upgradeWeaponSystem();
    IState recruitCrewMember();
    IState leaveSpaceStation();

    IState goOnExpedition();
    IState moveUp();
    IState moveDown();
    IState moveLeft();
    IState moveRight();

    IState produceEnergyShield();
    IState produceAmmo();
    IState produceFuel();

    IState playAgain();
    IState saveGame();
    IState loadGame();
    IState exitGame();


}
