package com.santiago.labexercisefinals1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnInternalStorage, btnSharedPref;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_Password);
        btnSharedPref = (Button) findViewById(R.id.btn_SharedPref);
        btnInternalStorage = (Button) findViewById(R.id.btn_InternalStorage);
        preferences = getPreferences(Context.MODE_PRIVATE);

    }

    public void sharedPref(View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",etUsername.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.apply();
        Toast.makeText(this, "Username and Password Saved!", Toast.LENGTH_SHORT).show();

    }
    public void intStorage(View view){
        String message = " " + etUsername.getText().toString() + " and";
        String message2 = " Password is " + etPassword.getText().toString() + " ";

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.write(message2.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e ){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Username and Password Saved!", Toast.LENGTH_SHORT).show();
    }
    public void next (View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
