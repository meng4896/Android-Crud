package com.example.mobileappication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappication.adapter.ProductAdapter;
import com.example.mobileappication.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView rvProduct;
    private ProductAdapter productAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvProduct = findViewById(R.id.rvProduct);
        //Setup Layout
        layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setItemAnimator(new DefaultItemAnimator());
        // Data list product
        List<Product> productList = new ArrayList<>();
        productList.add(
                new Product(
                        1,
                        "Coca Cola",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Coca_Cola_Flasche_-_Original_Taste.jpg/500px-Coca_Cola_Flasche_-_Original_Taste.jpg",
                        30.00)

        );
        productList.add(
                new Product(
                        1,
                        "Fanta",
                        "https://www.coca-cola.com/content/dam/onexp/kh/en/brands/fanta/kh-en-fanta-orange.png/width3840.png",
                        20.00)

        );
        // new adapter
        productAdapter = new ProductAdapter(this,productList);
        // set adapter
        rvProduct.setAdapter(productAdapter);
    }
}