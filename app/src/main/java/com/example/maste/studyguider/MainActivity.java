package com.example.maste.studyguider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int answer1 = R.id.flashAnswer1;
    private final int answer2 = R.id.flashAnswer2;
    private final int answer3 = R.id.flashAnswer3;
    private boolean isAnsA = false;
    private boolean isAnsB = false;
    private boolean isAnsC = true;
    private boolean isVisible = true;
    private FlashcardDatabase flashData;
    private List<Flashcard> flashDeck;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashData = new FlashcardDatabase(this);
        flashDeck = flashData.getAllCards();

        updateView(0);

        findViewById(answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnsA) {
                    //Set to correct colors
                    findViewById(answer1).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                    ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                } else {
                    //Set to wrong colors
                    if (isAnsB) {
                        findViewById(answer2).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    } else if (isAnsC) {
                        findViewById(answer3).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    }

                    findViewById(answer1).setBackground(getDrawable(R.drawable.small_rounded_shape_wrong));
                    ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                }
            }
        });

        findViewById(answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnsB) {
                    //Set to correct colors
                    findViewById(answer2).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                    ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                } else {
                    //Set to wrong colors
                    if (isAnsA) {
                        findViewById(answer1).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    } else if (isAnsC) {
                        findViewById(answer3).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    }

                    findViewById(answer2).setBackground(getDrawable(R.drawable.small_rounded_shape_wrong));
                    ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                }
            }
        });

        findViewById(answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnsC) {
                    //Set to correct colors
                    findViewById(answer3).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                    ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                } else {
                    //Set to wrong colors
                    if (isAnsA) {
                        findViewById(answer1).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer1)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    } else if (isAnsB) {
                        findViewById(answer2).setBackground(getDrawable(R.drawable.small_rounded_shape_correct));
                        ((TextView) findViewById(answer2)).setTextColor(getResources().getColor(R.color.colorAnswerTextCorrect, null));
                    }

                    findViewById(answer3).setBackground(getDrawable(R.drawable.small_rounded_shape_wrong));
                    ((TextView) findViewById(answer3)).setTextColor(getResources().getColor(R.color.colorAnswerTextWrong, null));
                }
            }
        });

        findViewById(R.id.mainScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Resets view to default
                updateView(1);
            }
        });

        findViewById(R.id.visibleToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_invisible);
                    isVisible = false;
                    toggleVisibility(0);
                } else if (flashDeck.size() > 0) {
                    ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_visible);
                    isVisible = true;
                    toggleVisibility(0);
                }

                if (!isVisible) {
                    flashDeck = flashData.getAllCards();

                    for (Flashcard f : flashDeck
                            ) {
                        Log.d("DatabaseCollection", f.toString());
                    }
                    Log.d("DatabaseIndex", Integer.toString(currentIndex));
                    Log.d("DatabaseSize", Integer.toString(flashDeck.size()));
                }
            }
        });

        findViewById(R.id.cardAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddQuestion.class);
                MainActivity.this.startActivityForResult(intent, 50);
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
                data.putExtra("isAnswerA", isAnsA);
                data.putExtra("isAnswerB", isAnsB);
                data.putExtra("isAnswerC", isAnsC);
                MainActivity.this.startActivityForResult(data, 100);
            }
        });

        findViewById(R.id.cardNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < flashDeck.size() - 1)
                    currentIndex++;
                else {
                    currentIndex = 0;
                    Snackbar.make(findViewById(R.id.mainScreen), "Reached last card. Going to first", Snackbar.LENGTH_SHORT).show();
                }
                updateView(1);
            }
        });

        findViewById(R.id.cardPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0)
                    currentIndex--;
                else {
                    currentIndex = flashDeck.size() - 1;
                    Snackbar.make(findViewById(R.id.mainScreen), "Reached first card. Going to last", Snackbar.LENGTH_SHORT).show();
                }
                updateView(1);
            }
        });
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            if (resultCode == -5) {
                Log.d("DatabaseCollection", "Deleted card");
                try {
                    flashData.deleteCard(((TextView) findViewById(R.id.flashQuestion)).getText().toString());
                    Snackbar.make(findViewById(R.id.mainScreen), "Card successfully deleted.", Snackbar.LENGTH_SHORT).show();
                    flashDeck = flashData.getAllCards();
                    updateView(-1);
                } catch (Exception e) {
                    Log.e("DatabaseError", e.getLocalizedMessage(), e.fillInStackTrace());
                }
            }
            return;
        }

        String question = data.getExtras().getString("question");
        String answerA = data.getExtras().getString("answerA");
        String answerB = data.getExtras().getString("answerB");
        String answerC = data.getExtras().getString("answerC");
        ((TextView) findViewById(R.id.flashQuestion)).setText(question);
        isAnsA = data.getBooleanExtra("markA", false);
        isAnsB = data.getBooleanExtra("markB", false);
        isAnsC = data.getBooleanExtra("markC", false);

        int set;
        if (isAnsA)
            set = 1;
        else if (isAnsB)
            set = 2;
        else
            set = 3;

        if (requestCode == 50) {
            Snackbar.make(findViewById(R.id.mainScreen), "Card successfully created.", Snackbar.LENGTH_SHORT).show();
            flashData.insertCard(new Flashcard(question, answerA, answerB, answerC, set));
            currentIndex = flashData.getAllCards().size() - 1;
        } else if (requestCode == 100) {
            Snackbar.make(findViewById(R.id.mainScreen), "Card successfully edited.", Snackbar.LENGTH_SHORT).show();
            Flashcard flashcard = flashDeck.get(currentIndex);
            flashcard.setQuestion(question);
            flashcard.setChoice1(answerA);
            flashcard.setChoice2(answerB);
            flashcard.setChoice3(answerC);
            flashcard.setCorrect(set);
            flashData.updateCard(flashcard);
        }

        updateView(1);
    }

    private void updateView(int source) {
        flashDeck = flashData.getAllCards();
        try {
            resetView();
            ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_visible);
            isVisible = true;
            toggleVisibility(2);

            if (source == -1 || source == 1) {
                if (source == -1)
                    currentIndex = 0;

                ((TextView) findViewById(R.id.flashQuestion)).setText(flashDeck.get(currentIndex).getQuestion());
                ((TextView) findViewById(R.id.flashAnswer1)).setText(flashDeck.get(currentIndex).getChoice1());
                ((TextView) findViewById(R.id.flashAnswer2)).setText(flashDeck.get(currentIndex).getChoice2());
                ((TextView) findViewById(R.id.flashAnswer3)).setText(flashDeck.get(currentIndex).getChoice3());

                int set = flashDeck.get(currentIndex).getCorrect();
                if (set == 1) {
                    isAnsA = true;
                    isAnsB = false;
                    isAnsC = false;
                } else if (set == 2) {
                    isAnsA = false;
                    isAnsB = true;
                    isAnsC = false;
                } else if (set == 3) {
                    isAnsA = false;
                    isAnsB = false;
                    isAnsC = true;
                }
            } else if (source == 0) {
                ((TextView) findViewById(R.id.flashQuestion)).setText(flashDeck.get(0).getQuestion());
                ((TextView) findViewById(R.id.flashAnswer1)).setText(flashDeck.get(0).getChoice1());
                ((TextView) findViewById(R.id.flashAnswer2)).setText(flashDeck.get(0).getChoice2());
                ((TextView) findViewById(R.id.flashAnswer3)).setText(flashDeck.get(0).getChoice3());

                int set = flashDeck.get(0).getCorrect();
                if (set == 1) {
                    isAnsA = true;
                    isAnsB = false;
                    isAnsC = false;
                } else if (set == 2) {
                    isAnsA = false;
                    isAnsB = true;
                    isAnsC = false;
                } else if (set == 3) {
                    isAnsA = false;
                    isAnsB = false;
                    isAnsC = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (!flashDeck.isEmpty()) {
                ((TextView) findViewById(R.id.flashQuestion)).setText(flashDeck.get(0).getQuestion());
                ((TextView) findViewById(R.id.flashAnswer1)).setText(flashDeck.get(0).getChoice1());
                ((TextView) findViewById(R.id.flashAnswer2)).setText(flashDeck.get(0).getChoice2());
                ((TextView) findViewById(R.id.flashAnswer3)).setText(flashDeck.get(0).getChoice3());
            } else {
                ((TextView) findViewById(R.id.flashQuestion)).setText(R.string.Default);
                ((TextView) findViewById(R.id.flashAnswer1)).setText("");
                ((TextView) findViewById(R.id.flashAnswer2)).setText("");
                ((TextView) findViewById(R.id.flashAnswer3)).setText("");
                ((ImageView) findViewById(R.id.visibleToggle)).setImageResource(R.drawable.eye_invisible);
                isVisible = false;
                toggleVisibility(1);
            }
        }
    }

    private void resetView() {
        findViewById(answer1).setBackground(getDrawable(R.drawable.small_rounded_shape_base));
        ((TextView) findViewById(answer1)).setTextColor(Color.WHITE);
        findViewById(answer2).setBackground(getDrawable(R.drawable.small_rounded_shape_base));
        ((TextView) findViewById(answer2)).setTextColor(Color.WHITE);
        findViewById(answer3).setBackground(getDrawable(R.drawable.small_rounded_shape_base));
        ((TextView) findViewById(answer3)).setTextColor(Color.WHITE);
    }

    private void toggleVisibility(int source) {
        if (source == 1) {
            findViewById(R.id.cardEdit).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardNext).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardPrevious).setVisibility(View.INVISIBLE);
        } else if (source == 2) {
            findViewById(R.id.cardEdit).setVisibility(View.VISIBLE);
            findViewById(R.id.cardNext).setVisibility(View.VISIBLE);
            findViewById(R.id.cardPrevious).setVisibility(View.VISIBLE);
        }

        if (isVisible) {
            findViewById(answer1).setVisibility(View.VISIBLE);
            findViewById(answer2).setVisibility(View.VISIBLE);
            findViewById(answer3).setVisibility(View.VISIBLE);
        } else {
            findViewById(answer1).setVisibility(View.INVISIBLE);
            findViewById(answer2).setVisibility(View.INVISIBLE);
            findViewById(answer3).setVisibility(View.INVISIBLE);
        }
    }
}
