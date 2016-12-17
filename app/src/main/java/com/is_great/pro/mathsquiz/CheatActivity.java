package com.is_great.pro.mathsquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private final static String TAG="CheatActivity";
    private final static String ANSWER_IS_TRUE="com.is_great.pro.mathsquiz.answer_is_true";
    private final static  String IS_CHEATED="IS_CHEATED";
    private boolean Is_cheated=false;

    private TextView mCheatAnswerTextView;
    private Button mShowButton;

    public static Intent newIntent(Context context, boolean b)
    {
        Intent intent = new Intent(context,CheatActivity.class);
        intent.putExtra(ANSWER_IS_TRUE,b);// first parameter is just a index and second is value
        return intent;
    }
    public static boolean wasCheatShown(Intent i)
    {
        return i.getBooleanExtra(IS_CHEATED,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG,"Inside onCreate");
        Is_cheated =getIntent().getBooleanExtra(ANSWER_IS_TRUE,false);

        mCheatAnswerTextView =(TextView) findViewById(R.id.cheatAnswer_text_view);
        mShowButton= (Button) findViewById(R.id.showCheat_button);
        mShowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(Is_cheated)
                {
                    mCheatAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mCheatAnswerTextView.setText(R.string.false_button);
                }
                setAnswerResult(true);
            }
        });

        Log.d(TAG,"Recievd value: "+Is_cheated);
    }

    private void setAnswerResult(boolean b){
        Intent i = new Intent();
        i.putExtra(IS_CHEATED,b);
        setResult(RESULT_OK,i);
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
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"Inside onResume");
    }
}
