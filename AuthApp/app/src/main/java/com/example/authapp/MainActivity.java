package com.example.authapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.authapp.data.Prefs;
import com.example.authapp.model.User;

public class MainActivity extends AppCompatActivity {

    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Principal");

        prefs = new Prefs(this);

        if (!prefs.isLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        TextView txtUser = findViewById(R.id.txtUser);
        TextView txtEmail = findViewById(R.id.txtEmail);
        Button btnLogout = findViewById(R.id.btnLogout);

        User u = prefs.getSavedUser();
        if (u != null) {
            txtUser.setText(u.username);
            txtEmail.setText(u.email);
        }

        btnLogout.setOnClickListener(v -> {
            prefs.clearLogin();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
