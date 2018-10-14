package com.example.maste.studyguider;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int answer1 = R.id.flashAnswer1;
        final int answer2 = R.id.flashAnswer2;
        final int answer3 = R.id.flashAnswer3;

        findViewById(answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to correct colors
                findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
            }
        });

        findViewById(answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to wrong colors
                findViewById(answer2).setBackgroundColor(getResources().getColor(R.color.colorAnswerWrong, null));
                ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                //Set to correct colors
                findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
            }
        });

        findViewById(answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to wrong colors
                findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerWrong, null));
                ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                //Set to correct colors
                findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
            }
        });

        findViewById(R.id.mainScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Resets view to default
                findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
                ((TextView) findViewById(answer1)).setTextColor(Color.WHITE);
                findViewById(answer2).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
                ((TextView) findViewById(answer2)).setTextColor(Color.WHITE);
                findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
                ((TextView) findViewById(answer3)).setTextColor(Color.WHITE);
            }
        });
    }
}
