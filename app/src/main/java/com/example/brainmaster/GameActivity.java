package com.example.brainmaster;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView timer;
    TextView score;
    TextView problem;
    Button option1;
    Button option2;
    Button option3;
    Button option4;

    ArrayList<Integer> answer = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Random random = new Random();

        timer = (TextView) findViewById(R.id.timer_TV);
        score = (TextView) findViewById(R.id.score_TV);
        problem = (TextView) findViewById(R.id.problem_TV);
        option1 = (Button) findViewById(R.id.optionOne);
        option2 = (Button) findViewById(R.id.optionTwo);
        option3 = (Button) findViewById(R.id.optionThree);
        option4 = (Button) findViewById(R.id.optionFour);

        int a = random.nextInt(101);
        int b = random.nextInt(501);

        char operator = '?';
        int value = 0;

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

        int correctAnswerLocation = random.nextInt(4);

        for (int i=0; i<4; i++) {
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
}