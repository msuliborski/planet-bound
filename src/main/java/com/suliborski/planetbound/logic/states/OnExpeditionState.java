package com.suliborski.planetbound.logic.states;

import com.suliborski.planetbound.logic.Galaxy;

public class OnExpeditionState extends StateAdapter {


    public OnExpeditionState(Galaxy galaxy) {
        super(galaxy);
    }

    @Override
    public IState moveUp() {
        return super.moveUp();
    }

    @Override
    public IState moveDown() {
        return super.moveDown();
    }

    @Override
    public IState moveLeft() {
        return super.moveLeft();
    }

    @Override
    public IState moveRight() {
        return super.moveRight();
    }
}
