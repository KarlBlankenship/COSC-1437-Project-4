/*
// Karl Blankenship
// COSC 1437
// Project IV - Select the Red Card Game
// July 2018
// Filename: MainCardGameActivity.java
*/

package com.example.karl.karlblankenshipcosc1437projectiv;

// The following imports are required for game specific actions.
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

/**
 * Main class that will contain the Select a Red Card Game.
 * @author Karl Blankenship
 */
public class MainCardGameActivity extends Activity {

    // Create required object reference variables.
    Button startButton;
    Button buttonLeft;
    Button buttonMiddle;
    Button buttonRight;
    ImageView cardViewLeft;
    ImageView cardViewMiddle;
    ImageView cardViewRight;
    TextView textViewMessage;

    // Create public variables to contain card positions.
    public int leftCard;
    public int middleCard;
    public int rightCard;

    // Create a public variable to flag whether the game is running or complete.
    public boolean gameRunning = false;

    /**
     * The main method which is the starting point for this java program.
      * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card_game);

        // Extract references for the object reference variables.
        startButton = (Button) findViewById(R.id.startButton);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonMiddle = (Button) findViewById(R.id.buttonMiddle);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        cardViewLeft = (ImageView) findViewById(R.id.cardViewLeft);
        cardViewMiddle = (ImageView) findViewById(R.id.cardViewMiddle);
        cardViewRight = (ImageView) findViewById(R.id.cardViewRight);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        // Adds event handler for startButton.
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start the game sequence if the game state is false, not currently in a game.
                if (!gameRunning) {
                    gameRunning = true; // Set to true to indicate game has started.

                    // Set button text and text color or reset from previous game.
                    buttonLeft.setTextColor(Color.BLACK);
                    buttonLeft.setText("Left Card");
                    buttonMiddle.setTextColor(Color.BLACK);
                    buttonMiddle.setText("Middle Card");
                    buttonRight.setTextColor(Color.BLACK);
                    buttonRight.setText("Right Card");

                    // Set the card animation to slide off screen to the left.
                    Animation animation1 =
                            AnimationUtils.makeOutAnimation(getApplication(), false);
                    // Set the card animation to slide off screen to the right.
                    Animation animation3 =
                            AnimationUtils.makeOutAnimation(getApplication(), true);

                    // Animate each card to slide off to left or right and change image to face down.
                    cardViewLeft.startAnimation(animation1);
                    cardViewLeft.setImageResource(R.drawable.face_down_icon);
                    cardViewMiddle.startAnimation(animation1);
                    cardViewMiddle.setImageResource(R.drawable.face_down_icon);
                    cardViewRight.startAnimation(animation3);
                    cardViewRight.setImageResource(R.drawable.face_down_icon);

                    // Determine the random order of the cards.
                    determineRandomOrder();

                    // Change message to ask user to select the red card.
                    textViewMessage.setText("Please Select the RED Card.");

                    // Left button click listener to respond to selection of the left card.
                    buttonLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // If game is running, perform analysis. Otherwise ignore the click.
                            if (gameRunning) {
                                // Change button text color and message.
                                buttonLeft.setTextColor(Color.GREEN);
                                buttonLeft.setText("Selected");

                                // Display the cards in the randomized order.
                                displayRandomCards(leftCard, middleCard, rightCard);

                                // determine win and display message
                                determineWinLose(leftCard);

                                // Set to false so that is another button is selected prior to
                                // restarting the game, the button will not respond.
                                gameRunning = false;
                            }
                        }
                    });

                    // Middle button click listener to respond to selection of the middle card.
                    buttonMiddle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // If game is running, perform analysis. Otherwise ignore the click.
                            if (gameRunning) {
                                // Change button text color and message.
                                buttonMiddle.setTextColor(Color.GREEN);
                                buttonMiddle.setText("Selected");

                                // Display the cards in the randomized order.
                                displayRandomCards(leftCard, middleCard, rightCard);

                                // determine win and display message
                                determineWinLose(middleCard);

                                // Set to false so that is another button is selected prior to
                                // restarting the game, the button will not respond.
                                gameRunning = false;
                            }
                        }
                    });

                    // Right button click listener to respond to selection of the right card.
                    buttonRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // If game is running, perform analysis. Otherwise ignore the click.
                            if (gameRunning) {
                                // Change button text color and message.
                                buttonRight.setTextColor(Color.GREEN);
                                buttonRight.setText("Selected");

                                // Display the cards in the randomized order.
                                displayRandomCards(leftCard, middleCard, rightCard);

                                // determine win and display message
                                determineWinLose(rightCard);

                                // Set to false so that is another button is selected prior to
                                // restarting the game, the button will not respond.
                                gameRunning = false;
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * This method accepts no parameters and returns no value but it does randomly
     * assign the numbers 7, 8 and 9 corresponding to the game card to the left,
     * middle and right positions in the game.
     */
    public void determineRandomOrder() {
        // Create a new random object.
        Random randomCard = new Random();

        // Initialize left card value as 7, 8 or 9 randomly.
        leftCard = randomCard.nextInt(3) + 7;

        // Initialize middle card value as equal to left card entering while loop.
        middleCard = leftCard;

        // Select random values for middle card until it is different from left card.
        while (middleCard == leftCard) {
            middleCard = randomCard.nextInt(3) + 7;
        }

        // Initialize right card and set it equal to middle card entering while loop.
        rightCard = middleCard;
        // select random values until right card is different from other cards.
        while (rightCard == middleCard || rightCard == leftCard) {
            rightCard = randomCard.nextInt(3) + 7;
        }
    }

