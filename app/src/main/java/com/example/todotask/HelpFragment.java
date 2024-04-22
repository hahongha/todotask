package com.example.todotask;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_help,container,false);
        // Ánh xạ các thành phần giao diện từ layout XML (nếu cần)
        TextView text1 = v.findViewById(R.id.text1);
        TextView text2 = v.findViewById(R.id.text2);
        TextView text3 = v.findViewById(R.id.text3);
        TextView text4 = v.findViewById(R.id.text4);
        TextView text5 = v.findViewById(R.id.text5);
        TextView text6 = v.findViewById(R.id.text6);
        TextView text7 = v.findViewById(R.id.text7);
        TextView text8 = v.findViewById(R.id.text8);
        ImageView button1= v.findViewById(R.id.button1);
        ImageView button2= v.findViewById(R.id.button2);
        ImageView button3= v.findViewById(R.id.button3);
        ImageView button4= v.findViewById(R.id.button4);
        ImageView button5= v.findViewById(R.id.button5);
        ImageView button6= v.findViewById(R.id.button6);
        ImageView button7= v.findViewById(R.id.button7);
        ImageView button8= v.findViewById(R.id.button8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getVisibility()==View.GONE){
                    text1.setVisibility(View.VISIBLE);
                    button1.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text1.setVisibility(View.GONE);
                    button1.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text2.getVisibility()==View.GONE){
                    text2.setVisibility(View.VISIBLE);
                    button2.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text2.setVisibility(View.GONE);
                    button2.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text3.getVisibility()==View.GONE){
                    text3.setVisibility(View.VISIBLE);
                    button3.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text3.setVisibility(View.GONE);
                    button3.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text4.getVisibility()==View.GONE){
                    text4.setVisibility(View.VISIBLE);
                    button4.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text4.setVisibility(View.GONE);
                    button4.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text5.getVisibility()==View.GONE){
                    text5.setVisibility(View.VISIBLE);
                    button5.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text5.setVisibility(View.GONE);
                    button5.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text6.getVisibility()==View.GONE){
                    text6.setVisibility(View.VISIBLE);
                    button6.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text6.setVisibility(View.GONE);
                    button6.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text7.getVisibility()==View.GONE){
                    text7.setVisibility(View.VISIBLE);
                    button7.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text7.setVisibility(View.GONE);
                    button7.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text8.getVisibility()==View.GONE){
                    text8.setVisibility(View.VISIBLE);
                    button8.setImageResource(R.drawable.ic_help1);
                }
                else {
                    text8.setVisibility(View.GONE);
                    button8.setImageResource(R.drawable.ic_helpdown);
                }
            }
        });
        return v;
    }
}