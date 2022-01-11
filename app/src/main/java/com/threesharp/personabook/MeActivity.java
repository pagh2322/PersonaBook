package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.threesharp.personabook.databinding.ActivityMeBinding;

import java.util.ArrayList;

public class MeActivity extends AppCompatActivity {
    private ActivityMeBinding binding;
    private int type;
    private PersonaDatabase personaDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeBinding.inflate(getLayoutInflater());
        personaDB = PersonaDatabase.getInstance(this);
        init();
        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                setGoodType();
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();
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
        binding.tvKey1.setText(Types.get(type).keys[0]);
        binding.tvKey2.setText(Types.get(type).keys[1]);
        binding.tvKey3.setText(Types.get(type).keys[2]);
        binding.tvKey4.setText(Types.get(type).keys[3]);

    }
    private void setGoodType() {
        ArrayList<Integer> goodTypes = Types.getGoodTypes(type);
        int size = goodTypes.size();
        binding.tvGoodType1.setText(Types.get(goodTypes.get(0)).name);
        binding.tvNum1.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(0)).size()));
        binding.tvGoodType2.setText(Types.get(goodTypes.get(1)).name);
        binding.tvNum2.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(1)).size()));
        if (size == 3) {
            binding.tvGoodType3.setTextColor(this.getColor(R.color.fontBColor));
            binding.tvGoodType3.setText(Types.get(goodTypes.get(2)).name);
            binding.tvNum3.setTextColor(this.getColor(R.color.fontBColor));
            binding.tvNum3.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(2)).size()));
            binding.tvNum.setTextColor(this.getColor(R.color.fontBColor));
        }
        else {
            binding.tvGoodType3.setTextColor(this.getColor(R.color.fontWColor));
            binding.tvNum3.setTextColor(this.getColor(R.color.fontWColor));
            binding.tvNum.setTextColor(this.getColor(R.color.fontWColor));
        }
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
        setGoodType();
    }
}