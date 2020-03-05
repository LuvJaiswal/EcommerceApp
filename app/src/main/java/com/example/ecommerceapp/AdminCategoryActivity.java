package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView Kurtha,Lehnga,Saree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();

        Kurtha =(ImageView)findViewById(R.id.kurtha) ;
        Lehnga =(ImageView)findViewById(R.id.lehnga);
        Saree =(ImageView)findViewById(R.id.saree);


        Kurtha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","kurtha");
                startActivity(intent);
            }
        });

        Lehnga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","saree");
                startActivity(intent);
            }
        });

        Saree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","saree");
                startActivity(intent);
            }
        });


    }
}
