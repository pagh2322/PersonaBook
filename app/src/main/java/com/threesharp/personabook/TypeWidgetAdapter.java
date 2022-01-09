package com.threesharp.personabook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TypeWidgetAdapter extends RecyclerView.Adapter<TypeWidgetAdapter.ViewHolder> {
    private ArrayList<TypeWidget> mData;
    private Context context;
    private PersonaDatabase database;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    TypeWidgetAdapter(Context context, ArrayList<TypeWidget> list) {
        this.context = context;
        mData = list ;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type ;
        TextView number ;
        CardView background ;
        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            type = itemView.findViewById(R.id.tv_type) ;
            number = itemView.findViewById(R.id.tv_num) ;
            background = itemView.findViewById(R.id.cv_typeView) ;
            // When click view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent ptActivity = new Intent(context, PersonalityTypeActivity.class);
                        ptActivity.putExtra("type", pos);
                        context.startActivity(ptActivity);
                    }
                }
            });
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public TypeWidgetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.type_widget, parent, false) ;
        TypeWidgetAdapter.ViewHolder vh = new TypeWidgetAdapter.ViewHolder(view) ;
        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(TypeWidgetAdapter.ViewHolder holder, int position) {
        TypeWidget item = mData.get(position) ;
        holder.type.setText(item.getType()) ;
        holder.number.setText(item.getNumber()) ;
        holder.background.setCardBackgroundColor(item.getBackground());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}