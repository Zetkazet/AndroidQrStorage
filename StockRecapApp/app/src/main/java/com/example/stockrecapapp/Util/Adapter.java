package com.example.stockrecapapp.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockrecapapp.R;
import com.example.stockrecapapp.model.ProductModel;
import com.google.android.gms.analytics.ecommerce.Product;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductViewHolder> {


    private final Context mCtx;
    private final List<ProductModel> productList;

    public Adapter(Context mCtx, List<ProductModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.stock_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);

        //loading the image

        holder.textId.setText(product.getproductid());
        holder.textDesc.setText(product.getproductname());
        holder.textStock.setText(String.valueOf(product.getproductstock()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textId, textDesc, textStock ;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.txtNo);
            textDesc = itemView.findViewById(R.id.txtDescription);
            textStock = itemView.findViewById(R.id.txtAmount);
        }
    }
}
