package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);
    }


    public void HomePage(View view) {
        Intent intent = new Intent(ClientDashboardActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
