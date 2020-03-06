package com.example.ecommerceapp.ViewHolder;

import android.content.ClipData;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Interface.ItemClickListener;
import com.example.ecommerceapp.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;

    public ItemClickListener listener;



    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.item_product_image);
        txtProductName =(TextView)itemView.findViewById(R.id.item_product_name);
        txtProductDescription = (TextView)itemView.findViewById(R.id.item_product_description);
        txtProductPrice = (TextView)itemView.findViewById(R.id.item_product_price);


    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);
    }
}
