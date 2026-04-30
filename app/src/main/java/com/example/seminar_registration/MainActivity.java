package com.example.seminar_registration;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);

        intent.putExtra("USER_NAME", "Mahasiswa Tester");

        startActivity(intent);

        finish();
    }
}