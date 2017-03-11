package com.lexi.survey;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Step13 extends AppCompatActivity {

    private String filemessage;
    private Intent intent;
    private final static String EXTRA_MESSAGE = "file";
    private EditText editText;
    private File file;
    private OutputStream outputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step13);
        intent=getIntent();
        filemessage=intent.getStringExtra(EXTRA_MESSAGE);
        editText= (EditText)findViewById(R.id.editText);
        String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "Result"+timestamp+".txt";
        file=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),filename);
    }
    protected void Sub(View v)
    {
        if (editText.length()<1)
        {
            Toast.makeText(getBaseContext(),"please fill the blank",Toast.LENGTH_LONG).show();
        } else if ( !isEmail(editText.getText().toString())) {
            Toast.makeText(getBaseContext(),"please fill a valid email address",Toast.LENGTH_LONG).show();
        } else {
            String s=editText.getText().toString();
            filemessage =  filemessage+s;
            try {
                outputStream = new FileOutputStream(file, true);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();

            }
            try {
                outputStream.write(filemessage.getBytes());
                outputStream.close();
                Toast.makeText(getBaseContext(), "Thank you !", Toast.LENGTH_LONG).show();
            } catch (IOException e){
                e.printStackTrace();

            }
        }

    }
    protected boolean isEmail(String s)
    {
        return s.matches("\\w+@(\\w+.)+[a-z]{2,3}");
    }
}
