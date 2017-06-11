package com.cristian_sedano.createfile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileActivity extends AppCompatActivity {

    Button create,read;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        create = (Button) findViewById(R.id.create);
        read = (Button) findViewById(R.id.read);
        text = (TextView) findViewById(R.id.text);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = "My File";
                String content = "New File";

                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fileOutputStream.write(content.getBytes());
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = getApplicationContext().openFileInput("My File");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line).append("\n");
                    }
                    text.setText(stringBuilder.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e){

                } catch (IOException e){

                }
            }
        });
    }
}
