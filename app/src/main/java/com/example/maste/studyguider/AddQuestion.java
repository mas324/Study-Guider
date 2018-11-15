package com.example.maste.studyguider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddQuestion extends AppCompatActivity {

    private EditText question;
    private EditText ansA;
    private EditText ansB;
    private EditText ansC;
    private RadioButton selectA;
    private RadioButton selectB;
    private RadioButton selectC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        question = findViewById(R.id.newQuestion);
        ansA = findViewById(R.id.newAnswerA);
        ansB = findViewById(R.id.newAnswerB);
        ansC = findViewById(R.id.newAnswerC);
        selectA = findViewById(R.id.selectAnswerA);
        selectB = findViewById(R.id.selectAnswerB);
        selectC = findViewById(R.id.selectAnswerC);

        if (getIntent().getExtras() != null) {
            question.setText(getIntent().getStringExtra("currentQuestion"));
            ansA.setText(getIntent().getStringExtra("currentAnswerA"));
            ansB.setText(getIntent().getStringExtra("currentAnswerB"));
            ansC.setText(getIntent().getStringExtra("currentAnswerC"));

            selectA.setChecked(getIntent().getBooleanExtra("isAnswerA", false));
            selectB.setChecked(getIntent().getBooleanExtra("isAnswerB", false));
            selectC.setChecked(getIntent().getBooleanExtra("isAnswerC", false));

            findViewById(R.id.cardDelete).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.cardCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.cardDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-5);
                finish();
            }
        });

        findViewById(R.id.cardSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.length() != 0 && ansA.length() != 0 && ansB.length() != 0 && ansC.length() != 0 && (selectA.isChecked() || selectB.isChecked() || selectC.isChecked())) {
                    Intent result = new Intent();
                    result.putExtra("question", question.getText().toString());
                    result.putExtra("answerA", ansA.getText().toString());
                    result.putExtra("answerB", ansB.getText().toString());
                    result.putExtra("answerC", ansC.getText().toString());
                    result.putExtra("markA", selectA.isChecked());
                    result.putExtra("markB", selectB.isChecked());
                    result.putExtra("markC", selectC.isChecked());
                    setResult(RESULT_OK, result);
                    finish();
                } else if (question.length() == 0 || ansA.length() == 0 || ansB.length() == 0 || ansC.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Must fill in all fields!", Toast.LENGTH_SHORT).show();
                } else if (!selectA.isChecked() && !selectB.isChecked() && !selectC.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Must mark an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
