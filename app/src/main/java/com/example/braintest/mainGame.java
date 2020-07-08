package com.example.braintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class mainGame extends AppCompatActivity {

    TextView timerTextView,scoreTextView,questionTextView,checkTextView;
    Button option1,option2,option3,option4;
    String answer;
    int questionCount,correctAnsCount;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        checkTextView = findViewById(R.id.checkTextView);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        checkTextView.setVisibility(View.INVISIBLE);
        putRandom();
        countDownTimer = new CountDownTimer(60*1000, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText("" + l/1000);
            }

            @Override
            public void onFinish() {
                checkTextView.setText("Game Over");
                checkTextView.setVisibility(View.VISIBLE);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        }.start();
    }

    public void putRandom(){
        int num1 = new Random().nextInt(91) + 10;
        int num2 = new Random().nextInt(91) + 10;
        int ans = num1+num2;
        answer = ""+ans;
        String str = "What is " + num1 + " + " + num2 + " ?";
        questionTextView.setText(str);
        int ansPosition = new Random().nextInt(4);
        String options[] = new String[4];
        options[ansPosition] = ""+answer;
        for(int i = 0; i < 4; i++){
            if(i != ansPosition){
                int random = new Random().nextInt(91) + 10;
                options[i] = "" + random;
            }
        }
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);
        option4.setText(options[3]);
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void playAgain(View view){
        // code for game reset
        countDownTimer.cancel();
        scoreTextView.setText("Score - 0/0");
        questionCount = 0;
        correctAnsCount = 0;
        putRandom();
        countDownTimer.start();
        checkTextView.setVisibility(View.INVISIBLE);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    public void checkAnswer(View view){
        Button checkBtn = (Button) view;
        String check = checkBtn.getText().toString();
        if(check.equals(answer)){
            checkTextView.setText("Correct");
            correctAnsCount++;
        }else{
            checkTextView.setText("Wrong");
        }
        questionCount++;
        scoreTextView.setText("Score - " + correctAnsCount + "/" + questionCount);
        checkTextView.setVisibility(View.VISIBLE);
        putRandom();
    }
}