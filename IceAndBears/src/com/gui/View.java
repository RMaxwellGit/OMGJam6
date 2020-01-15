package com.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Pos;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import com.iceandbears.IceAndBears;

public class View extends Application{
	private final int windowHeight = 1080;
	private final int windowLength = 550;
	private ImageView bearLeft = new ImageView();
	private ImageView bearRight = new ImageView();
	private ImageView stove = new ImageView();
	private ImageView iceTop, iceMid, iceBot;
	private Image baseBlock = new Image("file:res/SnowBlockBase.png");
	private Image leftBlock = new Image("file:res/SnowBlockLeft.png");
	private Image rightBlock = new Image("file:res/SnowBlockRight.png");
	private Image logBlock = new Image("file:res/SnowBlockLog.png");
	private Image stoveFull = new Image("file:res/StoveFull.png");
	private Image stoveMid = new Image("file:res/StoveMid.png");
	private Image stoveLow = new Image("file:res/StoveLow.png");
	private Image stoveDone = new Image("file:res/StoveDone.png");
	private IceAndBears game = new IceAndBears();
	private AnimationTimer leftTimer;
	private AnimationTimer rightTimer;
	private long leftFrames = 0;
	private long rightFrames = 0;
	private Timeline timer;
	private Stage globalStage;

	@Override
	public void start(Stage stage) {
		//set up the bears
		bearLeft.setSmooth(true);
		bearLeft.setPreserveRatio(true);
		bearLeft.setFitWidth(windowLength/3);
		bearRight.setSmooth(true);
		bearRight.setPreserveRatio(true);
		bearRight.setFitWidth(windowLength/3);

		setBear("standing", "Left", bearLeft);
		setBear("standing", "Right", bearRight);

		//set up the ice (this will go in the bearBox)
		stove.setSmooth(true);
		stove.setPreserveRatio(true);
		stove.setFitWidth(windowLength/3);
		setStove(3);

		//put the bears in the bear box and position them
		HBox bearBox = new HBox(bearLeft, stove, bearRight);
		bearBox.setSpacing(0);
		bearBox.setAlignment(Pos.BOTTOM_CENTER);
		bearBox.setLayoutX(windowLength/2 - windowLength/12);
		bearBox.setLayoutY(20);

		//set up the ice
		iceTop = new ImageView(baseBlock);
		iceTop.setSmooth(true);
		iceTop.setPreserveRatio(true);
		iceTop.setFitWidth(windowLength/2);
		iceMid = new ImageView(leftBlock);
		iceMid.setSmooth(true);
		iceMid.setPreserveRatio(true);
		iceMid.setFitWidth(windowLength/2);
		iceBot = new ImageView(rightBlock);
		iceBot.setSmooth(true);
		iceBot.setPreserveRatio(true);
		iceBot.setFitWidth(windowLength/2);
		VBox iceBox = new VBox(iceTop, iceMid, iceBot);
		iceBox.setAlignment(Pos.BOTTOM_CENTER);
		iceBox.setLayoutX(windowLength/2 + bearLeft.getFitWidth()/2);
		iceBox.setLayoutY(310);

		//set up the background
		BackgroundImage background = new BackgroundImage(new Image("file:res/lifes_a_beach_and_ur_here_dude.png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			new BackgroundSize(windowLength, windowHeight, false, false, false, true));

		//create a new pane and add everything to it
		Pane root = new Pane();
		root.setBackground(new Background(background));
		root.getChildren().add(iceBox);
		root.getChildren().add(bearBox);

		//add the root to a new scene
        Scene scene = new Scene(root, windowHeight, windowLength);

        //set up controls within the scene
        scene.setOnKeyPressed(e -> {
        	boolean iceUpdate = false;
		    if ((e.getCode() == KeyCode.A) && (leftFrames == 0)) {
				animateLeftBear();
		        iceUpdate = game.bearDigs("left");
		    } else if ((e.getCode() == KeyCode.D) && (rightFrames == 0)) {
		    	animateRightBear();
		        iceUpdate = game.bearDigs("right");
		    } else if ((e.getCode() == KeyCode.W) && (leftFrames == 0) && (rightFrames == 0)) {
		    	animateLeftBear();
		    	animateRightBear();
		    	iceUpdate = game.bearDigs("up");
		    	game.getStove().addLog();
	            setStove(game.getStove().getState());
		    }

		    if (iceUpdate) {
		    	setIceBlocks();
		    }
		});

		//start the timer for the stove
		timer = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
	            setStove(game.getStove().getState());
	            game.getStove().decrease();
		}));
		timer.setCycleCount(Animation.INDEFINITE);
    	timer.play();

