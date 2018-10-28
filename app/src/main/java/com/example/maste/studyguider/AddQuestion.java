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
                Intent result = new Intent();
                result.putExtra("question", ((EditText) findViewById(R.id.newQuestion)).getText());
                result.putExtra("answer", ((EditText) findViewById(R.id.newAnswer)).getText());
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
}
