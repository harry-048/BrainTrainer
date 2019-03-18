package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView result;
    int score=0;
    int noOfQuestions=0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    Button goButton;
    ConstraintLayout gameLayout;
    GridLayout buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        result = (TextView) findViewById(R.id.result);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgain = (Button) findViewById(R.id.btnPlayAgain);
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
        buttonLayout= (GridLayout) findViewById(R.id.gridLayout);
        buttonLayout.setVisibility(View.VISIBLE);



    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+ Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        for (int i=0;i<4;i++){
            if (i==locationOfCorrectAnswer){
                answer.add(a+b);
            }
            else {
                int wrongAnswer= rand.nextInt(41);
                while (wrongAnswer==a+b){
                    wrongAnswer= rand.nextInt(41);
                }
                answer.add(wrongAnswer);

            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void start(View view) {
        view.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);

    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            result.setText("Correct!");
            score++;
        }
        else{
            result.setText("Wrong :(");
        }
        noOfQuestions++;
        scoreTextView.setText(score+"/"+noOfQuestions);
        newQuestion();
    }

    public void playAgain(View view) {
        score=0;
        noOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        result.setText("");
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        buttonLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(10100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                result.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.INVISIBLE);

            }
        }.start();
    }
}
