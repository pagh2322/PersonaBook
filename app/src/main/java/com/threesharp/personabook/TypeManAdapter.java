package com.threesharp.personabook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TypeManAdapter extends RecyclerView.Adapter<TypeManAdapter.ViewHolder> {
    private List<Persona> personaList;
    private Context context;
    private PersonaDatabase database;

    public TypeManAdapter(Context context, List<Persona> personaList) {
        this.context = context;
        this.personaList = personaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
