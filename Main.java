package com.myMatch4game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
        controller.createPlayground();

        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Match Four");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenu() {

        // File Menu
        Menu fileMenu = new Menu("File");

        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(event -> controller.resetGame());

        MenuItem resetGame = new MenuItem("Reset game");
        resetGame.setOnAction(event -> controller.resetGame());

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit game");
        exitGame.setOnAction(event -> exitGame());

        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        // Help Menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutGame = new MenuItem("About Match4");
        aboutGame.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About Match Four");
            alert.setHeaderText("How To Play?");
            alert.setContentText("Match Four is a two-player connection game in which the " +
                    "players first choose a color and then take turns dropping colored discs " +
                    "from the top into a seven-column, six-row vertically suspended grid. "+
                    "The pieces fall straight down, occupying the next available space within the column. "+
                    "The objective of the game is to be the first to form a horizontal, vertical, " +
                    "or diagonal line of four of one's own discs. Match Four is a solved game. " +
                    "The first player can always win by playing the right moves.");

            alert.show();
        });

        SeparatorMenuItem separator = new SeparatorMenuItem();

        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About The Developer");
            alert.setHeaderText("Charan Chowdary");
            alert.setContentText("I love to play around with code and create games. " +
                    "Match 4 is one of them. ");

            alert.show();
        });

        helpMenu.getItems().addAll(aboutGame, separator, aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }




    private void exitGame() {

        Platform.exit();
        System.exit(0);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
