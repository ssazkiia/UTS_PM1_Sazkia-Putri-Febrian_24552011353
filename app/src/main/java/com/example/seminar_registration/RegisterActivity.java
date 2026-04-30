package com.example.seminar_registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        TextInputLayout tilUsername = findViewById(R.id.tilUsername);
        TextInputLayout tilEmail = findViewById(R.id.tilEmail);
        TextInputLayout tilPassword = findViewById(R.id.tilPassword);
        TextInputLayout tilConfirmPassword = findViewById(R.id.tilConfirmPassword);

        TextInputEditText etUsername = findViewById(R.id.etUsername);
        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        TextInputEditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        
        Button btnRegister = findViewById(R.id.btnRegisterMember);
        TextView tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setText(Html.fromHtml(getString(R.string.already_a_member_sign_in), Html.FROM_HTML_MODE_COMPACT));

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Reset errors
            tilUsername.setError(null);
            tilEmail.setError(null);
            tilPassword.setError(null);
            tilConfirmPassword.setError(null);

            if (username.isEmpty()) {
                tilUsername.setError(getString(R.string.error_empty));
                etUsername.requestFocus();
            } else if (email.isEmpty()) {
                tilEmail.setError(getString(R.string.error_empty));
                etEmail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.setError(getString(R.string.error_email_invalid));
                etEmail.requestFocus();
            } else if (password.isEmpty()) {
                tilPassword.setError(getString(R.string.error_empty));
                etPassword.requestFocus();
            } else if (password.length() < 6) {
                tilPassword.setError("Password minimal 6 karakter");
                etPassword.requestFocus();
            } else if (!password.equals(confirmPassword)) {
                tilConfirmPassword.setError(getString(R.string.error_password_not_match));
                etConfirmPassword.requestFocus();
            } else {
                // Save to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(this, "Registration Successful for " + username, Toast.LENGTH_LONG).show();
                // Navigate to Login
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}