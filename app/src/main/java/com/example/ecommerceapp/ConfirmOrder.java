package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmOrder extends AppCompatActivity {

    private EditText ship_name,ship_phone,ship_address;
    private Button ship_confirm_btn;

    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total amount estimated is : " +totalAmount, Toast.LENGTH_SHORT).show();

        ship_name = (EditText) findViewById(R.id.shipping_name);
        ship_phone = (EditText) findViewById(R.id.shipping_phone);
        ship_address = (EditText) findViewById(R.id.shipping_address);

        ship_confirm_btn = (Button) findViewById(R.id.shipping_confirm_btn);

        ship_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoCheck();
            }
        });
    }

    private void DoCheck() {
        if(TextUtils.isEmpty(ship_name.getText().toString())){
            Toast.makeText(this, "provide your name", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(ship_phone.getText().toString())){
            Toast.makeText(this, "provide your phone no.", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(ship_address.getText().toString())){
            Toast.makeText(this, "provide your address", Toast.LENGTH_SHORT).show();
        }
        else {
            ConfirmOrder();
        }

    }

    private void ConfirmOrder() {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference myOrderRef = FirebaseDatabase.getInstance().getReference()
                .child("Take Orders")
                .child(Prevalent.currentOnlineUser.getPhone());



        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount", totalAmount);
        orderMap.put("name", ship_name.getText().toString());
        orderMap.put("phone", ship_phone.getText().toString());
        orderMap.put("address", ship_address.getText().toString());
        orderMap.put("date", saveCurrentDate);
        orderMap.put("time", saveCurrentTime);
        orderMap.put("state", "not shipped");

        myOrderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                FirebaseDatabase.getInstance().getReference()
                        .child("Cart List")
                        .child("User View")
                        .child(Prevalent.currentOnlineUser.getPhone())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(ConfirmOrder.this, "Order given successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ConfirmOrder.this,HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });



            }
        });

    }
}
