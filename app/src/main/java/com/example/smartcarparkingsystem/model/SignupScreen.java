package com.example.smartcarparkingsystem.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartcarparkingsystem.R;

public class SignupScreen extends AppCompatActivity {
    EditText username, pass, conf_pass, mobile_num;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        Init();


    }


    public void Init() {
        loginButton = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        conf_pass = (EditText) findViewById(R.id.conf_password);
        mobile_num = (EditText) findViewById(R.id.mobile_num);

    }
}