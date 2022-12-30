package com.example.mystore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedProduct extends AppCompatActivity {
ImageView Product_image;
TextView Product_price;
TextView Product_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);
        Product_image=findViewById(R.id.prodImg);
        Product_price=findViewById(R.id.ProdPrice);
        Product_Details=findViewById(R.id.details);
        Bundle extras= getIntent().getExtras();
        if(extras != null)
        {
            String details=extras.getString("ProdName");
            //int price= extras.getInt("Price");

            Product_Details.setText(details);
            //Product_price.setText(price);
        }
    }
}