package com.suliborski.planetbound.logic.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Planet extends Landable {
    private String type;
    private int redCargo;
    private int greenCargo;
    private int blueCargo;
    private int blackCargo;
    private int artifacts;
    private boolean isExplorable;

}
