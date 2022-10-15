package com.example.wafar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Cameras extends AppCompatActivity {
    RecyclerView recyclerView;
    RAdapter rAdapter;
    String l1[];
    String l2[];

    ImageView addToCart;
   Button sell;

    int img[]={R.drawable.nikonnn,R.drawable.nikon1,R.drawable.nikon3,R.drawable.canon22,R.drawable.canon,R.drawable.canon2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameras);
        l1=getResources().getStringArray(R.array.Products);
        l2=getResources().getStringArray(R.array.price);

        recyclerView=findViewById(R.id.recyclerview);
        rAdapter =new RAdapter(l1,l2,img,this);

        addToCart=findViewById(R.id.Add);
        recyclerView.setAdapter(rAdapter);


        DividerItemDecoration divider=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        sell=findViewById(R.id.sell);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cameras.this,ProductConifmation.class);
                startActivity(intent);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Cameras.this,Seller.class);
                startActivity(it);
            }
        });

    }
}