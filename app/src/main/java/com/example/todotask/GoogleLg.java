package com.example.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GoogleLg extends AppCompatActivity {
    private EditText edEmail, edPass;
    private ImageView ivImage;
    private Button btnLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_google);

        edEmail = findViewById(R.id.Email);
        edPass = findViewById(R.id.Pass);
        ivImage = findViewById(R.id.ivImage);
        btnLogin = findViewById(R.id.btnLogin);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String image = bundle.getString("Image");
            String email = bundle.getString("Email");
            String pass = bundle.getString("Mật khẩu");
            edEmail.setText(email);
            edPass.setText(pass);
            btnLogin.setText("Đăng nhập");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("Email", email);
                b.putString("Mật khẩu", pass);
                intent.putExtras(b);
                setResult(150, intent);
                finish();
            }
        });
    }

}
