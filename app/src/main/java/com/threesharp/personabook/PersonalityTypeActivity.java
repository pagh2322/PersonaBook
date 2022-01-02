package com.threesharp.personabook;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;

import com.threesharp.personabook.databinding.ActivityPersonalityTypeBinding;

public class PersonalityTypeActivity extends AppCompatActivity {
    private ActivityPersonalityTypeBinding binding;
    private int type;
    GradientDrawable nsvDrawable;
    VectorDrawable backDrawable;
    boolean isCollapsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalityTypeBinding.inflate(getLayoutInflater());
        nsvDrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.personality_type_nsv);
        backDrawable = (VectorDrawable) ContextCompat.getDrawable(this, R.drawable.ic_outline_arrow_back_ios_new_24);
        init();
        setContentView(binding.getRoot());
    }
    private void init() {
        initToolbar();
        initAppbar();
        binding.tvPersonality.setText(Types.get(type).name);
        getWindow().getDecorView().setBackgroundColor(Types.get(type).color);
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
                    isCollapsed = true;
                    nsvDrawable.setCornerRadius(0);
                    backDrawable.setTint(getResources().getColor(R.color.fontBColor));
                    getSupportActionBar().setHomeAsUpIndicator(backDrawable);
                    binding.nsv.setBackground(nsvDrawable);
                }
                else {
                    isCollapsed = false;
                    nsvDrawable.setCornerRadius(dpToPx(35));
                    backDrawable.setTint(getResources().getColor(R.color.bgColor));
                    getSupportActionBar().setHomeAsUpIndicator(backDrawable);
                    binding.nsv.setBackground(nsvDrawable);
                }
            }
        });
    }
    private void initToolbar() {
        type = getIntent().getIntExtra("type", 0);
        binding.llToolbar.setBackgroundColor(Types.get(type).color);
//        binding.tvTitle.setText(Types.get(type).name);
//        binding.tvTitle.setTextColor(Types.get(type).color);
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
        if (isCollapsed == true)
            backDrawable.setTint(getResources().getColor(R.color.bgColor));
        super.onBackPressed();
    }
}