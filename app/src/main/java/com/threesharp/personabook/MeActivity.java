package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.threesharp.personabook.databinding.ActivityMeBinding;

public class MeActivity extends AppCompatActivity {
    private ActivityMeBinding binding;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeBinding.inflate(getLayoutInflater());
        init();
        setContentView(binding.getRoot());
    }

    private void init() {
        type = MyInfo.getType();
        initToolbar();
        initCVChangeInfo();
        setInfo();
    }
    private void initCVChangeInfo() {
        binding.cvChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeInfoIntent = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(changeInfoIntent);
            }
        });
    }
    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_24);
    }
    private void setInfo() {
        binding.toolbar.setBackgroundColor(Types.get(type).sColor);
        binding.clInfo.setBackgroundColor(Types.get(type).sColor);
        binding.clEdit.setBackgroundColor(Types.get(type).color);
        binding.tvPersonality.setText(Types.get(type).name);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        type = MyInfo.getType();
        setInfo();
    }
}