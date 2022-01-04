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
import android.view.WindowManager;
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
        typeWidgetAdapter = new TypeWidgetAdapter(this, typeList);
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
                Intent addPersonaIntent = new Intent(getApplicationContext(), AddPersonaActivity.class);
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

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // initialize type widgets
    private void initRVType() {
        new Types(getApplicationContext());
        for (Types.Type type : Types.types) {
            addType(type.name, "0", type.color);
        }
        typeWidgetAdapter.notifyDataSetChanged();
    }
    private void addType(String type, String num, int background) {
        TypeWidget typeWidget = new TypeWidget();
        typeWidget.setType(type);
        typeWidget.setNumber(num);
        typeWidget.setBackground(background);
        typeList.add(typeWidget);
    }
}