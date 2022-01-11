package com.threesharp.personabook;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.threesharp.personabook.databinding.ActivityPersonaInfoBinding;

public class PersonaInfoActivity extends AppCompatActivity {
    private ActivityPersonaInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonaInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}