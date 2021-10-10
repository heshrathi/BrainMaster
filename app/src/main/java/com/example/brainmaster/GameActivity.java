package com.example.brainmaster;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView timer;
    TextView scoreText;
    TextView problem;
    TextView resultText;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button restart;

    ArrayList<Integer> answer = new ArrayList<Integer>();

    int correctAnswerLocation;
    Boolean check = true;
    int score = 0;
    int numberOfQuestions = 0;


    public void restartGame() {
        restart.setAlpha(0);
        option1.setAlpha(1);
        option2.setAlpha(1);
        option3.setAlpha(1);
        option4.setAlpha(1);
        problem.setAlpha(1);

        check = true;
        score = 0;
        numberOfQuestions = 0;

        new CountDownTimer(5400, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultText.setText("Time Up!!");
                restart.setAlpha(1);
                option1.setAlpha(0);
                option2.setAlpha(0);
                option3.setAlpha(0);
                option4.setAlpha(0);
                problem.setAlpha(0);

                newQuestion();
            }
        }.start();

    }

    public void newQuestion() {

        Random random = new Random();

        int a = random.nextInt(101);
        int b = random.nextInt(501);

        char operator = '?';
        int value = 0;

        answer.clear();

        switch (random.nextInt(4)) {
            case 0:
                operator = '+';
                value = a + b;
                break;
            case 1:
                operator = '-';
                value = a - b;
                break;
            case 2:
                operator = '*';
                value = a * b;
                break;
            case 3:
                operator = '/';
                value = a / b;
                break;
            default:
                operator = '?';
        }

        problem.setText(Integer.toString(a) + " " + operator + " " + Integer.toString(b));

        correctAnswerLocation = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerLocation)
                answer.add(value);
            else {
                int wrongAnswer = random.nextInt(6001);

                while (wrongAnswer == value)
                    wrongAnswer = random.nextInt(6001);

                answer.add(wrongAnswer);
            }
        }

        option1.setText(Integer.toString(answer.get(0)));
        option2.setText(Integer.toString(answer.get(1)));
        option3.setText(Integer.toString(answer.get(2)));
        option4.setText(Integer.toString(answer.get(3)));
    }

    public void correctAnswer(View view) {

        //if (check == true) {
        scoreText.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));

        numberOfQuestions++;

        if (Integer.toString(correctAnswerLocation).equals(view.getTag().toString())) {
            resultText.setAlpha(1);
            resultText.setText("Correct Answer");
            score++;
        } else {
            resultText.setAlpha(1);
            resultText.setText("Wrong Answer");
        }
        //check = false;

        scoreText.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        //}

        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timer = (TextView) findViewById(R.id.timer_TV);
        scoreText = (TextView) findViewById(R.id.score_TV);
        problem = (TextView) findViewById(R.id.problem_TV);
        option1 = (Button) findViewById(R.id.optionOne);
        option2 = (Button) findViewById(R.id.optionTwo);
        option3 = (Button) findViewById(R.id.optionThree);
        option4 = (Button) findViewById(R.id.optionFour);
        resultText = findViewById(R.id.gameStatus_TextView);
        restart = findViewById(R.id.restartBtn);

        newQuestion();
        restartGame();

    }
}