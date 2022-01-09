package com.threesharp.personabook;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threesharp.personabook.databinding.ActivityPersonalityTypeBinding;

public class PersonalityTypeActivity extends AppCompatActivity {
    private ActivityPersonalityTypeBinding binding;
    private int type;
    private int typeNumber;
    private PersonaDatabase personaDB = null;
    GradientDrawable nsvDrawable;
    VectorDrawable backDrawable;
    private TypeSexAdapter typeMaleAdapter;
    private TypeSexAdapter typeFemaleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalityTypeBinding.inflate(getLayoutInflater());
        nsvDrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.personality_type_nsv);
        backDrawable = (VectorDrawable) ContextCompat.getDrawable(this, R.drawable.ic_outline_arrow_back_ios_new_24);
        init();
        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                try {
                    typeMaleAdapter = new TypeSexAdapter(PersonalityTypeActivity.this, personaDB.personaDao().loadSex(type,0));
                    typeFemaleAdapter = new TypeSexAdapter(PersonalityTypeActivity.this, personaDB.personaDao().loadSex(type,1));
                    typeMaleAdapter.notifyDataSetChanged();
                    typeFemaleAdapter.notifyDataSetChanged();
                    binding.rvMale.setAdapter(typeMaleAdapter);
                    binding.rvFemale.setAdapter(typeFemaleAdapter);
                    binding.rvMale.setLayoutManager(new GridLayoutManager(PersonalityTypeActivity.this,4));
                    binding.rvFemale.setLayoutManager(new GridLayoutManager(PersonalityTypeActivity.this,4));
                }
                catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();
        setContentView(binding.getRoot());
    }
    // initialize persona database
    private void initData() {
        personaDB = PersonaDatabase.getInstance(this);
        typeNumber = personaDB.personaDao().load(type).size();
        binding.tvTotal.setText(String.valueOf(typeNumber));
        binding.tvTotalMale.setText(String.valueOf(personaDB.personaDao().loadSex(type,0).size()));
        binding.tvTotalFemale.setText(String.valueOf(personaDB.personaDao().loadSex(type,1).size()));
        typeMaleAdapter = new TypeSexAdapter(PersonalityTypeActivity.this, personaDB.personaDao().loadSex(type,0));
        typeFemaleAdapter = new TypeSexAdapter(PersonalityTypeActivity.this, personaDB.personaDao().loadSex(type,1));
    }
    private void init() {
        type = getIntent().getIntExtra("type", 0);
        initToolbar();
        initAppbar();
        binding.tvPersonality.setText(Types.get(type).name);
        binding.getRoot().setBackgroundColor(Types.get(type).sColor);
        initData();
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    private void initAppbar() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 56;
        if (this.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        int finalActionBarHeight = actionBarHeight;
        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (appBarLayout.getTotalScrollRange()-Math.abs(verticalOffset) <= finalActionBarHeight) {
                    nsvDrawable.setCornerRadius(0);
                    backDrawable.setTint(getResources().getColor(R.color.fontBColor));
                }
                else {
                    nsvDrawable.setCornerRadius(dpToPx(35));
                    backDrawable.setTint(getResources().getColor(R.color.bgColor));
                }
                getSupportActionBar().setHomeAsUpIndicator(backDrawable);
                binding.nsv.setBackground(nsvDrawable);
            }
        });
    }
    private void initToolbar() {
        binding.llToolbar.setBackgroundColor(Types.get(type).sColor);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_24);
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
    public void onBackPressed() {
        backDrawable.setTint(getResources().getColor(R.color.bgColor));
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        personaDB.destroyInstance();
    }
}