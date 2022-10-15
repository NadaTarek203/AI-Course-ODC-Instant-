package com.example.wafar;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    String data1[];
    String data2[];
    int image[];
    Context context;

    public RecyclerAdapter(Context ct,String s1[],String s2[],int images[]) {
        context=ct;
        data1=s1;
        data2=s2;
        image=images;

    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //create individual row

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //
        holder.textView2.setText(String .valueOf(data2[position]));
        holder.textView.setText(String.valueOf(data1[position]));
        holder.imageView.setImageResource(image[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Cameras.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return image.length;
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //idntify each component in row
        ImageView imageView;
        TextView textView , textView2;

        public ViewHolder(@NonNull View itemView)   {
            super(itemView);
            imageView=itemView.findViewById(R.id.productImage);
            textView=itemView.findViewById(R.id.name);
            textView2=itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),textView.getText(),Toast.LENGTH_SHORT).show();

        }
    }
}


