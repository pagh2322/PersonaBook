package com.threesharp.personabook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.threesharp.personabook.databinding.ActivityEditInfoBinding;

public class EditInfoActivity extends AppCompatActivity {
    private ActivityEditInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInfoBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24);
        setContentView(binding.getRoot());
    }
}