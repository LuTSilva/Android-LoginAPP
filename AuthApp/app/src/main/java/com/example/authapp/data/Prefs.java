package com.example.authapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.authapp.model.User;

public class Prefs {
    private static final String FILE = "auth_prefs";
    private static final String K_USER = "user";
    private static final String K_EMAIL = "email";
    private static final String K_PASS = "pass";
    private static final String K_LOGGED = "logged";

    private final SharedPreferences sp;

    public Prefs(Context ctx) {
        sp = ctx.getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    // --- validações ---
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isValidPassword(String pass) { return pass != null && pass.length() >= 6; }

    // --- persistência de usuário (um único usuário simples) ---
    public void saveUser(User u) {
        sp.edit()
                .putString(K_USER, u.username)
                .putString(K_EMAIL, u.email)
                .putString(K_PASS, u.password)
                .apply();
    }

    public User getSavedUser() {
        String u = sp.getString(K_USER, null);
        String e = sp.getString(K_EMAIL, null);
        String p = sp.getString(K_PASS, null);
        if (u == null || e == null || p == null) return null;
        return new User(u, e, p);
    }

    public boolean userExists(String username, String email) {
        User s = getSavedUser();
        if (s == null) return false;
        return s.email.equalsIgnoreCase(username) || s.username.equalsIgnoreCase(email);
    }

    // --- login/logout ---
    public boolean tryLogin(String userOrEmail, String pass) {
        User s = getSavedUser();
        if (s == null) return false;
        boolean idOk = s.username.equalsIgnoreCase(userOrEmail) || s.email.equalsIgnoreCase(userOrEmail);
        boolean passOk = s.password.equals(pass);
        if (idOk && passOk) {
            setLogged(true);
            return true;
        }
        return false;
    }

    public void setLogged(boolean v) {
        sp.edit().putBoolean(K_LOGGED, v).apply();
    }

    public boolean isLogged() {
        return sp.getBoolean(K_LOGGED, false);
    }

    public void clearLogin() {
        setLogged(false);
    }
}
