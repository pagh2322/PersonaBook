package com.threesharp.personabook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.threesharp.personabook.databinding.ActivityMeBinding;

public class MeActivity extends AppCompatActivity {
    private ActivityMeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_24);
    }
}