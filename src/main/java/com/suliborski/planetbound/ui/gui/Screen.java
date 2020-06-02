package com.suliborski.planetbound.ui.gui;

import com.suliborski.planetbound.logic.GalaxyData;
import com.suliborski.planetbound.logic.GalaxyLogic;
import com.suliborski.planetbound.logic.states.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Screen implements Initializable {
    private Stage stage;

    GalaxyLogic galaxyLogic;

    public Text cargoText;
    public Text cargoBlackText;
    public Text cargoBlueText;
    public Text cargoGreenText;
    public Text cargoRedText;
    public Text cargoArtifactText;
    public Text fuelText;
    public Text droneText;
    public Text ammoText;
    public Text shieldText;
    public Text crewText;

    public Button arrowUp;
    public Button arrowDown;
    public Button arrowLeft;
    public Button arrowRight;

    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;

    public Button saveButton;
    public Button loadButton;
    public Button playAgainButton;
    public TextField saveAs;
    public Spinner<String> whichSpinner;

    public AnchorPane anchor;
    public TextField field00;
    public TextField field01;
    public TextField field02;
    public TextField field03;
    public TextField field04;
    public TextField field05;

    public TextField field10;
    public TextField field11;
    public TextField field12;
    public TextField field13;
    public TextField field14;
    public TextField field15;

    public TextField field20;
    public TextField field21;
    public TextField field22;
    public TextField field23;
    public TextField field24;
    public TextField field25;

    public TextField field30;
    public TextField field31;
    public TextField field32;
    public TextField field33;
    public TextField field34;
    public TextField field35;

    public TextField field40;
    public TextField field41;
    public TextField field42;
    public TextField field43;
    public TextField field44;
    public TextField field45;

    public TextField field50;
    public TextField field51;
    public TextField field52;
    public TextField field53;
    public TextField field54;
    public TextField field55;

    public Map<String, TextField> expeditionFields = new HashMap<>();

    public Text toText;
    public TextField text3;
    public TextField text4;
    public TextField text5;

    public Spinner<String> spinner1;
    public Spinner<String> spinner2;

    public Text mainText;


    private static com.suliborski.planetbound.ui.gui.Screen instance;

    public Screen() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("MainView already created!");
        }
    }

    public static com.suliborski.planetbound.ui.gui.Screen getInstance() {
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchor.setDisable(true);
        galaxyLogic = new GalaxyLogic();
        ObservableList<String> resources = FXCollections.observableArrayList("RED", "GREEN", "BLUE", "BLACK");
        spinner1.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(resources));
        spinner2.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(resources));

        expeditionFields.put("00", field00);
        expeditionFields.put("01", field01);
        expeditionFields.put("02", field02);
        expeditionFields.put("03", field03);
        expeditionFields.put("04", field04);
        expeditionFields.put("05", field05);

        expeditionFields.put("10", field10);
        expeditionFields.put("11", field11);
        expeditionFields.put("12", field12);
        expeditionFields.put("13", field13);
        expeditionFields.put("14", field14);
        expeditionFields.put("15", field15);

        expeditionFields.put("20", field20);
        expeditionFields.put("21", field21);
        expeditionFields.put("22", field22);
        expeditionFields.put("23", field23);
        expeditionFields.put("24", field24);
        expeditionFields.put("25", field25);

        expeditionFields.put("30", field30);
        expeditionFields.put("31", field31);
        expeditionFields.put("32", field32);
        expeditionFields.put("33", field33);
        expeditionFields.put("34", field34);
        expeditionFields.put("35", field35);

        expeditionFields.put("40", field40);
        expeditionFields.put("41", field41);
        expeditionFields.put("42", field42);
        expeditionFields.put("43", field43);
        expeditionFields.put("44", field44);
        expeditionFields.put("45", field45);

        expeditionFields.put("50", field50);
        expeditionFields.put("51", field51);
        expeditionFields.put("52", field52);
        expeditionFields.put("53", field53);
        expeditionFields.put("54", field54);
        expeditionFields.put("55", field55);

        updateView();
        updateSaveSpinner();
    }

    void updateSaveSpinner() {
        File folder = new File("saves");
        File[] listOfFiles = folder.listFiles();

        ObservableList<String> saves = FXCollections.observableArrayList();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().substring(file.getName().lastIndexOf('.')).equals(".pbs")) saves.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
        }
        whichSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(saves));
    }

    void updateView() {
        if (galaxyLogic.getState() instanceof ShipSelectionState) shipSelection();
        else if (galaxyLogic.getState() instanceof AcceptTravelConsequencesState) acceptTravelConsequences();
        else if (galaxyLogic.getState() instanceof OnPlanetState) onPlanet();
        else if (galaxyLogic.getState() instanceof OnExpeditionState) onExpedition();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) onSpaceStation();
        else if (galaxyLogic.getState() instanceof GameWonState) gameOverWon();
        else if (galaxyLogic.getState() instanceof GameLostState) gameOverLost();

    }

    void hideAll() {
        anchor.setVisible(false);
        arrowUp.setVisible(false);
        arrowDown.setVisible(false);
        arrowLeft.setVisible(false);
        arrowRight.setVisible(false);

        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        button7.setVisible(false);

        toText.setVisible(false);

        text3.setVisible(false);
        text4.setVisible(false);
        text5.setVisible(false);

        spinner1.setVisible(false);
        spinner2.setVisible(false);

    }

    @FXML
    void button1Click(){
        if (galaxyLogic.getState() instanceof ShipSelectionState) galaxyLogic.selectShip("mining");
        else if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.travelToNextPlanet();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.convertResource(spinner1.getValue().toLowerCase(), spinner2.getValue().toLowerCase());
        else if (galaxyLogic.getState() instanceof AcceptTravelConsequencesState) galaxyLogic.acceptTravelConsequences();
        else if (galaxyLogic.getState() instanceof GameWonState) galaxyLogic.playAgain();
        else if (galaxyLogic.getState() instanceof GameLostState) galaxyLogic.playAgain();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button2Click(){
        if (galaxyLogic.getState() instanceof ShipSelectionState) galaxyLogic.selectShip("military");
        else if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.goOnExpedition();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.fullFixEnergyShields();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button3Click(){
        if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.produceEnergyShield();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.buyDrone();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button4Click(){
        if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.produceAmmo();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.upgradeCargoCapacity();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button5Click(){
        if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.produceFuel();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.upgradeWeaponSystem();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button6Click(){
        if (galaxyLogic.getState() instanceof OnPlanetState) galaxyLogic.visitSpaceStation();
        else if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.recruitCrewMember();

        updateStatus(true);
        updateView();
    }

    @FXML
    void button7Click(){
        if (galaxyLogic.getState() instanceof OnSpaceStationState) galaxyLogic.leaveSpaceStation();

        updateStatus(true);
        updateView();
    }

    void shipSelection(){
        hideAll();

        mainText.setText("Select type of the ship:");
        button1.setVisible(true);
        button1.setText("Mining");
        button2.setVisible(true);
        button2.setText("Military");
    }

    private void onPlanet() {
        hideAll();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You are on planet orbit.").append("\n");
        stringBuilder.append("Planet type is ").append(galaxyLogic.getGalaxyData().getPlanet().getType().toUpperCase()).append(".\n");
        stringBuilder.append("Resources available: ").append(galaxyLogic.getGalaxyData().getShip().getCrew() >= 2 ?
                (galaxyLogic.getGalaxyData().getPlanet().getResourceIds().isEmpty() ? "none \n" : String.join(", ", galaxyLogic.getGalaxyData().getPlanet().getResourceIds()).toUpperCase())
                : "??? (recruit Navigation Officer)").append("\n");
        if (galaxyLogic.getGalaxyData().getPlanet().isSpaceStationAvailable()) stringBuilder.append("There is a space station here!").append("\n");
        else stringBuilder.append("There is no space station here!").append("\n");

        mainText.setText(stringBuilder.toString());

        updateStatus(true);

        button1.setVisible(true);
        button1.setText("Travel to next planet");
        button2.setVisible(true);
        button2.setText("Go on expedition");
        button3.setVisible(true);
        button3.setText("Produce 1 energy shield \n(1 GREEN, 1 BLUE, 1 BLACK)");
        button4.setVisible(true);
        button4.setText("Produce 1 ammo \n(1 BLUE, 1 BLACK)");
        button5.setVisible(true);
        button5.setText("Produce 1 fuel \n(1 RED, 1 GREEN, 1 BLACK)");
        if (galaxyLogic.getGalaxyData().getPlanet().isSpaceStationAvailable()) {
            button6.setVisible(true);
            button6.setText("Go to Space Station");
        }
    }

    private void onSpaceStation(){
        mainText.setText("You are at the Space Station.\n");
        button1.setVisible(true);
        button1.setText("Convert resource");
        toText.setVisible(true);
        button2.setVisible(true);
        button2.setText("Fully fix energy shields \n(1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        button3.setVisible(true);
        button3.setText("Buy a new drone \n(3 RED, 3 GREEN, 3 BLUE, 3 BLACK)");
        button4.setVisible(true);
        button4.setText("Upgrade cargo \n(1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        button5.setVisible(true);
        button5.setText("Upgrade weapon system \n(2 RED, 2 GREEN, 2 BLUE, 2 BLACK)");
        button6.setVisible(true);
        button6.setText("Recruit new crew member \n(1 RED, 1 GREEN, 1 BLUE, 1 BLACK)");
        button7.setVisible(true);
        button7.setText("Leave Space Station");

        spinner1.setVisible(true);
        spinner2.setVisible(true);
    }

    private void onExpedition(){
        hideAll();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("You are on the surface").append("\n");
        stringBuilder.append("Planet has ").append(galaxyLogic.getGalaxyData().getExpedition().getResourceType().toUpperCase()).append(" resource type.");
        mainText.setText(stringBuilder.toString());

        arrowUp.setVisible(true);
        arrowDown.setVisible(true);
        arrowLeft.setVisible(true);
        arrowRight.setVisible(true);

        anchor.setVisible(true);
        updateBoard();
    }

    @FXML
    void moveDroneUp(){
        galaxyLogic.moveUp();
        updateBoard();
        updateStatus(true);
        updateView();
    }

    @FXML
    void moveDroneDown(){
        galaxyLogic.moveDown();
        updateBoard();
        updateStatus(true);
        updateView();
    }

    @FXML
    void moveDroneLeft(){
        galaxyLogic.moveLeft();
        updateBoard();
        updateStatus(true);
        updateView();
    }

    @FXML
    void moveDroneRight(){
        galaxyLogic.moveRight();
        updateBoard();
        updateStatus(true);
        updateView();
    }

    private void updateBoard() {
        for (Map.Entry<String, TextField> element : expeditionFields.entrySet()) element.getValue().clear();

        int landingX = galaxyLogic.getGalaxyData().getExpedition().getLandingPlaceX();
        int landingY = galaxyLogic.getGalaxyData().getExpedition().getLandingPlaceY();

        int resourceX = galaxyLogic.getGalaxyData().getExpedition().getResourceX();
        int resourceY = galaxyLogic.getGalaxyData().getExpedition().getResourceY();

        int alienX = galaxyLogic.getGalaxyData().getExpedition().getAlien().getX();
        int alienY = galaxyLogic.getGalaxyData().getExpedition().getAlien().getY();
        int alienHealth = galaxyLogic.getGalaxyData().getExpedition().getAlien().getHealth();

        int droneX = galaxyLogic.getGalaxyData().getExpedition().getDrone().getX();
        int droneY = galaxyLogic.getGalaxyData().getExpedition().getDrone().getY();
        int droneHealth = galaxyLogic.getGalaxyData().getExpedition().getDrone().getHealth();

        boolean isCargoLoaded = galaxyLogic.getGalaxyData().getExpedition().getDrone().isCargoLoaded();

        expeditionFields.get(String.valueOf(landingX) + String.valueOf(landingY)).setText("L");
        if (!isCargoLoaded) expeditionFields.get(String.valueOf(resourceX) + String.valueOf(resourceY)).setText("R");
        if (alienHealth > 0) expeditionFields.get(String.valueOf(alienX) + String.valueOf(alienY)).setText("A");
        expeditionFields.get(String.valueOf(droneX) + String.valueOf(droneY)).setText(String.valueOf(droneHealth));
    }

    private void acceptTravelConsequences(){
        hideAll();

        StringBuilder stringBuilder = new StringBuilder();
        if (galaxyLogic.getGalaxyData().getEventType() == null)
            stringBuilder.append("You are travelling through a wormhole!\n" +
                    "Travel cost is 3 fuel cells and 2 shield cells\n" +
                    "If you have the fuel, but not the shield cells, you will lose a crew member\n" +
                    "If you don’t have a Shield System Officer, costs increase by 1 fuel cell and 2 shield cells\n");
        else {
            stringBuilder.append("You are not travelling through a wormhole! Travel cost is 1 fuel cell.\n");
            switch (galaxyLogic.getGalaxyData().getEventType()){
                case crewDeath: stringBuilder.append("In addition, a crew member is injured due to a system malfunction. You lose one crew member."); break;
                case salvageShip: stringBuilder.append("In addition, your ship comes across an abandoned ship and you find 1-6 of a random resource."); break;
                case cargoLoss: stringBuilder.append("In addition, a cargo mishap causes you to lose 1-3 of one of your resources."); break;
                case fuelLoss: stringBuilder.append("In addition, you accidentally use too much fuel in a test run. You lose 1 fuel cell"); break;
                case noEvent: stringBuilder.append("And your travel was without any troubles."); break;
                case crewRescue: stringBuilder.append("In addition, you find a ship with a survivor and you gain 1 crew member if possible."); break;
            }
        }
        mainText.setText(stringBuilder.toString());

        button1.setVisible(true);
        button1.setText("Accept travel consequences");
    }

    private void gameOverWon() {
        hideAll();
        updateStatus(false);

        mainText.setText("You won!");

        button1.setVisible(true);
        button1.setText("Play again");
    }

    private void gameOverLost() {
        hideAll();
        updateStatus(false);

        mainText.setText("You lost");

        button1.setVisible(true);
        button1.setText("Play again");
    }

    @FXML
    private void playAgain() {
        galaxyLogic.playAgain();
        updateView();
        updateStatus(false);
    }

    @FXML
    private void saveGame() {
        if (!saveAs.getText().isEmpty()) {
            galaxyLogic.saveGame(saveAs.getText());
            saveAs.clear();
            updateSaveSpinner();
        }
    }

    @FXML
    private void loadGame() {
        galaxyLogic.loadGame(whichSpinner.getValue());
        updateView();
        updateStatus(true);
    }

    @FXML
    private void deleteSave() {
        galaxyLogic.deleteGame(whichSpinner.getValue());
        updateSaveSpinner();
    }

    private void updateStatus(boolean showValues) {
        if (showValues) {
            cargoText.setText("Cargo (level: " + galaxyLogic.getGalaxyData().getShip().getCargoLevel() + "/" + galaxyLogic.getGalaxyData().getShip().getMaxCargoLevel() + "):");
            cargoBlackText.setText("BLACK " + galaxyLogic.getGalaxyData().getShip().getBlackCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getBlackCargoCapacity());
            cargoBlueText.setText("BLUE " + galaxyLogic.getGalaxyData().getShip().getBlueCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getBlueCargoCapacity());
            cargoGreenText.setText("GREEN " + galaxyLogic.getGalaxyData().getShip().getGreenCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getGreenCargoCapacity());
            cargoRedText.setText("RED " + galaxyLogic.getGalaxyData().getShip().getRedCargo() + "/" + galaxyLogic.getGalaxyData().getShip().getRedCargoCapacity());
            cargoArtifactText.setText("ARTIFACT: " + galaxyLogic.getGalaxyData().getShip().getArtifacts() + "/" + galaxyLogic.getGalaxyData().getArtifactsNeeded());
            fuelText.setText("Fuel: " + galaxyLogic.getGalaxyData().getShip().getFuel() + "/" + galaxyLogic.getGalaxyData().getShip().getFuelCapacity());
            droneText.setText("Drone available: " + (galaxyLogic.getGalaxyData().getShip().isDroneWorking() ? "yes" : "no"));
            ammoText.setText("Ammo (level: " + galaxyLogic.getGalaxyData().getShip().getWeaponSystemLevel() + "/" + galaxyLogic.getGalaxyData().getShip().getMaxWeaponSystemLevel() + "): " + galaxyLogic.getGalaxyData().getShip().getAmmo() + "/" + galaxyLogic.getGalaxyData().getShip().getAmmoCapacity());
            shieldText.setText("Shield: " + galaxyLogic.getGalaxyData().getShip().getShields() + "/" + galaxyLogic.getGalaxyData().getShip().getShieldsCapacity());
            crewText.setText("Crew: " + galaxyLogic.getGalaxyData().getShip().getCrew() + "/6 (" +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 1 ? "Captain" : "") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 2 ? "Captain, Navigation Officer" : "") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 3 ? "Captain, Navigation Officer, Landing Officer" : "") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 4 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer" : "") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 5 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer, Shield Officer" : "") +
                    (galaxyLogic.getGalaxyData().getShip().getCrew() == 6 ? "Captain, Navigation Officer, Landing Officer, Weapon Officer, Shield Officer, Cargo Officer" : "") + ")");
        } else {
            cargoText.setText("Cargo (level: ?/?):");
            cargoBlackText.setText("BLACK ?/?");
            cargoBlueText.setText("BLUE ?/?");
            cargoGreenText.setText("GREEN ?/?");
            cargoRedText.setText("RED ?/?");
            cargoArtifactText.setText("ARTIFACT: ?/?");
            fuelText.setText("Fuel: ??/??");
            droneText.setText("Drone available: ???");
            ammoText.setText("Ammo (level: ?/?): ??/??");
            shieldText.setText("Shield: ??/??");
            crewText.setText("Crew: ?/?: ");
        }
    }
}
