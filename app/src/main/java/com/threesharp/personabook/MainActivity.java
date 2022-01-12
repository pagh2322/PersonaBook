package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.threesharp.personabook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<TypeWidget> typeList = new ArrayList<TypeWidget>();
    TypeWidgetAdapter typeWidgetAdapter = null;
    TypeRelationWidgetAdapter typeRelationWidgetAdapter = null;
    private PersonaDatabase personaDB = null;
    GradientDrawable tpDrawable;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    int adNumber = 0;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        typeWidgetAdapter = new TypeWidgetAdapter(this, typeList);
        typeRelationWidgetAdapter = new TypeRelationWidgetAdapter(this, typeList);
        binding.rvType.setAdapter(typeWidgetAdapter);
        binding.rvType.setLayoutManager(new GridLayoutManager(this,4));
        new Types(this);
        new MyInfo(this);
        personaDB = PersonaDatabase.getInstance(this);
        initBottomAppbar();
        // initialize data
        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                initThread();
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();
        setContentView(binding.getRoot());
    }
    private void initAd() {
        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        InterstitialAd.load(MainActivity.this, AD_UNIT_ID, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
        if (mInterstitialAd != null)
            mInterstitialAd.show(MainActivity.this);
    }
    private void initThread() {
        initData();
        setTypeList();
        setGoodType();
        setColor();
    }
    // initialize persona database
    private void initData() {
        int totalNumber = personaDB.personaDao().getAll().size();
        binding.tvTotalNumber.setText(String.valueOf(totalNumber));
    }
    // initialize background color and total persona number
    private void setColor() {
        int type = MyInfo.getType();
        tpDrawable = (GradientDrawable) ContextCompat.getDrawable(MainActivity.this, R.drawable.total_personas);
        tpDrawable.setColor(Types.get(type).sColor);
        binding.llTotalPersona.setBackground(tpDrawable);
        binding.ctlMyPersona.setCollapsedTitleTextColor(Types.get(type).sColor);
        binding.cvSummary.setCardBackgroundColor(ColorStateList.valueOf(Types.get(type).color));
        binding.fab.setBackgroundTintList(ColorStateList.valueOf(Types.get(type).color));
    }
    // initialize type widgets
    private void setTypeList() {
        if (typeList.isEmpty()) {
            Types.Type myType = Types.get(MyInfo.getType());
            for (int i=0; i<Types.types.size(); i++) {
                addType(Types.get(i).name, personaDB.personaDao().load(i).size(), Types.get(i).color, Types.getRelationBackground(this, myType.relation[i]));
            }
            typeWidgetAdapter.notifyDataSetChanged();
            typeRelationWidgetAdapter.notifyDataSetChanged();
        }
    }
    // initialize good types
    private void setGoodType() {
        int type = MyInfo.getType();
        ArrayList<Integer> goodTypes = Types.getGoodTypes(type);
        int size = goodTypes.size();
        binding.tvGoodType1.setText(Types.get(goodTypes.get(0)).name);
        binding.tvNum1.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(0)).size()));
        binding.tvGoodType2.setText(Types.get(goodTypes.get(1)).name);
        binding.tvNum2.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(1)).size()));
        if (size == 3) {
            binding.tvGoodType3.setTextColor(this.getColor(R.color.fontWColor));
            binding.tvGoodType3.setText(Types.get(goodTypes.get(2)).name);
            binding.tvNum3.setTextColor(this.getColor(R.color.fontWColor));
            binding.tvNum3.setText(String.valueOf(personaDB.personaDao().load(goodTypes.get(2)).size()));
            binding.tvNum.setTextColor(this.getColor(R.color.fontWColor));
        }
        else {
            binding.tvGoodType3.setTextColor(Types.get(type).color);
            binding.tvNum3.setTextColor(Types.get(type).color);
            binding.tvNum.setTextColor(Types.get(type).color);
        }
    }
    // initialize bottom appbar and menu
    private void initBottomAppbar() {
        binding.btappbar.replaceMenu(R.menu.bottom_app_bar_menu);
        setSupportActionBar(binding.btappbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_web_asset_24);
        binding.fab.setOnClickListener(v -> {
            Intent addPersonaIntent = new Intent(getApplicationContext(), AddPersonaActivity.class);
            startActivity(addPersonaIntent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_app_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.16personalities.com/ko/%EB%AC%B4%EB%A3%8C-%EC%84%B1%EA%B2%A9-%EC%9C%A0%ED%98%95-%EA%B2%80%EC%82%AC"));
                startActivity(siteIntent);
                break;
            case R.id.ab_relation:
                binding.rvType.setAdapter(typeRelationWidgetAdapter);
                binding.rvType.setLayoutManager(new GridLayoutManager(this,4));
                binding.btappbar.replaceMenu(R.menu.re_bottom_app_bar_menu);
                break;
            case R.id.ab_notRelation:
                binding.rvType.setAdapter(typeWidgetAdapter);
                binding.rvType.setLayoutManager(new GridLayoutManager(this,4));
                binding.btappbar.replaceMenu(R.menu.bottom_app_bar_menu);
                break;
            case R.id.ab_me:
                Intent myInfoIntent = new Intent(getApplicationContext(), MeActivity.class);
                startActivity(myInfoIntent);
                break;
            case R.id.ab_setting:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setColor();
        binding.tvTotalNumber.setText(String.valueOf(personaDB.personaDao().getAll().size()));
        if (!typeList.isEmpty()) {
            typeList.clear();
            setTypeList();
            setGoodType();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adNumber++;
        if (adNumber == 6) {
            initAd();
            adNumber = 1;
        }
    }

    private void addType(String type, int num, int background, int relationBackground) {
        TypeWidget typeWidget = new TypeWidget();
        typeWidget.setType(type);
        typeWidget.setNumber(String.valueOf(num));
        typeWidget.setBackground(background);
        typeWidget.setRelationBackground(relationBackground);
        typeList.add(typeWidget);
    }
}