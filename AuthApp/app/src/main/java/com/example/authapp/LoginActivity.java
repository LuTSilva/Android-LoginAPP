package com.example.authapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.authapp.data.Prefs;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserOrEmail, edtPass;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        prefs = new Prefs(this);

        edtUserOrEmail = findViewById(R.id.edtUserOrEmail);
        edtPass = findViewById(R.id.edtPass);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnGoRegister = findViewById(R.id.btnGoRegister);

        btnLogin.setOnClickListener(v -> {
            String id = edtUserOrEmail.getText().toString().trim();
            String pass = edtPass.getText().toString();

            if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Preencha usuário/e-mail e senha.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean ok = prefs.tryLogin(id, pass);
            if (ok) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciais inválidas.", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
