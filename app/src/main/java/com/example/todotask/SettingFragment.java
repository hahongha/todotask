package com.example.todotask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SettingFragment extends Fragment {
    SwitchCompat changetheme;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ImageView btnGoogle;
    private ImageView btnFacebook;
    private ImageView btnTwitter;


    private AutoCompleteTextView dropDownMenuRepeat;
    //    String[] options = new String[]{"Do not repeat", "Daily","Weekly","Monthly","Annually"};
    ArrayAdapter<String> adapterItems ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        // Ánh xạ các thành phần giao diện từ layout XML (nếu cần)

        dropDownMenuRepeat = v.findViewById(R.id.set_language);
        adapterItems = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line,
                new String[]{"English", "Vietnamese", "Chinese"});
        dropDownMenuRepeat.setAdapter(adapterItems);
        
        changetheme = v.findViewById(R.id.ChangeTheme);
        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);

        if(nightMode){
            changetheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        changetheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                editor.apply();
            }
        });
        btnGoogle = v.findViewById(R.id.google);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), GoogleLg.class);
                startActivity(intent);
            }
        });
        btnFacebook = v.findViewById(R.id.facebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), FacebookLg.class);
                startActivity(intent);
            }
        });
        btnTwitter = v.findViewById(R.id.twitter);
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), TwitterLg.class);
                startActivity(intent);
            }
        });


        return v;

    }


}




