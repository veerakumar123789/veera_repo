package com.example.myapp.adapter;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.StockClass;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    private List<StockClass> mdata = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        StockClass model = mdata.get(position);
        holder.code.setText(model.getProductCode());
        holder.name.setText(model.getProductName());
        holder.description.setText(model.getDescription());
        holder.partNo.setText(model.getPartNo());
        holder.qty.setText(model.getQty());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public void setData(List<StockClass> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView code,name,description,partNo,qty;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.productcode);
            name = itemView.findViewById(R.id.productname);
            description = itemView.findViewById(R.id.description);
            partNo = itemView.findViewById(R.id.partno);
            qty = itemView.findViewById(R.id.qty);
        }
    }
}


