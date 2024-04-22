package com.example.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookLg extends AppCompatActivity {
    private EditText etEmail, etPass;
    private ImageView ivImage1;
    private Button btnLogin1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_facebook);
            etEmail = findViewById(R.id.Email1);
            etPass = findViewById(R.id.Pass1);
            ivImage1=findViewById(R.id.ivImage1);
            btnLogin1 = findViewById(R.id.btnLogin1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String image = bundle.getString("Image");
            String email1 = bundle.getString("Email");
            String pass1 = bundle.getString("Mật khẩu");
            etEmail.setText(email1);
            etPass.setText(pass1);
            btnLogin1.setText("Đăng nhập");
        }

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = etEmail.getText().toString();
                String pass1 = etPass.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("Email", email1);
                b.putString("Mật khẩu", pass1);
                intent.putExtras(b);
                setResult(150, intent);
                finish();
            }
        });
    }
}
