package com.example.android.milanii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * This app displays a quiz game to the user, and calculates the score.
 */
public class MainActivity extends AppCompatActivity {

    // define the views to initialize inside onCreate() method
    CheckBox quiz1AnswerA, quiz1AnswerB, quiz1AnswerC, quiz4AnswerA, quiz4AnswerB, quiz4AnswerC;
    EditText userNameTextView, quiz2Answer;
    RadioButton quiz3AnswerB, quiz5AnswerC;

     // define the score at the beginning of the quiz game
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the views inside the onCreate method to avoid multiple reinitializing
        userNameTextView = findViewById(R.id.user_name);
        quiz1AnswerA = findViewById(R.id.quiz_1_answer_a);
        quiz1AnswerB = findViewById(R.id.quiz_1_answer_b);
        quiz1AnswerC = findViewById(R.id.quiz_1_answer_c);
        quiz2Answer = findViewById(R.id.quiz_2_answer);
        quiz3AnswerB = findViewById(R.id.quiz_3_answer_b);
        quiz4AnswerA = findViewById(R.id.quiz_4_answer_a);
        quiz4AnswerB = findViewById(R.id.quiz_4_answer_b);
        quiz4AnswerC = findViewById(R.id.quiz_4_answer_c);
        quiz5AnswerC = findViewById(R.id.quiz_5_answer_c);
    }

    /**
     * This method is called when the order button is clicked.
     * Checks user's name
     * Checks user's answers and increment score
     * Calls feedback toast
     *
     * @param view - method is called onClick of ViewButton
     */
    public void submit(View view) {

        // Check name in name_edit EditText
        String userName = (userNameTextView.getText().toString());
        if (TextUtils.isEmpty(userName)) {
            userName = "Anonymous user";
        }


        // Check Quiz 1 and assign score
        ScoreIncrementIfBooleanIsTrue(quiz1AnswerA.isChecked());
        ScoreIncrementIfBooleanIsFalse(quiz1AnswerB.isChecked());
        ScoreIncrementIfBooleanIsTrue(quiz1AnswerC.isChecked());

        // Check Quiz 2 and assign score
        String guessAnswer2 = (quiz2Answer).getText().toString().replace(" ", "");
        if (guessAnswer2.equalsIgnoreCase("Leonardo") || guessAnswer2.equalsIgnoreCase("LeonardoDaVinci")) {
            score++;
        }

        // Check Quiz 3 and assign score
        ScoreIncrementIfBooleanIsTrue(quiz3AnswerB.isChecked());

        // Check Quiz 4 and assign score
        ScoreIncrementIfBooleanIsTrue(quiz4AnswerA.isChecked());
        ScoreIncrementIfBooleanIsTrue(quiz4AnswerB.isChecked());
        ScoreIncrementIfBooleanIsFalse(quiz4AnswerC.isChecked());

        // Check Quiz 5 and assign score
        ScoreIncrementIfBooleanIsTrue(quiz5AnswerC.isChecked());

        // Display results toast
        if (score > 4) {
            Toast.makeText(this, getString(R.string.toast_good, userName, score), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.toast_nice_try, userName, score), Toast.LENGTH_LONG).show();
        }
        // Reset score value
        score = 0;
    }

    /**
     * This method increases score when the answer is a boolean and its value is set to true
     *
     * @param answer is the answer to verify
     */
    private void ScoreIncrementIfBooleanIsTrue(boolean answer) {
        if (answer) {
            score++;
        }
    }

    /**
     * This method increases score when the answer is a boolean and its value is set to false
     *
     * @param answer is the answer to verify
     */
    private void ScoreIncrementIfBooleanIsFalse(boolean answer) {
        if (!answer) {
            score++;
        }
    }

}
