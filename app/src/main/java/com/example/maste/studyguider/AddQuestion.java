package com.example.maste.studyguider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestion extends AppCompatActivity {

    private EditText question;
    private EditText ans1;
    private EditText ans2;
    private EditText ans3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        question = findViewById(R.id.newQuestion);
        ans1 = findViewById(R.id.newAnswerA);
        ans2 = findViewById(R.id.newAnswerB);
        ans3 = findViewById(R.id.newAnswerC);

        if (getIntent().getExtras() != null) {
            String fillQuestion = getIntent().getStringExtra("currentQuestion");
            String fillAnswer1 = getIntent().getStringExtra("currentAnswerA");
            String fillAnswer2 = getIntent().getStringExtra("currentAnswerB");
            String fillAnswer3 = getIntent().getStringExtra("currentAnswerC");

            question.setText(fillQuestion);
            ans1.setText(fillAnswer1);
            ans2.setText(fillAnswer2);
            ans3.setText(fillAnswer3);
        }

        findViewById(R.id.cardCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.cardSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.length() != 0 && ans1.length() != 0 && ans2.length() != 0 && ans3.length() != 0) {
                    Intent result = new Intent();
                    result.putExtra("question", question.getText().toString());
                    result.putExtra("answerA", ans1.getText().toString());
                    result.putExtra("answerB", ans2.getText().toString());
                    result.putExtra("answerC", ans3.getText().toString());
                    setResult(RESULT_OK, result);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Must fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
