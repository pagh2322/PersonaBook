package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.threesharp.personabook.databinding.ActivityEditInfoBinding;

public class EditInfoActivity extends AppCompatActivity {
    private ActivityEditInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInfoBinding.inflate(getLayoutInflater());
        init();
        setContentView(binding.getRoot());
    }

    private void init() {
        initToolbar();
    }
    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        switch(getIntent().getStringExtra("title")) {
            case "me":
                binding.tvTitle.setText("내 정보");
                break;
            case "persona":
                binding.tvTitle.setText("페르소나 정보");
                break;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_close_24);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
    private void loadDialog() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(EditInfoActivity.this);
        dlg.setMessage("삭제할까요?");
        dlg.setPositiveButton("삭제",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlg.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}