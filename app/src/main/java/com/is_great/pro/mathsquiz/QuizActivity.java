package com.is_great.pro.mathsquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private Button mHintButton;
    private TextView mQuestionTextView;

    //logging activity
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX ="index";
    private static final int REQUEST_CODE_CHEAT=1;
    private boolean mCHEATED;

    private QuestionBank[] mQuestionBank=new QuestionBank[]{
            new QuestionBank(R.string.question_text_1, true),
            new QuestionBank(R.string.question_text_2, true),
            new QuestionBank(R.string.question_text_3, true),
            new QuestionBank(R.string.question_text_4, true),
            new QuestionBank(R.string.question_text_5, false)
    };

    private int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log
        Log.d(TAG,"Inside  onCreate");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        //check for saved currentIndex value in Bundle as it store data in key value pair
        if(savedInstanceState != null)
        {
            currentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();

        mTrueButton=(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });
        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton=(Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                currentIndex= (currentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mHintButton=(Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Hint Pressed");
                //start HintActivity
                String text="see,it can be concident that you kow how to write Android code";
                //startActivityForResult(i,REQUEST_CODE_HINT);
            }
        });
        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Cheat button is pressed");
                //Intent i = new Intent(QuizActivity.this,CheatActivity.class);
                //startActivity(i);
                boolean b= mQuestionBank[currentIndex].isTrueQuestion();
                Intent i= CheatActivity.newIntent(QuizActivity.this,b);
                //current index for cheating for tha question. :P
                //startActivity(i);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT)
        {
            if(data == null)
            {
                return ;
            }
            else
            {
                mCHEATED=CheatActivity.wasCheatShown(data);
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"Inside onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,currentIndex);
    }
    private void updateQuestion()
    {
        int question=mQuestionBank[currentIndex].getQuestion();//question has resource id
        mCHEATED=false;
        mQuestionTextView.setText(question);// display text from the resiurce id
    }
    private void checkAnswer(boolean userPressed)
    {
        boolean isAnswerTrue=mQuestionBank[currentIndex].isTrueQuestion();
        int messageId=0;
        if(mCHEATED)
        {
            messageId=R.string.cheated_toast;
        }else{
            if(userPressed == isAnswerTrue)
            {
                messageId=R.string.correct_toast;
            }
            else
            {
                messageId=R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,messageId,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"Inside onStart");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"Inside onStop");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"Inside onPause");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Inside onResume");
        Log.d(TAG,"Did user cheat? "+mCHEATED);
    }
}
