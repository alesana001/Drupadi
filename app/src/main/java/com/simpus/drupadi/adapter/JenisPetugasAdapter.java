package com.simpus.drupadi.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.simpus.drupadi.R;
import com.simpus.drupadi.RegistrasiActivity;
import com.simpus.drupadi.model.ListJenis;

import java.util.ArrayList;

import butterknife.OnClick;

public class JenisPetugasAdapter  extends RecyclerView.Adapter<JenisPetugasAdapter.JenisPetugasViewHolder>{

    public static ArrayList<ListJenis> data;
    private Context context;

    public JenisPetugasAdapter(Context context, ArrayList<ListJenis> data) {
        super();
        this.context = context;
        this.data= data;
    }
    @Override
    public JenisPetugasAdapter.JenisPetugasViewHolder onCreateViewHolder(ViewGroup parent, int position){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        JenisPetugasViewHolder view = new JenisPetugasViewHolder(layoutView);
        return view;
    }
    @Override
    public void onBindViewHolder(JenisPetugasAdapter.JenisPetugasViewHolder holder, int position) {
    final ListJenis objIncome = data.get(position);
      holder.list_item.setText(data.get(position).getName());

     // holder.list_item.setOnClickListener(view -> RegistrasiActivity.idptgs.add(objIncome.getId()));
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public class JenisPetugasViewHolder extends RecyclerView.ViewHolder{
        TextView list_item;
        SharedPreferences sharedPreferences;

        public JenisPetugasViewHolder(View itemView) {
            super(itemView);
            list_item =(TextView)itemView.findViewById(R.id.list_item);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                //masukkan kedalam obj
                RegistrasiActivity.idptgs.add(data.get(position).getId());

            });
        }

    }
}