		//set the timer to stop when the stage is closed
		stage.setOnCloseRequest(event -> {
		    timer.stop();
		});

        stage.setScene(scene);

        globalStage = stage;

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setIceBlocks() {
    	//rewrite this to not be all ArrayList-y because using consistant imageview instance vars works for the bears
    	ArrayList<Image> temp = new ArrayList<Image>();

		for (int i = 0; i < 3; i++) {
			if (game.getIceTypes().get(i).equals("base")) {
				temp.add(baseBlock);
			} else if (game.getIceTypes().get(i).equals("left")) {
				temp.add(leftBlock);
			} else if (game.getIceTypes().get(i).equals("right")) {
				temp.add(rightBlock);
			} else if (game.getIceTypes().get(i).equals("log")) {
				temp.add(logBlock);
			}
		}

		iceTop.setImage(temp.get(0));
		iceMid.setImage(temp.get(1));
		iceBot.setImage(temp.get(2));
    }

    private void setBear(String state, String side, ImageView b) {
    	if (state.equals("standing")) {
			b.setImage(new Image("file:res/BearStanding" + side + ".png"));
    	} else if (state.equals("digging")) {
			b.setImage(new Image("file:res/BearDigging" + side + ".png"));
		}else if (state.equals("throwing")) {
			b.setImage(new Image("file:res/BearThrowing" + side + ".png"));
		} else if (state.equals("fallen")) {
			b.setImage(new Image("file:res/BearFallen" + side + ".png"));
    	}
    }

    private void animateLeftBear() {
    	leftFrames = 0;
    	leftTimer = new AnimationTimer() {
        	@Override
        	public void handle(long now) {
        		leftFrames++;
        		if (leftFrames < 10){
        			setBear("digging", "Left", bearLeft);
        		} else if (leftFrames >= 10 && leftFrames < 20) {
        			setBear("throwing", "Left", bearLeft);
        		} else if (leftFrames >= 20) {
        			setBear("standing", "Left", bearLeft);
        			leftFrames = 0;
        			leftTimer.stop();
        		}
        	}
        };
    	leftTimer.start();
    }

    private void animateRightBear() {
    	rightFrames = 0;
    	rightTimer = new AnimationTimer() {
        	@Override
        	public void handle(long now) {
        		rightFrames++;
        		if (rightFrames < 10){
        			setBear("digging", "Right", bearRight);
        		} else if (rightFrames >= 10 && rightFrames < 20) {
        			setBear("throwing", "Right", bearRight);
        		} else if (rightFrames >= 20) {
        			setBear("standing", "Right", bearRight);
        			rightFrames = 0;
        			rightTimer.stop();
        		}
        	}
        };
    	rightTimer.start();
    }

    private void setStove(int state) {
    	switch(state) {
    		case -1:
    			globalStage.close();
    			break;
	    	case 0:
	    		stove.setImage(stoveDone);
	    		break;
	    	case 1:
	    		stove.setImage(stoveLow);
	    		break;
	    	case 2:
	    		stove.setImage(stoveMid);
	    		break;
	    	case 3:
	    		stove.setImage(stoveFull);
	    		break;
	    }
    }

}