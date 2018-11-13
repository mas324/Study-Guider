package com.example.maste.studyguider;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

@SuppressWarnings("NullableProblems")
@Entity
class Flashcard {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;
    @NonNull
    @ColumnInfo(name = "question")
    private String question;
    @ColumnInfo(name = "choice_1")
    private String choice1;
    @Nullable
    @ColumnInfo(name = "choice_2")
    private String choice2;
    @Nullable
    @ColumnInfo(name = "choice_3")
    private String choice3;
    @Nullable
    @ColumnInfo(name = "correct")
    private int correct;

    Flashcard(String question, String choice1, String choice2, String choice3, int correct) {
        this.uuid = UUID.randomUUID().toString();
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.correct = correct;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Nullable
    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(@Nullable String choice1) {
        this.choice1 = choice1;
    }

    @Nullable
    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(@Nullable String choice2) {
        this.choice2 = choice2;
    }

    @Nullable
    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(@Nullable String choice3) {
        this.choice3 = choice3;
    }

    @Nullable
    public int getCorrect() {
        return correct;
    }

    public void setCorrect(@Nullable int correct) {
        this.correct = correct;
    }

    public String toString() {
        return "Flashcard: Q: " + question + " | A: " + choice1 + " | B: " + choice2 + " | C: " + choice3 + "| Cor: " + correct;
    }
}