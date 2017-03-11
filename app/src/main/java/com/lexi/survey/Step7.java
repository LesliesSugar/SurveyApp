package com.lexi.survey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Step7 extends AppCompatActivity {

    private String filemessage;
    private Intent intent;
    private CheckBox ck1,ck2,ck3,ck4,ck5;
    private final static String EXTRA_MESSAGE = "file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step7);
        ck1=(CheckBox)findViewById(R.id.checkBox);
        ck2=(CheckBox)findViewById(R.id.checkBox2);
        ck3=(CheckBox)findViewById(R.id.checkBox3);
        ck4=(CheckBox)findViewById(R.id.checkBox4);
        ck5=(CheckBox)findViewById(R.id.checkBox5);

    }
    protected void go(View v){
        String s="7:";
        if (!ck1.isChecked()&&!ck2.isChecked()&&!ck4.isChecked()&&!ck4.isChecked()&&!ck5.isChecked()){
            Toast.makeText(getBaseContext()," choose one or more",Toast.LENGTH_SHORT).show();
        }
        else {
            if (ck1.isChecked()) {
                s=s+ck1.getText().toString()+" ";
            }
            if (ck2.isChecked()) {
                s=s+ck2.getText().toString()+" ";
            }
            if (ck3.isChecked()) {
                s=s+ck3.getText().toString()+" ";
            }
            if (ck4.isChecked()) {
                s=s+ck4.getText().toString()+" ";
            }
            if (ck5.isChecked()) {
                s=s+ck5.getText().toString()+" ";
            }
            Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
            intent=getIntent();
            filemessage=intent.getStringExtra(EXTRA_MESSAGE);
            filemessage=filemessage+s;
            intent=new Intent(this,Step8.class);
            intent.putExtra(EXTRA_MESSAGE,filemessage);
            startActivity(intent);
            }

    }


}
