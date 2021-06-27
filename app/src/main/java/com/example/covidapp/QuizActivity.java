package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton, mFalseButton, mNextButton, mFormButton, mHomeButton;
    private TextView mQuestionTextView;
    int listNumber;//a number representing which list to display (sent from intent)

    //Array to save the Astrazeneca questions into
    private Questions[] AstraQuestionArray = new Questions[]{
            new Questions(R.string.AstraZeneca1, true),
            new Questions(R.string.AstraZeneca2, true),
            new Questions(R.string.AstraZeneca3, false),
            new Questions(R.string.AstraZeneca4, false),
            new Questions(R.string.AstraZeneca5, true)
    };
    //Array to save the Pfizer questions into
    private Questions[] PfizerQuestionArray = new Questions[]{
            new Questions(R.string.Pfizer1, true),
            new Questions(R.string.Pfizer2, false),
            new Questions(R.string.Pfizer3, false),
            new Questions(R.string.Pfizer4, false),
            new Questions(R.string.Pfizer5, true)
    };
    private Questions[] SinopharmQuesionArray = new Questions[]{
            new Questions(R.string.Sino1, false),
            new Questions(R.string.Sino2, false),
            new Questions(R.string.Sino3, false),
            new Questions(R.string.Sino4, true),
            new Questions(R.string.Sino5, false)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mFormButton = findViewById(R.id.form_button);
        mHomeButton = findViewById(R.id.homepage_button);
        mHomeButton.setVisibility(View.INVISIBLE);

        //getting the value that represents which button has been clicked to generate appropriate
        // list of questions
        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        if (listNumber == 1){
            updateAstraQuestion();
        }
        else if (listNumber == 2){
            updatePfizerQuestions();
        }
        else{
            updateSinoQuestions();
        }


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex++;

                if(mCurrentIndex == AstraQuestionArray.length ||
                        mCurrentIndex == PfizerQuestionArray.length ||
                        mCurrentIndex == SinopharmQuesionArray.length){//to ensure that we haven't run out of questions
                    //mNextButton.setText("End");
                    //mNextButton.setEnabled(false);
                    mNextButton.setVisibility(View.INVISIBLE);
                    mQuestionTextView.setText("Congratulations, you finished the quiz!!");
                    mFormButton.setVisibility(View.VISIBLE);
                    mTrueButton.setVisibility(View.INVISIBLE);
                    mFalseButton.setVisibility(View.INVISIBLE);
                    mHomeButton.setVisibility(View.VISIBLE);
                }
                else {//if there are still questions
                    //updateQuestion();
                    switch (listNumber){
                        case 1:
                            updateAstraQuestion();
                            break;
                        case 2:
                            updatePfizerQuestions();
                            break;
                        default:
                            updateSinoQuestions();
                    }
                }

            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    private void updateAstraQuestion() {//loads Astrazeneca questions
        int question = AstraQuestionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void updatePfizerQuestions(){//loads Pfizer questions
        int question = PfizerQuestionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void updateSinoQuestions(){//loads sinopharm questions
        int question = SinopharmQuesionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void checkAnswer(boolean userAnswer){//Checks if the user choose the right or wrong answer
        if (listNumber == 1){
            boolean answer = AstraQuestionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else if (listNumber == 2){
            boolean answer = PfizerQuestionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            boolean answer = SinopharmQuesionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void fillForm(View view) {
        Intent intent = new Intent(this,FormActivity.class);
        intent.putExtra("QuestionListNumber", listNumber);
        startActivity(intent);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}