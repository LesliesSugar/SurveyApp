package com.lexi.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Step1 extends AppCompatActivity {

    private String filemessage;
    private Intent intent;
    private RadioGroup radioGroup;
    private final static String EXTRA_MESSAGE = "file";
    private int checkedbuttonid;
    private RadioButton checkedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);

    }
    protected void go(View v) {

        checkedbuttonid= radioGroup.getCheckedRadioButtonId();


        if(checkedbuttonid>0){
            checkedbutton=(RadioButton)findViewById(checkedbuttonid);
            filemessage="1:"+checkedbutton.getText().toString()+" ";
            Toast.makeText(getBaseContext(),filemessage,Toast.LENGTH_SHORT).show();
            intent=new Intent(this,Step2.class);
            intent.putExtra(EXTRA_MESSAGE,filemessage);
            startActivity(intent);
        }
        else
        Toast.makeText(getBaseContext(),"Please choose",Toast.LENGTH_SHORT).show();

    }
}
