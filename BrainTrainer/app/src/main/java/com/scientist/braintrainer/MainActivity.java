package com.scientist.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    int locationOfCorrectAnswer;
    ArrayList<Integer> options = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        String sum = a + " + " + b;
        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        options.clear();

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                options.add(a + b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                options.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(options.get(0)));
        button1.setText(Integer.toString(options.get(1)));
        button2.setText(Integer.toString(options.get(2)));
        button3.setText(Integer.toString(options.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setText("Correct");
            score++;
        }
        else{
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) +"/"+ Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }


}