package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.threesharp.personabook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<TypeWidget> typeList = new ArrayList<TypeWidget>();
    TypeWidgetAdapter typeWidgetAdapter = null;
    TypeRelationWidgetAdapter typeRelationWidgetAdapter = null;
    private PersonaDatabase personaDB = null;
    GradientDrawable tpDrawable;

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
    private void initThread() {
        initData();
        setTypeList();
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
                addType(Types.types.get(i).name, personaDB.personaDao().load(i).size(), Types.types.get(i).color, Types.getRelationBackground(this, myType.relation[i]));
            }
            typeWidgetAdapter.notifyDataSetChanged();
            typeRelationWidgetAdapter.notifyDataSetChanged();
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