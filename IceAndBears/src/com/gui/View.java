package com.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.ArrayList;

import com.iceandbears.IceAndBears;

public class View extends Application{
	private final int windowHeight = 1080;
	private final int windowLength = 550;
	private ImageView bearLeft = new ImageView();
	private ImageView bearRight = new ImageView();
	private ArrayList<ImageView> iceBlocks = new ArrayList<ImageView>();
	private IceAndBears game = new IceAndBears();

	@Override
	public void start(Stage stage) {
		//set up the bears
		bearLeft.setImage(new Image("file:res/BearStandingLeft.png"));
		bearLeft.setSmooth(true);
		bearLeft.setPreserveRatio(true);
		bearLeft.setFitWidth(windowLength/3);	
		bearRight.setImage(new Image("file:res/BearStandingRight.png"));
		bearRight.setSmooth(true);
		bearRight.setPreserveRatio(true);
		bearRight.setFitWidth(windowLength/3);
		//set up the ice
		
		//set up the background
		BackgroundImage background = new BackgroundImage(new Image("file:res/lifes_a_beach_and_ur_here_dude.png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			new BackgroundSize(windowLength, windowHeight, false, false, false, true));
		//create a new pane and add everything to it
		Pane root = new Pane();
		root.setBackground(new Background(background));
		//put the bears in the bear box and position them
		HBox bearBox = new HBox(bearLeft, bearRight);
		bearBox.setSpacing(bearLeft.getFitWidth()/2);
		bearBox.setLayoutX(windowLength/2);
		bearBox.setLayoutY((bearLeft.getFitHeight() + windowHeight/6));
		root.getChildren().add(bearBox);
		//add the root to a new scene
        Scene scene = new Scene(root, windowHeight, windowLength);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setIceBlocks() {
    	iceBlocks.clear();
		for (int i = 0; i < 3; i++) {
			if (game.getIceTypes().get(i).equals("base")) {
				iceBlock.add(new ImageView(new Image("file:res/SnowBlockBase.png")));
			} else if (game.getIceTypes().get(i).equals("left")) {
				iceBlock.add(new ImageView(new Image("file:res/SnowBlockLeft.png")));
			} else if (game.getIceTypes().get(i).equals("right")) {
				iceBlock.add(new ImageView(new Image("file:res/SnowBlockRight.png")));
			} else if (game.getIceTypes().get(i).equals("log")) {
				iceBlock.add(new ImageView(new Image("file:res/SnowBlockLog.png")));
			}
		}
    }
}