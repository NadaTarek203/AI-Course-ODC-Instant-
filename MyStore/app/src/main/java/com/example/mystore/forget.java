package com.example.mystore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class forget extends AppCompatActivity {
Button reset;
StoreDataBase users;
EditText email;
String subject="Reset Password",message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        users= new StoreDataBase(forget.this);
        reset=(Button)findViewById(R.id.resetpassword);
        email=(EditText)findViewById(R.id.forgetemail);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString= email.getText().toString().trim();
                if((!emailString.equals(""))&&users.availableEmail(emailString)){
                    // ha set el password
                    String password=users.getPassword(emailString);
                     message="your password is "+password;

                     JavaMailApi javaMailApi= new JavaMailApi(forget.this,emailString,subject,message);
                     javaMailApi.execute();

                }else{
                    email.setError("Please enter your registerd email");
                    Toast.makeText(forget.this,"Please enter your register Email",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}