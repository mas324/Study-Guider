package com.example.maste.studyguider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.screenView).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Hide the question
                ((TextView) findViewById(R.id.question)).setVisibility(View.INVISIBLE);
                //Show the answer
                ((TextView) findViewById(R.id.answer)).setVisibility(View.VISIBLE);
            }
        });
    }
}
