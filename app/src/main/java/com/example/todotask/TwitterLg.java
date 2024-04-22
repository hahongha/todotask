package com.example.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TwitterLg extends AppCompatActivity {
    private EditText etName;
    private EditText etMatKhau;
    private ImageView ivImage2;
    private Button btnLogin2,btnHuy;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_twitter);
        etName= findViewById(R.id.Email2);
        etMatKhau = findViewById(R.id.Pass2);
        ivImage2=findViewById(R.id.ivImage2);
        btnLogin2 = findViewById(R.id.btnLogin2);
        btnHuy = findViewById(R.id.btnHuy);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String image = bundle.getString("Image");
            String email2 = bundle.getString("Email");
            String pass2 = bundle.getString("Mật khẩu");
            etName.setText(email2);
            etMatKhau.setText(pass2);
            btnLogin2.setText("Đăng nhập");
            btnHuy.setText("Huỷ");
        }

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = etName.getText().toString();
                String pass2 = etMatKhau.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("Email", email2);
                b.putString("Mật khẩu", pass2);
                intent.putExtras(b);
                setResult(150, intent);
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHuy.setEnabled(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnHuy.setEnabled(true);
                        onBackPressed();
                    }
                }, 20);
            }
        });
    }
}
