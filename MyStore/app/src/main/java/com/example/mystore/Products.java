package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    StoreDataBase Db;
    private RecyclerView rec;
    private prod_adapter adapt;
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inf = new MenuInflater(this);
//        inf.inflate(R.menu.menu1,menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case (R.id.pro):
//                return true;
//            case (R.id.cart):
//                return true;
//            case (R.id.logout):
//                return true;
//
//        }
//        return true;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Db=new StoreDataBase(this);
        rec=findViewById(R.id.recview);

        String text = getIntent().getExtras().getString("searchText");
        String voice = getIntent().getExtras().getString("searchVoice");
        int Cat_ID = getIntent().getExtras().getInt("Cat_ID");

        // Todo: compare barcode with database barcodes & handling camera constraints (Ashraf)
        byte[] cam = getIntent().getExtras().getByteArray("searchBarcode");

        List<Product_Details> product_CatID = Db.getCatProducts(Cat_ID);
        List<Product_Details> product_detailsList = Db.getproducts();
        List<Product_Details> newproduct_detailsList = new ArrayList<>();

        String[] subs = {""};
        if(text != null && !text.isEmpty()) {
            subs = text.split(" ");
        }
        else if (voice != null && !voice.isEmpty()){
            subs = voice.split(" ");
        }


        for(int i= 0;i<product_detailsList.size();i++)
        {
            Product_Details p = product_detailsList.get(i);
            int count =0;
            for(String item2 :subs )
            {
                if(p.getProd_Name().toLowerCase().contains(item2.toLowerCase()))
                {
                    count++;
                }
            }
            if(count ==subs.length)
            {
                newproduct_detailsList.add(p);
            }
        }


        if (product_CatID != null && !product_CatID.isEmpty()){
            adapt = new prod_adapter(this, product_CatID, new prod_adapter.ItemClickListerner() {
                @Override
                public void onItemClick(Product_Details details) {
                    Toast.makeText(Products.this, details.getProd_Name(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Products.this,SelectedProduct.class);

                    i.putExtra("ProdName",details.getProd_Name());
                    i.putExtra("Price",details.getPrice());
                    i.putExtra("ProdImage",details.getImage());
                    startActivity(i);

                }
            });
            rec.setLayoutManager(new LinearLayoutManager(this));
            rec.setAdapter(adapt);
        }

        else if(newproduct_detailsList.size() > 0) {
            adapt = new prod_adapter(this, newproduct_detailsList, new prod_adapter.ItemClickListerner() {
                @Override
                public void onItemClick(Product_Details details) {
                    Toast.makeText(Products.this, details.getProd_Name(), Toast.LENGTH_SHORT).show();
                   /* Intent i=new Intent(Products.this,SelectedProduct.class);

                    i.putExtra("ProdName",details.getProd_Name());
                    i.putExtra("Price",details.getPrice());
                    i.putExtra("ProdImage",details.getImage());
                    startActivity(i);*/
                }
            });
            rec.setLayoutManager(new LinearLayoutManager(this));
            rec.setAdapter(adapt);
        }

    }
}