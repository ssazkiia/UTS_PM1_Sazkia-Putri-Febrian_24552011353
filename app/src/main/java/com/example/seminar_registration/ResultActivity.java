package com.example.seminar_registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvResNama = findViewById(R.id.rowNama).findViewById(R.id.value);
        ((TextView)findViewById(R.id.rowNama).findViewById(R.id.label)).setText(R.string.label_nama);

        TextView tvResEmail = findViewById(R.id.rowEmail).findViewById(R.id.value);
        ((TextView)findViewById(R.id.rowEmail).findViewById(R.id.label)).setText(R.string.label_email);

        TextView tvResHp = findViewById(R.id.rowHp).findViewById(R.id.value);
        ((TextView)findViewById(R.id.rowHp).findViewById(R.id.label)).setText(R.string.label_hp);

        TextView tvResGender = findViewById(R.id.rowGender).findViewById(R.id.value);
        ((TextView)findViewById(R.id.rowGender).findViewById(R.id.label)).setText(R.string.label_gender);

        TextView tvResSeminar = findViewById(R.id.tvResSeminar);
        MaterialButton btnBackHome = findViewById(R.id.btnBackHome);

        Intent intent = getIntent();
        if (intent != null) {
            tvResNama.setText(intent.getStringExtra("NAMA"));
            tvResEmail.setText(intent.getStringExtra("EMAIL"));
            tvResHp.setText(intent.getStringExtra("HP"));
            tvResGender.setText(intent.getStringExtra("GENDER"));
            tvResSeminar.setText(intent.getStringExtra("SEMINAR"));
        }

        btnBackHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(ResultActivity.this, HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(homeIntent);
            finish();
        });
    }
}