package com.imarwan.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer, scoreTV, question, result;
    CountDownTimer countDownTimer;
    Button btn, option, btn1, btn2, btn3, btn4;
    ConstraintLayout firstLayout, secondLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();;
    int locationOfCorectAnswer;
    int score, count, sum;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstLayout = (ConstraintLayout) findViewById(R.id.firstLayout);
        secondLayout = (ConstraintLayout) findViewById(R.id.secondLayout);
        btn = (Button) findViewById(R.id.stratBtn);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button1);
        btn3 = (Button) findViewById(R.id.button2);
        btn4 = (Button) findViewById(R.id.button3);
        timer = (TextView) findViewById(R.id.timer);
        scoreTV = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);
        result = (TextView) findViewById(R.id.result);

        secondLayout.setVisibility(View.INVISIBLE);

    }

    public void startGame(View view){
        firstLayout.setVisibility(View.INVISIBLE);
        secondLayout.setVisibility(View.VISIBLE);
        theGame();
    }

    public void theGame(){
        countDownTimer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) millisUntilFinished/1000;
                timer.setText(String.valueOf(second));
            }

            @Override
            public void onFinish() {
               result.setText("The correct answers are: " + score);
                timer.setText("0");
                disableBtn();
            }
        };
        countDownTimer.start();
        setQuestionAndOptions();
    }

    public void setQuestionAndOptions(){
        int rand1 = random.nextInt(100);
        int rand2 = random.nextInt(100);
        sum = rand1 + rand2;

        String q = String.valueOf(rand1) + " + " + String.valueOf(rand2);
        question.setText(q);

        updateButton();
    }

    public void click(View view){
        option = (Button) view;

        int answer = (int) option.getTag();

        if (answer == sum){
           score++;
            count++;
            scoreTV.setText(String.valueOf(score) + "/" + String.valueOf(count));
            result.setText("Correct");
        }
        else {
            count++;
            scoreTV.setText(String.valueOf(score) + "/" + String.valueOf(count));
            result.setText("Wrong");
        }

        setQuestionAndOptions();
    }

    public void updateButton(){
        locationOfCorectAnswer = random.nextInt(4);
        answers.clear();

        for (int i = 0; i<4; i++){
            if(i == locationOfCorectAnswer)
                answers.add(sum);
            else  {
                int wrongAnswer = random.nextInt(130);

                while (wrongAnswer == sum)
                    wrongAnswer = random.nextInt(130);


                answers.add(wrongAnswer);
            }
        }

        btn1.setText(String.valueOf(answers.get(0)));
        btn1.setTag(answers.get(0));

        btn2.setText(String.valueOf(answers.get(1)));
        btn2.setTag(answers.get(1));

        btn3.setText(String.valueOf(answers.get(2)));
        btn3.setTag(answers.get(2));

        btn4.setText(String.valueOf(answers.get(3)));
        btn4.setTag(answers.get(3));
    }

    public void disableBtn(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
    }




}
