package com.example.authapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.authapp.data.Prefs;
import com.example.authapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUser, edtEmail, edtPass;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Cadastro");

        prefs = new Prefs(this);

        edtUser  = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass  = findViewById(R.id.edtPass);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String u = edtUser.getText().toString().trim();
            String e = edtEmail.getText().toString().trim();
            String p = edtPass.getText().toString();

            if (TextUtils.isEmpty(u) || TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Prefs.isValidEmail(e)) {
                Toast.makeText(this, "E-mail inválido.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Prefs.isValidPassword(p)) {
                Toast.makeText(this, "Senha deve ter ao menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (prefs.userExists(e, u)) {
                Toast.makeText(this, "Usuário/e-mail já cadastrado.", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.saveUser(new User(u, e, p));
            Toast.makeText(this, "Cadastro realizado! Faça login.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
