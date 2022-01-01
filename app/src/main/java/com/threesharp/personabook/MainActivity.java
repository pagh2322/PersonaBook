package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.threesharp.personabook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<TypeWidget> typeList = new ArrayList<TypeWidget>();
    TypeWidgetAdapter typeWidgetAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        typeWidgetAdapter = new TypeWidgetAdapter(typeList);
        binding.rvType.setAdapter(typeWidgetAdapter);
        binding.rvType.setLayoutManager(new GridLayoutManager(this,4));
        init();
        setContentView(binding.getRoot());
    }

    // initialize
    private void init() {
        initBottomAppbar();
        initRVType();
    }

    // initialize bottom appbar
    private void initBottomAppbar() {
        binding.btappbar.replaceMenu(R.menu.bottom_app_bar_menu);
        setSupportActionBar(binding.btappbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_web_asset_24);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPersonaIntent = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(addPersonaIntent);
            }
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
                binding.btappbar.replaceMenu(R.menu.re_bottom_app_bar_menu);
                break;
            case R.id.ab_notRelation:
                binding.btappbar.replaceMenu(R.menu.bottom_app_bar_menu);
                break;
            case R.id.ab_me:
                Intent myInfoIntent = new Intent(getApplicationContext(), MeActivity.class);
                startActivity(myInfoIntent);
                break;
            case R.id.ab_setting:
                Toast.makeText(getApplicationContext(), "Press Setting", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // initialize type widgets
    private void initTypeColor() {
        TypeColor.ISTJ = getColor(R.color.ISTJ);
        TypeColor.ISFJ = getColor(R.color.ISFJ);
        TypeColor.INFJ = getColor(R.color.INFJ);
        TypeColor.INTJ = getColor(R.color.INTJ);
        TypeColor.ISTP = getColor(R.color.ISTP);
        TypeColor.ISFP = getColor(R.color.ISFP);
        TypeColor.INFP = getColor(R.color.INFP);
        TypeColor.INTP = getColor(R.color.INTP);
        TypeColor.ESTP = getColor(R.color.ESTP);
        TypeColor.ESFP = getColor(R.color.ESFP);
        TypeColor.ENFP = getColor(R.color.ENFP);
        TypeColor.ENTP = getColor(R.color.ENTP);
        TypeColor.ESTJ = getColor(R.color.ESTJ);
        TypeColor.ESFJ = getColor(R.color.ESFJ);
        TypeColor.ENFJ = getColor(R.color.ENFJ);
        TypeColor.ENTJ = getColor(R.color.ENTJ);

    }
    public void initRVType() {
        initTypeColor();
        addType("ISTJ-A/T", "4", TypeColor.ISTJ);
        addType("ISFJ-A/T", "14", TypeColor.ISFJ);
        addType("INFJ-A/T", "2", TypeColor.INFJ);
        addType("INTJ-A/T", "0", TypeColor.INTJ);
        addType("ISTP-A/T", "1", TypeColor.ISTP);
        addType("ISFP-A/T", "12", TypeColor.ISFP);
        addType("INFP-A/T", "21", TypeColor.INFP);
        addType("INTP-A/T", "1", TypeColor.INTP);
        addType("ESTP-A/T", "5", TypeColor.ESTP);
        addType("ESFP-A/T", "0", TypeColor.ESFP);
        addType("ENFP-A/T", "9", TypeColor.ENFP);
        addType("ENTP-A/T", "14", TypeColor.ENTP);
        addType("ESTJ-A/T", "2", TypeColor.ESTJ);
        addType("ESFJ-A/T", "6", TypeColor.ESFJ);
        addType("ENFJ-A/T", "15", TypeColor.ENFJ);
        addType("ENTJ-A/T", "0", TypeColor.ENTJ);
        typeWidgetAdapter.notifyDataSetChanged();
    }
    public void addType(String type, String num, int background) {
        TypeWidget typeWidget = new TypeWidget();
        typeWidget.setType(type);
        typeWidget.setNumber(num);
        typeWidget.setBackground(background);
        typeList.add(typeWidget);
    }
}