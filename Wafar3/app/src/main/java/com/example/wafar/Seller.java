package com.example.wafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class Seller extends AppCompatActivity {
    BottomNavigationView navView;
    DBHelper DB;
    ListView userslist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        navView = findViewById(R.id.nav_view);
        DB=new DBHelper( this);
        listItem =new ArrayList<>();

        userslist= findViewById(R.id.userslist);
        get_product();
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_home:

                        return true;
                    case R.id.navigation_search:
                        startActivity(new Intent(getApplicationContext(),search_product.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_add:
                        startActivity(new Intent(getApplicationContext(),addproduct.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
    private void get_product(){
        Cursor cursor =DB.get_product();
        if (cursor.getCount()==0) {
            Toast.makeText(this, "no data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0 ));
                listItem.add(cursor.getString(1 ));
                listItem.add(cursor.getString(2 ));
                listItem.add(cursor.getString(3 ));
                listItem.add(cursor.getString(4 ));
            }
            adapter=new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,listItem);
            userslist.setAdapter(adapter);

        }
    }

}