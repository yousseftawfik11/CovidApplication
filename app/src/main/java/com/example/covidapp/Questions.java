package com.example.covidapp;

public class Questions {
    private int mTextResultId;
    private boolean mTrueAnswer;

    public Questions(int mTextResultId, boolean mTrueAnswer){
        this.mTextResultId = mTextResultId;
        this.mTrueAnswer = mTrueAnswer;
    }

    public int getmTextResultId() {
        return mTextResultId;
    }

    public boolean ismTrueAnswer(){
        return mTrueAnswer;
    }
}
