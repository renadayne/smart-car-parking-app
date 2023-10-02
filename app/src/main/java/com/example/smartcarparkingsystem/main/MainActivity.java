package com.example.smartcarparkingsystem.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartcarparkingsystem.business.ChooseParking;
import com.example.smartcarparkingsystem.R;
import com.example.smartcarparkingsystem.business.Map;
import com.example.smartcarparkingsystem.model.LoginScreen;

public class MainActivity extends AppCompatActivity {
    Button chooseParking, btFinish, btLogin;
    ImageView florentinoIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        // xu li

        chooseParking.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                // setup button
                // imageView.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this, Map.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }

        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                // setup button
                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }

        });

        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
    }

    // ham anh xa
    private void Init() {
        chooseParking = (Button) findViewById(R.id.button);
        florentinoIcon = (ImageView) findViewById(R.id.flotentinoIcon);
        btFinish = (Button) findViewById(R.id.btFinish);
        btLogin = (Button) findViewById(R.id.btLogin);
    }
}