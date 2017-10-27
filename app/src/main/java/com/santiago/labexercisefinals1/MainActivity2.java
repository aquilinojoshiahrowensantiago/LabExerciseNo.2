package com.santiago.labexercisefinals1;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    FileInputStream fis;
    Button btnlInternalStorage, btnlSharedPref;
    TextView tvDisplay, tvDisplay2;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnlInternalStorage = (Button) findViewById(R.id.btn_lInternalStorage);
        btnlSharedPref = (Button) findViewById(R.id.btn_lSharedPref);
        preferences = getPreferences(Context.MODE_PRIVATE);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        tvDisplay2 = (TextView) findViewById(R.id.tv_display2);
    }
    public void back (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void dispSharedPref(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = preferences.getString("username", "");
        String pwd = preferences.getString("password", "");
        tvDisplay2.setText("The password of " + user + " is " + pwd);

    }


    public void dispIntStorage(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvDisplay.setText("The Username is" + buffer.toString());
    }
    public void clear(View view){
        tvDisplay.setText("");
        tvDisplay2.setText("");
    }

}
