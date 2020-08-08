package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminManageProductsActivity extends AppCompatActivity {

    private Button updateProductBtn;
    private EditText p_namUpdate, p_priceUpdate, p_descriptionUpdate;

    private ImageView updateImage;

    private String productID = "";
    private DatabaseReference productRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_products);

        productID = getIntent().getStringExtra("pid");

        productRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        p_namUpdate = findViewById(R.id.item_product_name_maintain);
        p_priceUpdate = findViewById(R.id.item_product_price_maintain);
        p_descriptionUpdate = findViewById(R.id.item_product_description_maintain);
        updateProductBtn = findViewById(R.id.updateProduct_Btn);
        updateImage = findViewById(R.id.item_product_image_maintain);

        displaySpecificProductInfo();


        updateProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });


    }

    private void applyChanges() {
        String pName =p_namUpdate.getText().toString();
        String pPrice =p_priceUpdate.getText().toString();
        String pDescription =p_descriptionUpdate.getText().toString();

        if (pName.equals("")){
            Toast.makeText(this, "Write down product name", Toast.LENGTH_SHORT).show();
        }
        else if(pDescription.equals("")){
            Toast.makeText(this, "Write down product description", Toast.LENGTH_SHORT).show();
        }

        else if(pPrice.equals("")){
            Toast.makeText(this, "Write down product price", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String,Object> productMap = new HashMap<>();
            productMap.put("pid",productID);
            productMap.put("description",pDescription);
            productMap.put("price",pPrice);
            productMap.put("pname",pName);

            productRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if ((task.isSuccessful())){
                        Toast.makeText(AdminManageProductsActivity.this, "changes applied successsfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AdminManageProductsActivity.this,AdminCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

    }

    private void displaySpecificProductInfo() {

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String pName = dataSnapshot.child("pname").getValue().toString();
                    String pPrice = dataSnapshot.child("price").getValue().toString();
                    String pDescription = dataSnapshot.child("description").getValue().toString();
                    String pImage = dataSnapshot.child("image").getValue().toString();

                    p_namUpdate.setText(pName);
                    p_descriptionUpdate.setText(pDescription);
                    p_priceUpdate.setText(pPrice);
                    Picasso.get().load(pImage).into(updateImage);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
