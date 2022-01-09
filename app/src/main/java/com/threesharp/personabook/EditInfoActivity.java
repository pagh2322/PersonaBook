package com.threesharp.personabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
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
    private Dialog editDialog;

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
        if (MyInfo.getSex() == 0)
            binding.rbMale.setChecked(true);
        else
            binding.rbFemale.setChecked(true);
        int binary = Types.getBinary(MyInfo.getType());
        int i = binary/1000;
        int s = 10*i - binary/100;
        int t = 100*i + 10*s - binary/10;
        int j = binary%10;
        if (i == 0)
            binding.rbI.setChecked(true);
        else
            binding.rbE.setChecked(true);
        if (s == 0)
            binding.rbS.setChecked(true);
        else
            binding.rbN.setChecked(true);
        if (t == 0)
            binding.rbT.setChecked(true);
        else
            binding.rbF.setChecked(true);
        if (j == 0)
            binding.rbJ.setChecked(true);
        else
            binding.rbP.setChecked(true);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        binding.tvTitle.setText("내 정보");
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
                editDialog.show();
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
        editDialog = new Dialog(EditInfoActivity.this);
        editDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editDialog.setContentView(R.layout.edit_dialog);
        editDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvCancel = editDialog.findViewById(R.id.tv_editCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDialog.dismiss();
            }
        });
        TextView tvEdit = editDialog.findViewById(R.id.tv_edit);
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbMale.isChecked())
                    MyInfo.setSex(0);
                else
                    MyInfo.setSex(1);
                int i, s, t, j;
                if (binding.rbI.isChecked())
                    i = 0;
                else
                    i = 1;
                if (binding.rbS.isChecked())
                    s = 0;
                else
                    s = 1;
                if (binding.rbT.isChecked())
                    t = 0;
                else
                    t = 1;
                if (binding.rbJ.isChecked())
                    j = 0;
                else
                    j = 1;
                int type = 1000*i + 100*s + 10*t + j;
                MyInfo.setType(Types.getType(type));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        cancelDialog.show();
    }
}