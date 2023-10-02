package com.example.smartcarparkingsystem.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smartcarparkingsystem.R;
import com.example.smartcarparkingsystem.model.MainUserInterface;
import com.example.smartcarparkingsystem.model.SignupScreen;

public class LoginTabFragment extends Fragment {
    EditText username, password, forgetPass;
    Button login, signup;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragments, container, false);
        login = root.findViewById(R.id.login);
        signup = root.findViewById(R.id.signup);
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);


        /*username = root.findViewById(R.id.username);
        pass = root.findViewById(R.id.password);
        forgetPass = root.findViewById(R.id.forget_password);
        login = root.findViewById(R.id.login);

        username.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        username.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().length() != 0 && password.getText().length() != 0)
                {
                    if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
                    {
                        Intent intent = new Intent(view.getContext(), MainUserInterface.class);
                        startActivity(intent);

                    }
                    else Toast.makeText(view.getContext(), "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(view.getContext(), "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignupScreen.class); // enclosing class
                startActivity(intent);
            }
        });

        return root;
    }


    }

