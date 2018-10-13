package com.example.maste.studyguider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashQuestion).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hide the question
                findViewById(R.id.flashQuestion).setVisibility(View.INVISIBLE);
                //Show the answer
                findViewById(R.id.flashAnswer).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide the answer
                findViewById(R.id.flashAnswer).setVisibility(View.INVISIBLE);
                //Show the question
                findViewById(R.id.flashQuestion).setVisibility(View.VISIBLE);
            }
        });
    }
}
