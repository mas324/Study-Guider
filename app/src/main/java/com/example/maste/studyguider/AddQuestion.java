package com.example.maste.studyguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        findViewById(R.id.cardCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.cardSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("question", ((EditText) findViewById(R.id.newQuestion)).getText().toString());
                result.putExtra("answerA", ((EditText) findViewById(R.id.newAnswerA)).getText().toString());
                result.putExtra("answerB", ((EditText) findViewById(R.id.newAnswerB)).getText().toString());
                result.putExtra("answerC", ((EditText) findViewById(R.id.newAnswerC)).getText().toString());
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
