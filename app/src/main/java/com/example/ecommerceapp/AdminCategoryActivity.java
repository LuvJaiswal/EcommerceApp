package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView Kurtha, Lehnga, Saree;

    private Button checkOrdersBtn, logoutAdminBtn, manageProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();

        checkOrdersBtn = (Button) findViewById(R.id.chek_orders_btn);
        logoutAdminBtn = (Button) findViewById(R.id.logout_admin_btn);
        manageProducts = (Button) findViewById(R.id.manageProduct_Btn);

        manageProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, HomeActivity.class);
               intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });


        logoutAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        checkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminCheckOrders.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        Kurtha = (ImageView) findViewById(R.id.kurtha);
        Lehnga = (ImageView) findViewById(R.id.lehnga);
        Saree = (ImageView) findViewById(R.id.saree);


        Kurtha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "kurtha");
                startActivity(intent);
            }
        });

        Lehnga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "saree");
                startActivity(intent);
            }
        });

        Saree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "saree");
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        moveTaskToBack(true);
    }
}
