package com.kodilla.project.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class TicTacToeController implements Initializable {

    MainController mainController;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, oButton, xButton;
    @FXML
    private Text chooseSignText;
    @FXML
    private Text winnerText;
    private int counter = 0;
    private String playerSign;
    private String computerSign;
    Random random = new Random();
    ArrayList<Button> buttons;
    ArrayList<Button> OXButtons;
    boolean allButtonsDisable;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        OXButtons = new ArrayList<>(Arrays.asList(xButton, oButton));
        OXButtons.forEach(oXButton -> {
            choiceButton(oXButton);
            oXButton.setFocusTraversable(false);
        });
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    public String choiceButton(Button button) {

        button.setOnMouseClicked(mouseEvent -> {
            buttons.forEach(b -> b.setDisable(false));
                    xButton.setDisable(true);
                    oButton.setDisable(true);
                    button.setDisable(false);
                    if (!xButton.isDisabled()) {
                        playerSign = "X";
                    } else if (!oButton.isDisabled()) {
                        playerSign = "O";
                    }
                    xButton.setDisable(true);
                    oButton.setDisable(true);
                    chooseSignText.setText("     PLAYER " + playerSign);
            chooseSignText.setFont(new Font(20));
            chooseSignText.setFill(Color.GREEN);
                }
        );
        return playerSign;
    }

    public void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        OXButtons.forEach(this::resetOXButton);
    }

    public void resetButton(Button button) {
        button.setText("");
        button.setDisable(true);
        winnerText.setText("");
        chooseSignText.setText("Choose your player sign:");
        chooseSignText.setFont(new Font(14));
        chooseSignText.setFill(Color.BLACK);
        counter = 0;
    }
    public void resetOXButton(Button button) {
        button.setDisable(false);
    }

    private void setupButton(Button button) {
        buttons.forEach(b -> b.setDisable(true));

        button.setOnMouseClicked(mouseEvent -> {
                    button.setText(playerSign);
                    button.setTextFill(Color.GREEN);
                    button.setDisable(true);
                    counter++;
                    checkTheWinner();
                    counter++;
                    if (counter < 9) {
                        makeComputerMove();
                    }
                }
        );
    }

    public void makeComputerMove() {
        int move = random.nextInt(9);
        if (!allButtonsDisable()) {
            pickButton(move);
            checkTheWinner();
        }
    }

    private void pickButton(int index) {
        computerSign = playerSign == "X" ? "O" : "X";

        if (buttons.get(index).isDisabled()) {
            makeComputerMove();
        } else {
            buttons.get(index).setText(computerSign);
            buttons.get(index).setTextFill(Color.RED);
            buttons.get(index).setDisable(true);
        }
    }

    public void checkTheWinner() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();

                default -> null;
            };
            if (line.equals(playerSign+""+playerSign+""+playerSign)) {
                winnerText.setText("Congratulations! You are the winner.");
                winnerText.setFont(new Font(14));
                buttonsDisable();
                break;

            } else if (line.equals(computerSign+""+computerSign+""+computerSign)) {
                winnerText.setText("You lost! The winner is " + computerSign + ".");
                winnerText.setFont(new Font(14));
                buttonsDisable();
                break;

            } else if (counter == 9 && !(line.equals("XXX") || line.equals("OOO"))) {
                winnerText.setText("It' a draw.");
                winnerText.setFont(new Font(20));
            }
        }
    }

    private void buttonsDisable() {
        buttons.forEach(button -> button.setDisable(true));
    }

    private boolean allButtonsDisable() {
        if (buttons.get(0).isDisabled() && buttons.get(1).isDisabled() && buttons.get(2).isDisabled()
                && buttons.get(3).isDisabled() && buttons.get(4).isDisabled() && buttons.get(5).isDisabled()
                && buttons.get(6).isDisabled() && buttons.get(7).isDisabled() && buttons.get(8).isDisabled()) {
            allButtonsDisable = true;
        } else {
            allButtonsDisable = false;
        }
       return allButtonsDisable;
    }

    @FXML
    public void backMenu() {
        mainController.loadMenuScreen();
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
