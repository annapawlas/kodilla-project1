package com.kodilla.project.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    MainController mainController;

    @FXML
    public void openTicTacToeGame(){

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/kodilla/project/fxml/TicTacToeScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TicTacToeController ticTacToeController = loader.getController();
        ticTacToeController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void openFiveInRowGame(){

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/kodilla/project/fxml/FiveInRowScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FiveInRowController fiveInRowController = loader.getController();
        fiveInRowController.setMainController(mainController);
        mainController.setScreen(pane);
    }
    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void exit(){
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}