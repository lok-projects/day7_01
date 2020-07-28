package com.example.day7_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
EditText fnam,dat;
String s1,s2;
TextView i,o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fnam=findViewById(R.id.editTextTextPersonName);
        dat=findViewById(R.id.editTextTextPersonName2);
        i=findViewById(R.id.textView2);
        o=findViewById(R.id.textView3);
        ActivityCompat.requestPermissions
                (MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
    }

    public void inter(View view) {
        s1=fnam.getText().toString();
        s2=dat.getText().toString();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream =openFileOutput(s1, Context.MODE_PRIVATE);
            fileOutputStream.write(s2.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exter(View view) {
        s1=fnam.getText().toString();
        s2=dat.getText().toString();
        try {
            File myFile = new File("/sdcard/" + s1);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(s2);
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getApplicationContext(), s1 + "saved External", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readint(View view){
        s1=fnam.getText().toString();
        s2=dat.getText().toString();
        i.setText("");
        StringBuffer st=new StringBuffer();
        try {
            BufferedReader bf= new BufferedReader(new InputStreamReader(openFileInput(s1)));
            String temp="";
            while ((temp=bf.readLine())!=null){
                st.append(temp+"\n");

            }
        }  catch (FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
        i.append(st);
    }

    public void readext(View view) {
        s1=fnam.getText().toString();
        s2=dat.getText().toString();
        StringBuffer st=new StringBuffer();
        String aDatarow="";
        String aBuffer="";
        try {
            File myfile= new File("/sdcard/"+s1);
            FileInputStream fin= new FileInputStream(myfile);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(fin));
            while ((aDatarow=bufferedReader.readLine())!=null){
                aBuffer+=aDatarow+"\n";
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, ""+aBuffer, Toast.LENGTH_SHORT).show();
    }
}