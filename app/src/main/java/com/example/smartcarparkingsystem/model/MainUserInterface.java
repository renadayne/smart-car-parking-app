package com.example.smartcarparkingsystem.model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcarparkingsystem.R;
import com.example.smartcarparkingsystem.business.Map;
import com.example.smartcarparkingsystem.business.ParkingAreaClone;
import com.example.smartcarparkingsystem.main.MainActivity;

public class MainUserInterface extends AppCompatActivity {
    Button chooseParking, btFinish, btUser, btLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_user_interface_screen);
        Init();

        chooseParking.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(MainUserInterface.this, Map.class);
                Bundle bundle = new Bundle();
                intent.putExtra("packet", bundle);
                startActivity(intent);
            }

        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainUserInterface.this, MainActivity.class);
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

        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void Init() {
        btLogout = (Button) findViewById(R.id.btLogout);
        chooseParking = (Button) findViewById(R.id.button);
        btFinish = (Button) findViewById(R.id.btFinish);
        btUser = (Button) findViewById(R.id.btUser);
    }
}