package com.example.smartcarparkingsystem.business;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smartcarparkingsystem.R;

public class SignupTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragments, container, false);
        return root;
    }
}
