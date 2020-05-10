package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Planet extends Landable {
    private String type;
    private int redCargo;
    private int greenCargo;
    private int blueCargo;
    private int blackCargo;
    private int artifacts;
    private boolean isExplorable;


    public Planet(String type, int redCargo, int greenCargo, int blueCargo, int blackCargo, int artifacts, boolean isExplorable, boolean isPlanet) {
        super(isPlanet);
        this.type = type;
        this.redCargo = redCargo;
        this.greenCargo = greenCargo;
        this.blueCargo = blueCargo;
        this.blackCargo = blackCargo;
        this.artifacts = artifacts;
        this.isExplorable = isExplorable;
    }
}
