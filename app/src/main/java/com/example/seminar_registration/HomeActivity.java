package com.example.seminar_registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        MaterialButton btnGoToForm = findViewById(R.id.btnGoToForm);

        String intentUserName = getIntent().getStringExtra("USER_NAME");
        String userName = (intentUserName != null && !intentUserName.isEmpty()) ? intentUserName : "User";

        tvWelcomeUser.setText(getString(R.string.welcome_user, userName));

        btnGoToForm.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FormActivity.class);
            startActivity(intent);
        });
    }
}