package com.example.mystore;

import static com.example.mystore.R.id.loginbtn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    StoreDataBase userDB;
      Button login;
      EditText email;
      EditText pass;
    TextView forget,Create_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userDB= new StoreDataBase(this);
        login= findViewById(loginbtn);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        forget=(TextView) findViewById(R.id.forget);
        CheckBox remember=(CheckBox)findViewById(R.id.checkBox);
        TextView error=(TextView)findViewById(R.id.error);
     Create_account=findViewById(R.id.createAccount);
    Create_account.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(SignIn.this,register.class);
            startActivity(i);
        }
    });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString= email.getText().toString();
                String passString=pass.getText().toString();

                if(!(emailString.equals("")) && !(passString.equals(""))){
                    if(userDB.availableUser(emailString,passString)){
                        error.setText("");
                        if(remember.isChecked()){
                            SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("remember","true");
                            editor.apply();
                        }else{
                            SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("remember","false");
                            editor.apply();
                        }
                        Intent i = new Intent(SignIn.this,CategoriesPage.class);
                        startActivity(i);

                        //mafrod b2a yru7 ll home page
                    }else{
                        error.setText("Incorrect email or password");
                    }
                }else{
                    Toast.makeText(SignIn.this, "Please enter your data", Toast.LENGTH_SHORT).show();
                }
            }

        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), forget.class);
                startActivity(i);
            }
        });
    }
}