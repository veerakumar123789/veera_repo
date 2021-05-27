package com.example.myapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adaptery extends RecyclerView.Adapter<adaptery.MyViewHolder> {



    private Context mContext;
    private List<StockClass> mdata;

    public adaptery(Context mContext, List<StockClass> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.row_layout,parent,false);
        return  new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.productCode.setText(mdata.get(position).getProductCode());
        holder.productName.setText(mdata.get(position).getProductName());
        holder.description.setText(mdata.get(position).getDescription());
        holder.partNo.setText(mdata.get(position).getPartNo());
        holder.qty.setText(mdata.get(position).getQty());


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    public static class  MyViewHolder extends  RecyclerView.ViewHolder{
        TextView id;
        TextView productCode;
        TextView productName;
        TextView description;
        TextView partNo;
        TextView qty;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productCode =itemView.findViewById(R.id.productcode);
            productName = itemView.findViewById(R.id.productname);
            description = itemView.findViewById(R.id.description);
            partNo = itemView.findViewById(R.id.partno);
            qty =itemView.findViewById(R.id.qty);




        }
    }


}
