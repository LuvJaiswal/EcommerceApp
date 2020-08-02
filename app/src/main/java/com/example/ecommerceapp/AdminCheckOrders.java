package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecommerceapp.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCheckOrders extends AppCompatActivity {
    private RecyclerView order_list;
    private DatabaseReference ordersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_orders);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Take Orders");

        order_list = findViewById(R.id.order_list);
        order_list.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options =
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                        .setQuery(ordersRef, AdminOrders.class)
                        .build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final AdminOrders model) {

                        holder.userName.setText("Name: " + model.getName());
                        holder.useraddress.setText("Address: " + model.getAddress());
                        holder.userDateTime.setText("Date: " + model.getDate() + " " + model.getTime());
                        holder.userPhoneNo.setText("Phone no: " + model.getPhone());
                        holder.userTotalPrice.setText("Total amount: Rs" + model.getTotalAmount());

                        holder.ordersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String uID = getRef(position).getKey();
                                Intent intent = new Intent(AdminCheckOrders.this, ShowProducts.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);

                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };

                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminCheckOrders.this);
                                builder.setTitle("Did You ship the products to the customer ?");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (which == 0) {
                                            String uID = getRef(position).getKey();

                                            RemoveOrder(uID);

                                        } else {
                                            finish();
                                        }

                                    }
                                });

                                builder.show();

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_order_layout, parent, false);
                        return new AdminOrdersViewHolder(view);
                    }
                };
        order_list.setAdapter(adapter);
        adapter.startListening();

    }

    private void RemoveOrder(String uID) {
        ordersRef.child(uID).removeValue();

    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {

        public TextView userName, userPhoneNo, useraddress, userTotalPrice, userDateTime;
        public Button ordersBtn;


        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);


            userName = itemView.findViewById(R.id.order_username);
            userPhoneNo = itemView.findViewById(R.id.order_phone);
            useraddress = itemView.findViewById(R.id.order_address);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);
            userDateTime = itemView.findViewById(R.id.order_datetime);

            ordersBtn = itemView.findViewById(R.id.showOrders_btn);


        }
    }

}
