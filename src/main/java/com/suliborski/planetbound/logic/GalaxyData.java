package com.suliborski.planetbound.logic;

import com.suliborski.planetbound.logic.data.*;
import com.suliborski.planetbound.logic.states.IState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GalaxyData implements Serializable {
    private static final List<Log> logs = new ArrayList<>();
    private List<Log> permanentLogs = new ArrayList<>();

    private Ship ship;

    private Planet planet;
    private EventType eventType;

    private Expedition expedition;

    private IState state;

    private int artifactsNeeded = 5;

    public static void addLog(Log log){
        logs.add(log);
        System.out.println(log.toString());
    }
    public String getPermanentLogsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Log log : permanentLogs) {
            stringBuilder.append(log.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
    public void clearLogs() {
        logs.clear();
    }
    public void logsToPermanentLogs() {
        permanentLogs.addAll(logs);
    }
    public void permanentLogsLogsTo() {
        logs.addAll(permanentLogs);
    }

    public Ship getShip() {
        return ship;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Planet getPlanet() {
        return planet;
    }
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public EventType getEventType() {
        return eventType;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Expedition getExpedition() {
        return expedition;
    }
    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public int getArtifactsNeeded() {
        return artifactsNeeded;
    }
    public void setArtifactsNeeded(int artifactsNeeded) {
        this.artifactsNeeded = artifactsNeeded;
    }

    public IState getState() {
        return state;
    }
    public void setState(IState state) {
        this.state = state;
    }

    public void preparePlanet() {
        double r = Math.floor(Math.random() * 4 + 1);
        if (r == 1) planet = new Planet("red");
        else if (r == 2) planet = new Planet("green");
        else if (r == 3) planet = new Planet("blue");
        else planet = new Planet("black");
        expedition = new Expedition();
    }

    public void prepareEvent() {
        double r = Math.random();
        if (r < 0.125) {
            eventType = null;
        } else {
            r = Math.floor(Math.random() * 6 + 1);
            if (r == 1) eventType = EventType.crewDeath;
            else if (r == 2) eventType = EventType.salvageShip;
            else if (r == 3) eventType = EventType.cargoLoss;
            else if (r == 4) eventType = EventType.fuelLoss;
            else if (r == 5) eventType = EventType.noEvent;
            else eventType = EventType.crewRescue;
        }
    }
}
