package com.example.mystore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class prod_adapter extends RecyclerView.Adapter<prod_adapter.ViewHolder> {
    private Context context;
    private List<Product_Details>list;
    private ItemClickListerner itemClickListerner;

    public prod_adapter(Context c, List<Product_Details> postlist,ItemClickListerner itemClickListerner)
    {
        context=c;
        list=postlist;
        this.itemClickListerner=itemClickListerner;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.product_post,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product_Details p = list.get(position);
        holder.name.setText(p.getProd_Name());
        holder.quantity.setText(String.valueOf(p.getQuantity()));
        holder.price.setText(String.valueOf(p.getPrice()));
        Bitmap bmp = BitmapFactory.decodeByteArray(p.getImage(), 0, p.getImage().length);
        holder.image.setImageBitmap(bmp);
        holder.itemView.setOnClickListener(v -> {
            itemClickListerner.onItemClick(p);
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

       TextView name,price,quantity;
       Button add;
       ImageView image;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           name = itemView.findViewById(R.id.name);
           price=itemView.findViewById(R.id.price);
           quantity= itemView.findViewById(R.id.quantity);
           add=itemView.findViewById(R.id.addtocart);
           image = itemView.findViewById(R.id.img);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });
       }
   }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public  interface ItemClickListerner{
       void onItemClick(Product_Details details);
    }
}
