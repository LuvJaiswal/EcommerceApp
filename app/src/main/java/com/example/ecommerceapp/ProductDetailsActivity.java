package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView product_name, product_description, product_price;
    private ImageView product_image;
    private ElegantNumberButton numberButton;
    private Button add_to_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product_name= findViewById(R.id.product_name_details);
        product_description= findViewById(R.id.product_description_details);
        product_price= findViewById(R.id.product_price_details);
        product_image = findViewById(R.id.product_image_details);

        numberButton= findViewById(R.id.number_btn);
        add_to_cart = findViewById(R.id.add_to_cart_btn);



    }
}
