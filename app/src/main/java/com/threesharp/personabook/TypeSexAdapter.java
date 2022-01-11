package com.threesharp.personabook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TypeSexAdapter extends RecyclerView.Adapter<TypeSexAdapter.ViewHolder> {
    private List<Persona> personaList;
    private Context context;

    public TypeSexAdapter(Context context, List<Persona> personaList) {
        this.context = context;
        this.personaList = personaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.type_man, parent, false);
        TypeSexAdapter.ViewHolder vh = new TypeSexAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Persona item = personaList.get(position);
        holder.typedName.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typedName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typedName = itemView.findViewById(R.id.tv_typedName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent ptActivity = new Intent(context, PersonaInfoActivity.class);
                        context.startActivity(ptActivity);
                    }
                }
            });
        }
    }
}
