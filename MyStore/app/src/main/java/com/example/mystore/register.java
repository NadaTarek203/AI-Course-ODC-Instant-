package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    String dateString;
    String gender;
    StoreDataBase newCustomer=new StoreDataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText Email=(EditText) findViewById(R.id.remail);
        EditText password=(EditText) findViewById(R.id.rpassword);
        EditText name=(EditText)findViewById(R.id.name);
        EditText job=(EditText)findViewById(R.id.rjop);
        RadioButton malebtn=(RadioButton)findViewById(R.id.male);
        RadioButton femalebtn=(RadioButton)findViewById(R.id.female);
        CalendarView calendarView=(CalendarView)findViewById(R.id.calendarView);
        Button save= (Button) findViewById(R.id.rRegisterbtn);
        TextView Have_account;

        Have_account=findViewById(R.id.haveAccount);
        Have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register.this,SignIn.class);
                startActivity(i);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateString=String.valueOf(i2)+"/"+String.valueOf(i1)+"/"+String.valueOf(i);
            }
        });

        // na2es el validation
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString= name.getText().toString();
                String emailString= Email.getText().toString();
                String PasswordString=password.getText().toString();
                String jobString =job.getText().toString();

                if(nameString.isEmpty()){
                    name.setError("this field can't be empty");
                }
                if(emailString.isEmpty()){
                    Email.setError("this field can't be empty");
                }
                if(PasswordString.isEmpty()){
                    password.setError("this field can't be empty");
                }

                // fadel 7tt el calender
                if(dateString.isEmpty()){
                    dateString="";
                }

                if(malebtn.isChecked())
                {
                    gender = "male";
                }else if(femalebtn.isChecked())
                {
                    gender="female";
                } else{
                    gender="";
                }
                if(jobString.isEmpty()){
                    job.setError("this field can't be empty");
                }

                if(!(nameString.isEmpty())&& !(emailString.isEmpty()) && !(PasswordString.isEmpty()) && !(jobString.isEmpty())){
                    newCustomer.CreateNewCustomer(nameString,emailString,PasswordString,gender,dateString,jobString);
                    Intent i = new Intent(register.this,SignIn.class);
                    startActivity(i);
                }

            }
        });



    }
}