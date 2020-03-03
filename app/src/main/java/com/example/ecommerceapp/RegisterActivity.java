package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

  private Button createAccountBtn;
  private EditText InputName,InputPhone,Inputpassword;
  private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountBtn =(Button)findViewById(R.id.Reg_createAcc_Btn);
        InputName= (EditText)findViewById(R.id.Reg_ET_name);
        InputPhone =(EditText)findViewById(R.id.Reg_ET_phone);
        Inputpassword =(EditText)findViewById(R.id.Reg_ET_password);

        loadingBar =new ProgressDialog(this);


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cretaeAccount();
            }
        });


    }

    private void cretaeAccount() {

        String name =InputName.getText().toString();
        String phone =InputPhone.getText().toString();
        String password =Inputpassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
        }

       else {
           loadingBar.setTitle("Create Account");
           loadingBar.setMessage("Please wait while we are checking the credentials.");
           loadingBar.setCanceledOnTouchOutside(false);
           loadingBar.show();

        }



    }



    public void tv_alreadyaccount(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}
