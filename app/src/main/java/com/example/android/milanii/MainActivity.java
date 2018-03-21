package com.example.android.milanii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
    private CheckBox cbQuiz1AnswerAShopping, cbQuiz1AnswerBBeaches, cbQuiz1AnswerCCathedral,
            cbQuiz4AnswerALetters, cbQuiz4AnswerBTram, cbQuiz4AnswerCCoffee;
    private EditText editUserName, editQuiz2Answer;
    private RadioButton rbQuiz3AnswerBPanettone, rbQuiz5AnswerCAlps;

    // define the score at the beginning of the quiz game
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the views inside the onCreate method to avoid multiple reinitializing
        editUserName = findViewById(R.id.edit_user_name);
        cbQuiz1AnswerAShopping = findViewById(R.id.cb_quiz_1_answer_a_shopping);
        cbQuiz1AnswerBBeaches = findViewById(R.id.cb_quiz_1_answer_b_beaches);
        cbQuiz1AnswerCCathedral = findViewById(R.id.cb_quiz_1_answer_c_cathedral);
        editQuiz2Answer = findViewById(R.id.edit_quiz_2_answer);
        rbQuiz3AnswerBPanettone = findViewById(R.id.rb_quiz_3_answer_b_panettone);
        cbQuiz4AnswerALetters = findViewById(R.id.cb_quiz_4_answer_a_letters);
        cbQuiz4AnswerBTram = findViewById(R.id.cb_quiz_4_answer_b_tram);
        cbQuiz4AnswerCCoffee = findViewById(R.id.cb_quiz_4_answer_c_coffee);
        rbQuiz5AnswerCAlps = findViewById(R.id.rb_quiz_5_answer_c_alps);
    }

    /**
     * This method is called when the submit button is clicked.
     * Checks user's name
     * Checks user's answers and increment score
     * Calls feedback toast
     */
    public void submitResults(View view) {

        // Check name in edit_user_name EditText
        String userName = (editUserName.getText().toString());
        if (TextUtils.isEmpty(userName)) {
            userName = getString(R.string.default_user_name_toast_string);
        }

        // Check Quiz 1 and assign score
        if (
                cbQuiz1AnswerAShopping.isChecked()
                        && !cbQuiz1AnswerBBeaches.isChecked()
                        && cbQuiz1AnswerCCathedral.isChecked()) {
            score++;
        }

        // Check Quiz 2 and assign score
        String guessAnswer2 = (editQuiz2Answer).getText().toString().replace(" ", "");

        Log.i("Answer2", guessAnswer2);
        if (
                guessAnswer2.equalsIgnoreCase("Leonardo")
                        || guessAnswer2.equalsIgnoreCase("LeonardoDaVinci")) {
            score++;
        }

        // Check Quiz 3 and assign score
        incrementScoreIfBooleanIsTrue(rbQuiz3AnswerBPanettone.isChecked());

        // Check Quiz 4 and assign score

        if (
                cbQuiz4AnswerALetters.isChecked()
                        && cbQuiz4AnswerBTram.isChecked()
                        && !cbQuiz4AnswerCCoffee.isChecked()) {
            score++;
        }

        // Check Quiz 5 and assign score
        incrementScoreIfBooleanIsTrue(rbQuiz5AnswerCAlps.isChecked());

        // Display results toast
        if (score > 3) {
            Toast.makeText(this, getString(R.string.well_done_toast_string, userName, score), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.nice_try_toast_string, userName, score), Toast.LENGTH_LONG).show();
        }
        // Reset score value
        score = 0;
    }

    /**
     * This method increases score when the answer is a boolean and its value is set to true
     *
     * @param answer is the answer to verify
     */
    private void incrementScoreIfBooleanIsTrue(boolean answer) {
        if (answer) {
            score++;
        }
    }
}
