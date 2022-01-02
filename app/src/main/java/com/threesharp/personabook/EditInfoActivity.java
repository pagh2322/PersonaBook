package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.threesharp.personabook.databinding.ActivityEditInfoBinding;

public class EditInfoActivity extends AppCompatActivity {
    private ActivityEditInfoBinding binding;
    private Dialog cancelDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInfoBinding.inflate(getLayoutInflater());
        init();
        setContentView(binding.getRoot());
    }

    private void init() {
        initToolbar();
        initDialog();
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
            case R.id.tb_edit:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initDialog() {
        cancelDialog = new Dialog(EditInfoActivity.this);
        cancelDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cancelDialog.setContentView(R.layout.custom_dialog);
        cancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvNo = cancelDialog.findViewById(R.id.tv_no);
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelDialog.dismiss();
            }
        });
        TextView tvYes = cancelDialog.findViewById(R.id.tv_yes);
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        cancelDialog.show();
    }
}