package com.kodilla.project.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class FiveInRowController implements Initializable {

    MainController mainController;
    @FXML
    private GridPane gridPaneFiveInRow;
    @FXML
    private Text chooseSignText;
    @FXML
    private Text winnerText;
    @FXML
    Button oButton, xButton, newGameButton;
    ArrayList<Button> OXButtons;
    private Button[][] board;
    private String playerSign;
    private String computerSign;
    private int moveCounter = 0;
    boolean allButtonsDisable;
    Random random = new Random();


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        OXButtons = new ArrayList<>(Arrays.asList(xButton, oButton));
        OXButtons.forEach(oXButton -> {
            newGameButton.setDisable(true);
            choiceButton(oXButton);
            oXButton.setFocusTraversable(false);
        });

        board = new Button[10][10];

        int row;
        for (row = 0; row < 10; row++) {
            int column;
            for (column = 0; column < 10; column++) {
                Button boardButton = new Button(" ");
                boardButton.setMaxSize(30, 30);
                boardButton.setPrefSize(30, 30);
                boardButton.setMinSize(30, 30);
                gridPaneFiveInRow.add(boardButton, column, row);
                board[row][column] = boardButton;
                boardButton.setDisable(true);
                boardButton.setFocusTraversable(false);

                boardButton.setOnAction(btn -> {
                    setupButton(boardButton);

                });

            }
        }

    }
    private void setupButton(Button button) {

        button.setOnMouseClicked(mouseEvent -> {
                    button.setText(playerSign);
                    button.setTextFill(Color.GREEN);
                    button.setFont(new Font(14));
                    button.setStyle("-fx-font-weight: bold");
                    button.setDisable(true);
                    moveCounter++;
                    verifyTheResult(moveCounter, playerSign, computerSign);
                    moveCounter++;
                    if (moveCounter < 100) {
                        computerMove();
                    }
                }
        );
    }

    public void choiceButton(Button button) {

        button.setOnMouseClicked(mouseEvent -> {
                    for (int row = 0; row < 10; row++) {
                        for (int col = 0; col < 10; col++) {
                            board[row][col].setDisable(false);
                        }
                    }
                    newGameButton.setDisable(false);
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
    }

    public void computerMove() {
        int computerRowNumber = random.nextInt(10);
        int computerColumnNumber = random.nextInt(10);
        if (!allButtonsDisabled()) {
            makeComputerMove(computerRowNumber, computerColumnNumber);
            verifyTheResult(moveCounter, playerSign, computerSign);
        }
    }

    private void makeComputerMove(int row, int column) {
        computerSign = playerSign == "X" ? "O" : "X";

        if (board[row][column].isDisabled()) {
            computerMove();
        } else {
            board[row][column].setText(computerSign);
            board[row][column].setTextFill(Color.RED);
            board[row][column].setFont(new Font(14));
            board[row][column].setStyle("-fx-font-weight: bold");
            board[row][column].setDisable(true);
        }
    }

    public void verifyTheResult(int moveCounter, String playerSign, String computerSign) {
        if (checkRowsFiveInRow(playerSign) || checkColumnsFiveInRow(playerSign) ||
                checkTheDiagonalFromLeftToRightFiveInRow(playerSign) || checkTheDiagonalFromRightToLeftFiveInRow(playerSign)
        ) {
            winnerText.setText("Congratulations! You are the winner.");
            winnerText.setFont(new Font(14));
            buttonsDisable();
        } else if (checkRowsFiveInRow(computerSign)|| checkColumnsFiveInRow(computerSign) ||
                checkTheDiagonalFromLeftToRightFiveInRow(computerSign) || checkTheDiagonalFromRightToLeftFiveInRow(computerSign)
        ) {
            winnerText.setText("You lost! The winner is " + computerSign + ".");
            winnerText.setFont(new Font(14));
            buttonsDisable();
        } else if (verifyTie(moveCounter)) {
            winnerText.setText("It' a draw.");
            winnerText.setFont(new Font(20));
            buttonsDisable();
        }
    }

    private void buttonsDisable() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col].setDisable(true);
            }
        }
    }

    private boolean allButtonsDisabled() {

        allButtonsDisable = board[0][0].isDisabled() && board[0][1].isDisabled() && board[0][2].isDisabled() && board[0][3].isDisabled() &&
                board[0][4].isDisabled() && board[0][5].isDisabled() && board[0][6].isDisabled() &&
                board[0][7].isDisabled() && board[0][8].isDisabled() && board[0][9].isDisabled() &&
                board[1][0].isDisabled() && board[1][1].isDisabled() && board[1][2].isDisabled() && board[1][3].isDisabled() &&
                board[1][4].isDisabled() && board[1][5].isDisabled() && board[1][6].isDisabled() &&
                board[1][7].isDisabled() && board[1][8].isDisabled() && board[1][9].isDisabled() &&
                board[2][0].isDisabled() && board[2][1].isDisabled() && board[2][2].isDisabled() && board[2][3].isDisabled() &&
                board[2][4].isDisabled() && board[2][5].isDisabled() && board[2][6].isDisabled() &&
                board[2][7].isDisabled() && board[2][8].isDisabled() && board[2][9].isDisabled() &&
                board[3][0].isDisabled() && board[3][1].isDisabled() && board[3][2].isDisabled() && board[3][3].isDisabled() &&
                board[3][4].isDisabled() && board[3][5].isDisabled() && board[3][6].isDisabled() &&
                board[3][7].isDisabled() && board[3][8].isDisabled() && board[3][9].isDisabled() &&
                board[4][0].isDisabled() && board[4][1].isDisabled() && board[4][2].isDisabled() && board[4][3].isDisabled() &&
                board[4][4].isDisabled() && board[4][5].isDisabled() && board[4][6].isDisabled() &&
                board[4][7].isDisabled() && board[4][8].isDisabled() && board[4][9].isDisabled() &&
                board[5][0].isDisabled() && board[5][1].isDisabled() && board[5][2].isDisabled() && board[5][3].isDisabled() &&
                board[5][4].isDisabled() && board[5][5].isDisabled() && board[5][6].isDisabled() &&
                board[5][7].isDisabled() && board[5][8].isDisabled() && board[5][9].isDisabled() &&
                board[6][0].isDisabled() && board[6][1].isDisabled() && board[6][2].isDisabled() && board[6][3].isDisabled() &&
                board[6][4].isDisabled() && board[6][5].isDisabled() && board[6][6].isDisabled() &&
                board[6][7].isDisabled() && board[6][8].isDisabled() && board[6][9].isDisabled() &&
                board[7][0].isDisabled() && board[7][1].isDisabled() && board[7][2].isDisabled() && board[7][3].isDisabled() &&
                board[7][4].isDisabled() && board[7][5].isDisabled() && board[7][6].isDisabled() &&
                board[7][7].isDisabled() && board[7][8].isDisabled() && board[7][9].isDisabled() &&
                board[8][0].isDisabled() && board[8][1].isDisabled() && board[8][2].isDisabled() && board[8][3].isDisabled() &&
                board[8][4].isDisabled() && board[8][5].isDisabled() && board[8][6].isDisabled() &&
                board[8][7].isDisabled() && board[8][8].isDisabled() && board[8][9].isDisabled() &&
                board[9][0].isDisabled() && board[9][1].isDisabled() && board[9][2].isDisabled() && board[9][3].isDisabled() &&
                board[9][4].isDisabled() && board[9][5].isDisabled() && board[9][6].isDisabled() &&
                board[9][7].isDisabled() && board[9][8].isDisabled() && board[9][9].isDisabled();
        return allButtonsDisable;
    }

    public void restartGame(ActionEvent event) {
        for (int row = 0; row < 10; row++) {
                        for (int col = 0; col < 10; col++) {
                          board[row][col].setText("");
                            board[row][col].setDisable(true);
                       }
                    }
        newGameButton.setDisable(true);
        winnerText.setText("");
        chooseSignText.setText("Choose your player sign");
        chooseSignText.setFont(new Font(14));
        chooseSignText.setFill(Color.BLACK);
        moveCounter = 0;
        OXButtons.forEach(this::resetOXButton);
    }

    public void resetOXButton(Button button) {
        button.setDisable(false);
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

    public boolean checkRowsFiveInRow(String player) {
        int size = 10;

        for (int row = 0; row < size - 1; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player) &&
                    board[row][3].getText().equals(player) &&
                    board[row][4].getText().equals(player) &&
                    !board[row][5].getText().equals(player)) {
                return true;
            }
            for (int column = 1; column < size - 5; column++) {
                if (board[row][column].getText().equals(player) &&
                        board[row][column + 1].getText().equals(player) &&
                        board[row][column + 2].getText().equals(player) &&
                        board[row][column + 3].getText().equals(player) &&
                        board[row][column + 4].getText().equals(player) &&
                        !board[row][column - 1].getText().equals(player) &&
                        !board[row][column + 5].getText().equals(player)) {
                    return true;
                }
                if (board[row][5].getText().equals(player) &&
                        board[row][6].getText().equals(player) &&
                        board[row][7].getText().equals(player) &&
                        board[row][8].getText().equals(player) &&
                        board[row][9].getText().equals(player) &&
                        !board[row][4].getText().equals(player)) {
                    return true;

                }
            }
        }

        return false;
    }

    public boolean checkColumnsFiveInRow(String player) {
        int size = 10;

        for (int column = 0; column < size - 1; column++) {
            if (board[0][column].getText().equals(player) &&
                    board[1][column].getText().equals(player) &&
                    board[2][column].getText().equals(player) &&
                    board[3][column].getText().equals(player) &&
                    board[4][column].getText().equals(player) &&
                    !board[5][column].getText().equals(player)) {
                return true;
            }
            for (int row = 1; row < size - 5; row++) {
                if (board[row][column].getText().equals(player) &&
                        board[row + 1][column].getText().equals(player) &&
                        board[row + 2][column].getText().equals(player) &&
                        board[row + 3][column].getText().equals(player) &&
                        board[row + 4][column].getText().equals(player) &&
                        !board[row - 1][column].getText().equals(player) &&
                        !board[row + 5][column].getText().equals(player)) {
                    return true;
                }
                if (board[5][column].getText().equals(player) &&
                        board[6][column].getText().equals(player) &&
                        board[7][column].getText().equals(player) &&
                        board[8][column].getText().equals(player) &&
                        board[9][column].getText().equals(player) &&
                        !board[4][column].getText().equals(player)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkTheDiagonalFromLeftToRightFiveInRow(String player) {
        int size = 10;
        for (int row = 0; row < size - 5; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row + 1][1].getText().equals(player) &&
                    board[row + 2][2].getText().equals(player) &&
                    board[row + 3][3].getText().equals(player) &&
                    board[row + 4][4].getText().equals(player) &&
                    !board[row + 5][5].getText().equals(player)) {
                return true;
            }
        }
        for (int column = 1; column < size - 5; column++) {
            if (board[0][column].getText().equals(player) &&
                    board[1][column + 1].getText().equals(player) &&
                    board[2][column + 2].getText().equals(player) &&
                    board[3][column + 3].getText().equals(player) &&
                    board[4][column + 4].getText().equals(player) &&
                    !board[5][column + 5].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 1; row < size-5; row++){
            if (board[row][1].getText().equals(player) &&
                    board[row + 1][2].getText().equals(player) &&
                    board[row + 2][3].getText().equals(player) &&
                    board[row + 3][4].getText().equals(player) &&
                    board[row + 4][5].getText().equals(player) &&
                    !board[row + 5][6].getText().equals(player) &&
                    !board[row - 1][0].getText().equals(player)){
                return true;
            }
        }
        for (int column = 2; column < size - 5; column++) {
            if (board[1][column].getText().equals(player) &&
                    board[2][column + 1].getText().equals(player) &&
                    board[3][column + 2].getText().equals(player) &&
                    board[4][column + 3].getText().equals(player) &&
                    board[5][column + 4].getText().equals(player) &&
                    !board[6][column + 5].getText().equals(player) &&
                    !board[0][column - 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 2; row < size-5; row++){
            if (board[row][2].getText().equals(player) &&
                    board[row + 1][3].getText().equals(player) &&
                    board[row + 2][4].getText().equals(player) &&
                    board[row + 3][5].getText().equals(player) &&
                    board[row + 4][6].getText().equals(player) &&
                    !board[row + 5][7].getText().equals(player) &&
                    !board[row - 1][1].getText().equals(player)){
                return true;
            }
        }
        for (int column = 3; column < size - 5; column++) {
            if (board[2][column].getText().equals(player) &&
                    board[3][column + 1].getText().equals(player) &&
                    board[4][column + 2].getText().equals(player) &&
                    board[5][column + 3].getText().equals(player) &&
                    board[6][column + 4].getText().equals(player) &&
                    !board[7][column + 5].getText().equals(player) &&
                    !board[1][column - 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 3; row < size-5; row++){
            if (board[row][3].getText().equals(player) &&
                    board[row + 1][4].getText().equals(player) &&
                    board[row + 2][5].getText().equals(player) &&
                    board[row + 3][6].getText().equals(player) &&
                    board[row + 4][7].getText().equals(player) &&
                    !board[row + 5][8].getText().equals(player) &&
                    !board[row - 1][2].getText().equals(player)){
                return true;
            }
        }
        for (int column = 4; column < size - 5; column++) {
            if (board[3][column].getText().equals(player) &&
                    board[4][column + 1].getText().equals(player) &&
                    board[5][column + 2].getText().equals(player) &&
                    board[6][column + 3].getText().equals(player) &&
                    board[7][column + 4].getText().equals(player) &&
                    !board[8][column + 5].getText().equals(player) &&
                    !board[2][column - 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 4; row < size-5; row++){
            if (board[row][4].getText().equals(player) &&
                    board[row + 1][5].getText().equals(player) &&
                    board[row + 2][6].getText().equals(player) &&
                    board[row + 3][7].getText().equals(player) &&
                    board[row + 4][8].getText().equals(player) &&
                    !board[row + 5][9].getText().equals(player) &&
                    !board[row - 1][3].getText().equals(player)){
                return true;
            }
        }

        for (int row = 5; row < size-4; row++){
            if (board[row][0].getText().equals(player) &&
                    board[row + 1][1].getText().equals(player) &&
                    board[row + 2][2].getText().equals(player) &&
                    board[row + 3][3].getText().equals(player) &&
                    board[row + 4][4].getText().equals(player)){
                return true;
            }
        }
        for (int column = 5; column < size-4; column++){
            if (board[0][column].getText().equals(player) &&
                    board[1][column + 1].getText().equals(player) &&
                    board[2][column + 2].getText().equals(player) &&
                    board[3][column + 3].getText().equals(player) &&
                    board[4][column + 4].getText().equals(player)){
                return true;
            }
        }
        for (int row = 1; row < size-4; row++){
            if (board[row][5].getText().equals(player) &&
                    board[row + 1][6].getText().equals(player) &&
                    board[row + 2][7].getText().equals(player) &&
                    board[row + 3][8].getText().equals(player) &&
                    board[row + 4][9].getText().equals(player) &&
                    !board[row - 1][4].getText().equals(player)){
                return true;
            }
        }
        for (int column = 1; column < size-5; column++){
            if (board[5][column].getText().equals(player) &&
                    board[6][column + 1].getText().equals(player) &&
                    board[7][column + 2].getText().equals(player) &&
                    board[8][column + 3].getText().equals(player) &&
                    board[9][column + 4].getText().equals(player) &&
                    !board[4][column - 1].getText().equals(player)){
                return true;
            }
        }
        return false;
    }
    public boolean checkTheDiagonalFromRightToLeftFiveInRow(String player) {
        int size = 10;
        for (int row = 0; row < size - 5; row++) {
            if (board[row][size - 1].getText().equals(player) &&
                    board[row + 1][size - 2].getText().equals(player) &&
                    board[row + 2][size - 3].getText().equals(player) &&
                    board[row + 3][size - 4].getText().equals(player) &&
                    board[row + 4][size - 5].getText().equals(player) &&
                    !board[row + 5][size - 6].getText().equals(player)) {
                return true;
            }
        }
        for (int column = 5; column < size - 1; column++) {
            if (board[0][column].getText().equals(player) &&
                    board[1][column - 1].getText().equals(player) &&
                    board[2][column - 2].getText().equals(player) &&
                    board[3][column - 3].getText().equals(player) &&
                    board[4][column - 4].getText().equals(player) &&
                    !board[5][column - 5].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 1; row < size - 5; row++) {
            if (board[row][size - 2].getText().equals(player) &&
                    board[row + 1][size - 3].getText().equals(player) &&
                    board[row + 2][size - 4].getText().equals(player) &&
                    board[row + 3][size - 5].getText().equals(player) &&
                    board[row + 4][size - 6].getText().equals(player) &&
                    !board[row + 5][size - 7].getText().equals(player) &&
                    !board[row - 1][size - 1].getText().equals(player)) {
                return true;
            }
        }
        for (int column = 5; column < size - 2; column++) {
            if (board[1][column].getText().equals(player) &&
                    board[2][column - 1].getText().equals(player) &&
                    board[3][column - 2].getText().equals(player) &&
                    board[4][column - 3].getText().equals(player) &&
                    board[5][column - 4].getText().equals(player) &&
                    !board[6][column - 5].getText().equals(player) &&
                    !board[0][column + 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 2; row < size - 5; row++) {
            if (board[row][size - 3].getText().equals(player) &&
                    board[row + 1][size - 4].getText().equals(player) &&
                    board[row + 2][size - 5].getText().equals(player) &&
                    board[row + 3][size - 6].getText().equals(player) &&
                    board[row + 4][size - 7].getText().equals(player) &&
                    !board[row + 5][size - 8].getText().equals(player) &&
                    !board[row - 1][size - 2].getText().equals(player)) {
                return true;
            }
        }
        for (int column = 5; column < size - 3; column++) {
            if (board[2][column].getText().equals(player) &&
                    board[3][column - 1].getText().equals(player) &&
                    board[4][column - 2].getText().equals(player) &&
                    board[5][column - 3].getText().equals(player) &&
                    board[6][column - 4].getText().equals(player) &&
                    !board[7][column - 5].getText().equals(player) &&
                    !board[1][column + 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 3; row < size - 5; row++) {
            if (board[row][size - 4].getText().equals(player) &&
                    board[row + 1][size - 5].getText().equals(player) &&
                    board[row + 2][size - 6].getText().equals(player) &&
                    board[row + 3][size - 7].getText().equals(player) &&
                    board[row + 4][size - 8].getText().equals(player) &&
                    !board[row + 5][size - 9].getText().equals(player) &&
                    !board[row - 1][size - 3].getText().equals(player)) {
                return true;
            }
        }
        for (int column = 5; column < size - 4; column++) {
            if (board[3][column].getText().equals(player) &&
                    board[4][column - 1].getText().equals(player) &&
                    board[5][column - 2].getText().equals(player) &&
                    board[6][column - 3].getText().equals(player) &&
                    board[7][column - 4].getText().equals(player) &&
                    !board[8][column - 5].getText().equals(player) &&
                    !board[2][column + 1].getText().equals(player)) {
                return true;
            }
        }
        for (int row = 4; row < size-5; row++){
            if (board[row][size - 5].getText().equals(player) &&
                    board[row + 1][size - 6].getText().equals(player) &&
                    board[row + 2][size - 7].getText().equals(player) &&
                    board[row + 3][size - 8].getText().equals(player) &&
                    board[row + 4][size - 9].getText().equals(player) &&
                    !board[row + 5][size - 10].getText().equals(player) &&
                    !board[row - 1][size - 4].getText().equals(player)){
                return true;
            }
        }
        for (int row = 0; row < size-9; row++){
            if (board[row][size-6].getText().equals(player) &&
                    board[row + 1][size-7].getText().equals(player) &&
                    board[row + 2][size-8].getText().equals(player) &&
                    board[row + 3][size-9].getText().equals(player) &&
                    board[row + 4][size-10].getText().equals(player)){
                return true;
            }
        }
        for (int column = 9; column < size; column++){
            if (board[5][column].getText().equals(player) &&
                    board[6][column - 1].getText().equals(player) &&
                    board[7][column - 2].getText().equals(player) &&
                    board[8][column - 3].getText().equals(player) &&
                    board[9][column - 4].getText().equals(player)){
                return true;
            }
        }
        for (int row = 1; row < size-4; row++){
            if (board[row][4].getText().equals(player) &&
                    board[row + 1][3].getText().equals(player) &&
                    board[row + 2][2].getText().equals(player) &&
                    board[row + 3][1].getText().equals(player) &&
                    board[row + 4][0].getText().equals(player) &&
                    !board[row - 1][5].getText().equals(player)){
                return true;
            }
        }
        for (int column = 5; column < size-1; column++){
            if (board[5][column].getText().equals(player) &&
                    board[6][column - 1].getText().equals(player) &&
                    board[7][column - 2].getText().equals(player) &&
                    board[8][column - 3].getText().equals(player) &&
                    board[9][column - 4].getText().equals(player) &&
                    !board[4][column + 1].getText().equals(player)){
                return true;
            }
        }

        return false;
    }
    public boolean verifyTie(int moveCounter) {

        if (moveCounter >= 100 && !checkRowsFiveInRow("X") && !checkRowsFiveInRow("O") &&
                !checkColumnsFiveInRow("X") && !checkColumnsFiveInRow("O") &&
                !checkTheDiagonalFromRightToLeftFiveInRow("X") && !checkTheDiagonalFromRightToLeftFiveInRow("O") &&
                !checkTheDiagonalFromLeftToRightFiveInRow("X") && !checkTheDiagonalFromLeftToRightFiveInRow("O")) {
            return true;
        }
        return false;
    }
}