    /**
     * This method accepts the randomly determined card values for the left, middle and
     * right cards and will display the cards on screen face up in that order.
     * @param left
     * @param middle
     * @param right
     */
    public void displayRandomCards(int left, int middle, int right) {
        if (left == 7 && middle == 8 && right == 9) {
            cardViewLeft.setImageResource(R.drawable.card_7_icon);
            cardViewMiddle.setImageResource(R.drawable.card_8_icon);
            cardViewRight.setImageResource(R.drawable.card_9_icon);}
        else if (left == 7 && middle == 9 && right == 8) {
            cardViewLeft.setImageResource(R.drawable.card_7_icon);
            cardViewMiddle.setImageResource(R.drawable.card_9_icon);
            cardViewRight.setImageResource(R.drawable.card_8_icon);}
        else if (left == 8 && middle == 7 && right == 9) {
            cardViewLeft.setImageResource(R.drawable.card_8_icon);
            cardViewMiddle.setImageResource(R.drawable.card_7_icon);
            cardViewRight.setImageResource(R.drawable.card_9_icon);}
        else if (left == 8 && middle == 9 && right == 7) {
            cardViewLeft.setImageResource(R.drawable.card_8_icon);
            cardViewMiddle.setImageResource(R.drawable.card_9_icon);
            cardViewRight.setImageResource(R.drawable.card_7_icon);}
        else if (left == 9 && middle == 7 && right == 8) {
            cardViewLeft.setImageResource(R.drawable.card_9_icon);
            cardViewMiddle.setImageResource(R.drawable.card_7_icon);
            cardViewRight.setImageResource(R.drawable.card_8_icon);}
        else {
            cardViewLeft.setImageResource(R.drawable.card_9_icon);
            cardViewMiddle.setImageResource(R.drawable.card_8_icon);
            cardViewRight.setImageResource(R.drawable.card_7_icon);}
    }

    /**
     * This method will accept the value of the card associated with the button that has been
     * clicked and will determine if that button/card image was the red 7.
     * @param x
     */
    public void determineWinLose(int x) {
        if (x == 7)
            textViewMessage.setText("You WIN!! Please press Start to play again.");
        else
            textViewMessage.setText("You lose. Please press Start to play again.");
    }
}
