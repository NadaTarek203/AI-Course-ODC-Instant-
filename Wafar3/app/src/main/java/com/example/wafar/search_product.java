package com.example.wafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class search_product extends AppCompatActivity {
    BottomNavigationView navView;
    DBHelper DB;
    ListView userslist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        navView = findViewById(R.id.nav_view);
        DB = new DBHelper(this);
        userslist = findViewById(R.id.userslist1);
        listItem = new ArrayList<>();
        get_product();
        SearchView searchveiw=(SearchView) findViewById(R.id.se);
        searchveiw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<String>users=new ArrayList<>();

                for (String user : listItem){

                    if (user.toLowerCase().contains(s.toLowerCase()))
                    {

                        users.add(user);

                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(search_product.this,
                        android.R.layout.simple_expandable_list_item_1,users);
                userslist.setAdapter(adapter);

                return true;
            }
        });



        navView.setSelectedItemId(R.id.navigation_search);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_search:

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
    private void get_product() {
        Cursor cursor = DB.get_product();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
                listItem.add(cursor.getString(3));
                listItem.add(cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userslist.setAdapter(adapter);

        }
    }
}