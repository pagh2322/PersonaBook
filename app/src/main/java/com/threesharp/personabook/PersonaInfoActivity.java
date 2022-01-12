package com.threesharp.personabook;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;

import com.threesharp.personabook.databinding.ActivityPersonaInfoBinding;

public class PersonaInfoActivity extends AppCompatActivity {
    private ActivityPersonaInfoBinding binding;
    private Dialog deleteDialog;
    private PersonaDatabase personaDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonaInfoBinding.inflate(getLayoutInflater());
        personaDB = PersonaDatabase.getInstance(this);
        initToolbar();
        initInfo();
        initMenu();
        setContentView(binding.getRoot());
    }
    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back_ios_new_b24);
    }
    private void initInfo() {
        binding.tvPersonality.setText(Types.get(getIntent().getIntExtra("type", 0)).name);
        binding.tvName.setText(getIntent().getStringExtra("name"));
        if (getIntent().getIntExtra("sex", 0) == 0)
            binding.tvSex.setText(R.string.male);
        else
            binding.tvSex.setText(R.string.female);
    }
    private void initMenu() {
        deleteDialog = new Dialog(PersonaInfoActivity.this);
        deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        deleteDialog.setContentView(R.layout.delete_dialog);
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvDeleteCancel = deleteDialog.findViewById(R.id.tv_deleteCancel);
        tvDeleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.dismiss();
            }
        });
        TextView tvDelete = deleteDialog.findViewById(R.id.tv_delete);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personaDB.personaDao().delete(getIntent().getIntExtra("id", 0));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.persona_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.tb_delete:
                deleteDialog.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}