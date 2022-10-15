package com.example.wafar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        String data1[];
        String data2[];
        int image[];
        Context context;

    public RAdapter(String[] data1, String[] data2, int[] image, Context context) {
        this.data1 = data1;
        this.data2 = data2;
        this.image = image;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.product,parent,false);
        RecyclerAdapter.ViewHolder viewHolder=new RecyclerAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView2.setText(String .valueOf(data2[position]));
        holder.textView.setText(String.valueOf(data1[position]));
        holder.imageView.setImageResource(image[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ProductDescription.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return image.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView Tv1 , Tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView=itemView.findViewById(R.id.productImage);
            Tv1=itemView.findViewById(R.id.name);
            Tv2=itemView.findViewById(R.id.price);
        }
    }
}
