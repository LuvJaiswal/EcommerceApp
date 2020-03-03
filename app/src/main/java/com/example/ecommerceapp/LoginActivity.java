package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button LoginButton;
    private EditText Input_LoginPhone,Input_Login_password;

    private ProgressDialog loadingBar;

    private String parentDbName = "Users";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton=(Button)findViewById(R.id.LoginBtn);
        Input_LoginPhone = (EditText)findViewById(R.id.login_phone);
        Input_Login_password =(EditText)findViewById(R.id.login_password);

        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String phone =Input_LoginPhone.getText().toString();
        String password =Input_Login_password.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

           AllowAccessToAccount(phone,password);

        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData =dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    }


                }
                else{
                    Toast.makeText(LoginActivity.this, "Account with this" +phone + "number donot exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    //Toast.makeText(LoginActivity.this, "You need to create a new Account", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
