package com.scientist.tictactoe;

import static com.scientist.tictactoe.R.string.draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
//    0 = Yellow, 1 = Red, 2 = Unplaced

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameIsActive = true;
    int activePlayer = 0;
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());

        if (gameState[tapped] == 2 && gameIsActive){
            gameState[tapped] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(160).setDuration(300);

            for (int[] winningPosition:winningPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2){

                    gameIsActive = false;
                    String winner = "Yellow";

                    if (gameState[winningPosition[0]] == 1) {
                        winner = "Red";
                    }
                    TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);
                    winnerMsg.setText(String.format("%s Has Won", winner));

                    LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameOver = true;

                    for (int state:gameState) {
                        if (state == 2) gameOver = false;
                    }

                    if (gameOver){
                        TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);
                        winnerMsg.setText(draw);

                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        activePlayer = 0;

        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        Arrays.fill(gameState, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}