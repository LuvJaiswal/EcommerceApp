package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class ProductDetailActivity extends AppCompatActivity {

    private Button addToCartButton;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productdescription, productprice, productname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        addToCartButton =(Button)findViewById(R.id.product_add_to_cart_btn);
        productdescription=(TextView)findViewById(R.id.product_Description_details);
        productname=(TextView)findViewById(R.id.product_name_detail);
        productprice=(TextView)findViewById(R.id.product_price_details);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_btn);
        productImage=(ImageView) findViewById(R.id.product_image_details);


    }
}
