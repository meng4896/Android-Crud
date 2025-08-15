package com.example.mobileappication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileappication.R;
import com.example.mobileappication.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private final Context context;
    private final List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card_item_layout,null,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (!product.getName().isEmpty()){
            holder.productName.setText(product.getName());
        }
        holder.productPrice.setText(""+product.getPrice()+ "$");
        if (!product.getImageUrl().isEmpty()){
            Glide.with(context).load(product.getImageUrl()).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    //Create View holder
    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView productName, productPrice;
        ImageView productImage;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.ivProductImageUrl);
            productName = itemView.findViewById(R.id.tvProductName);
            productPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}
