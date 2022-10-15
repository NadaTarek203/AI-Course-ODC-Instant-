package com.example.wafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class addproduct extends AppCompatActivity {
    EditText id;
    EditText name;
    EditText price;
    EditText category;
    TextView sellid;
    DBHelper DB;
    BottomNavigationView navView;
    RelativeLayout conlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        navView = findViewById(R.id.nav_view);
        id=(EditText) findViewById(R.id.editTextTextPersonName);
        name=(EditText) findViewById(R.id.editTextTextPersonName2);
        price=(EditText) findViewById(R.id.editTextTextPersonName3);
        category=(EditText) findViewById(R.id.editTextTextPersonName4);
        sellid=findViewById(R.id.editTextTextPersonName);
        DB=new DBHelper( this);
        conlay=(RelativeLayout)findViewById(R.id.con2);

        navView.setSelectedItemId(R.id.navigation_add);
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
                        startActivity(new Intent(getApplicationContext(),search_product.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_add:

                        return true;
                }
                return false;
            }
        });
        conlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idtxt = id.getText().toString();
                String nametxt = name.getText().toString();
                String pricetxt = price.getText().toString();
                String categorytxt = category.getText().toString();
                String selltxt=sellid.getText().toString();

                Boolean checkinsert = DB.insertproduct(idtxt, nametxt, pricetxt, categorytxt,selltxt);
                if (checkinsert == true) {
                    Toast.makeText(addproduct.this, "succeed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(addproduct.this, "faild", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}