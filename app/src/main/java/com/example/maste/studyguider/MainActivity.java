package com.example.maste.studyguider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int answer1 = R.id.flashAnswer1;
    private final int answer2 = R.id.flashAnswer2;
    private final int answer3 = R.id.flashAnswer3;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to correct colors
                findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
            }
        });

        findViewById(answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to wrong colors
                findViewById(answer2).setBackgroundColor(getResources().getColor(R.color.colorAnswerWrong, null));
                ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                //Set to correct colors
                findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
            }
        });

        findViewById(answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set to wrong colors
                findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerWrong, null));
                ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                //Set to correct colors
                findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerCorrect, null));
                ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
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

        findViewById(R.id.visibleToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_invisible);
                    findViewById(answer1).setVisibility(View.INVISIBLE);
                    findViewById(answer2).setVisibility(View.INVISIBLE);
                    findViewById(answer3).setVisibility(View.INVISIBLE);
                    isVisible = false;
                } else {
                    ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_visible);
                    findViewById(answer1).setVisibility(View.VISIBLE);
                    findViewById(answer2).setVisibility(View.VISIBLE);
                    findViewById(answer3).setVisibility(View.VISIBLE);
                    isVisible = true;
                }
            }
        });

        findViewById(R.id.cardAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddQuestion.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.cardEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(MainActivity.this, AddQuestion.class);
                data.putExtra("currentQuestion", ((TextView) findViewById(R.id.flashQuestion)).getText().toString());
                data.putExtra("currentAnswerA", ((TextView) findViewById(answer1)).getText().toString());
                data.putExtra("currentAnswerB", ((TextView) findViewById(answer2)).getText().toString());
                data.putExtra("currentAnswerC", ((TextView) findViewById(answer3)).getText().toString());
                MainActivity.this.startActivityForResult(data, 1);
            }
        });
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String string1 = data.getExtras().getString("question");
            String answerA = data.getExtras().getString("answerA");
            String answerB = data.getExtras().getString("answerB");
            String answerC = data.getExtras().getString("answerC");

            ((TextView) findViewById(R.id.flashQuestion)).setText(string1);
            ((TextView) findViewById(answer1)).setText(answerA);
            ((TextView) findViewById(answer2)).setText(answerB);
            ((TextView) findViewById(answer3)).setText(answerC);

            //Resets the answers
            findViewById(answer1).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
            ((TextView) findViewById(answer1)).setTextColor(Color.WHITE);
            findViewById(answer2).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
            ((TextView) findViewById(answer2)).setTextColor(Color.WHITE);
            findViewById(answer3).setBackgroundColor(getResources().getColor(R.color.colorAnswerBase, null));
            ((TextView) findViewById(answer3)).setTextColor(Color.WHITE);

            Snackbar.make(findViewById(R.id.mainScreen), "Card successfully created.", Snackbar.LENGTH_SHORT).show();
        }
    }
}
