package com.example.snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class SnakeLadder extends Application {
    public static final int tileSize=40,width=10,height=10,buttonLine= height*tileSize+50,infoLine=height*tileSize+20;
    Player playerFirst,playerSecond;
    boolean firstPlayerTurn=true,gameStart=false;
    int diceValue;
    private Pane creatContent() throws FileNotFoundException {
        Pane root=new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);
        for (int i = 0; i <height ; i++) {
            for (int j = 0; j <width ; j++) {
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().addAll(tile);
            }
            
        }
//
        FileInputStream inputstream = new FileInputStream("C:\\Users\\91961\\IdeaProjects\\Snake\\src\\img_1.png");

        Image img = new Image(inputstream);
        ImageView  boarImage = new ImageView(img);
boarImage.setFitWidth(width*tileSize);
boarImage.setFitHeight(height*tileSize);
        //boarImage.getWidth(boar);
        //boarImage.getHeight(height*tileSize);

        root.getChildren().add(boarImage);
        Button startButton =new Button("start");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);
        Button playeroneButton=new Button("Player One");
         playeroneButton.setTranslateX(10);
        playeroneButton.setTranslateY(buttonLine);
        Button playerTwoButton=new Button("Player Two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);
        Label discLable=new Label("Start the Game");
        discLable.setTranslateX(180);
        discLable.setTranslateY(infoLine);

        playerFirst=new Player("Sai", Color.BLACK,tileSize/2);
        playerSecond=new Player("Roy",Color.WHITE,tileSize/2-5);
        playeroneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(firstPlayerTurn){
                        diceValue=rollDice();
                        discLable.setText("Dice : "+diceValue);
                        playerFirst.movePlayers(diceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(playerFirst.checkWinner()){
                            discLable.setText("Winner is "+playerFirst.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gameStart=false;
                        }

                    }
                }

            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(!firstPlayerTurn){
                        diceValue=rollDice();
                        discLable.setText("Dice : "+diceValue);
                        playerSecond.movePlayers(diceValue);
                        firstPlayerTurn=!firstPlayerTurn;
                        if(playerSecond.checkWinner()){
                            discLable.setText("Winner is "+playerSecond.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerTurn=true;
                            gameStart=false;
                        }
                    }
                }

            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart=true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();
            }
        });

        root.getChildren().addAll(startButton,playeroneButton,playerTwoButton,discLable,playerFirst.getCoin(),playerSecond.getCoin());
        return root;

    }
    private int rollDice(){
        return (int) (Math.random()*6+1);
    }
    @Override
    public void start(Stage stage) throws IOException {
         Scene scene = new Scene(creatContent());
        stage.setTitle("SnakeLadder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}