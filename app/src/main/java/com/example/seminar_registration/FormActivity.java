package com.example.seminar_registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class FormActivity extends AppCompatActivity {

    private TextInputLayout tilNama, tilEmail, tilHp;
    private TextInputEditText etNama, etEmail, etHp;
    private RadioGroup rgGender;
    private Spinner spinnerSeminar;
    private MaterialCheckBox cbPersetujuan;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initViews();
        setupSpinner();
        setupValidation();

        btnSubmit.setOnClickListener(v -> {
            if (validateAll()) {
                showConfirmationDialog();
            }
        });
    }

    private void initViews() {
        tilNama = findViewById(R.id.tilNama);
        tilEmail = findViewById(R.id.tilEmail);
        tilHp = findViewById(R.id.tilHp);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etHp = findViewById(R.id.etHp);
        rgGender = findViewById(R.id.rgGender);
        spinnerSeminar = findViewById(R.id.spinnerSeminar);
        cbPersetujuan = findViewById(R.id.cbPersetujuan);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.seminar_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeminar.setAdapter(adapter);
    }

    private void setupValidation() {
        etNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    tilNama.setError(getString(R.string.error_empty));
                } else {
                    tilNama.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                if (email.isEmpty()) {
                    tilEmail.setError(getString(R.string.error_empty));
                } else if (!email.contains("@")) {
                    tilEmail.setError(getString(R.string.error_email_invalid));
                } else {
                    tilEmail.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        etHp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hp = s.toString().trim();
                if (hp.isEmpty()) {
                    tilHp.setError(getString(R.string.error_empty));
                } else if (!hp.startsWith("08") || hp.length() < 10 || hp.length() > 13) {
                    tilHp.setError(getString(R.string.error_hp_invalid));
                } else {
                    tilHp.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private boolean validateAll() {
        boolean isValid = true;

        if (Objects.requireNonNull(etNama.getText()).toString().trim().isEmpty()) {
            tilNama.setError(getString(R.string.error_empty));
            isValid = false;
        }

        String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
        if (email.isEmpty() || !email.contains("@")) {
            tilEmail.setError(email.isEmpty() ? getString(R.string.error_empty) : getString(R.string.error_email_invalid));
            isValid = false;
        }

        String hp = Objects.requireNonNull(etHp.getText()).toString().trim();
        if (hp.isEmpty() || !hp.startsWith("08") || hp.length() < 10 || hp.length() > 13) {
            tilHp.setError(hp.isEmpty() ? getString(R.string.error_empty) : getString(R.string.error_hp_invalid));
            isValid = false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (spinnerSeminar.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Pilih Seminar", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (!cbPersetujuan.isChecked()) {
            Toast.makeText(this, getString(R.string.error_persetujuan), Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_yes, (dialog, which) -> navigateToResult())
                .setNegativeButton(R.string.dialog_no, null)
                .show();
    }

    private void navigateToResult() {
        String nama = Objects.requireNonNull(etNama.getText()).toString();
        String email = Objects.requireNonNull(etEmail.getText()).toString();
        String hp = Objects.requireNonNull(etHp.getText()).toString();
        String gender = rgGender.getCheckedRadioButtonId() == R.id.rbLaki ? "Laki-laki" : "Perempuan";
        String seminar = spinnerSeminar.getSelectedItem().toString();

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("NAMA", nama);
        intent.putExtra("EMAIL", email);
        intent.putExtra("HP", hp);
        intent.putExtra("GENDER", gender);
        intent.putExtra("SEMINAR", seminar);
        startActivity(intent);
    }
}