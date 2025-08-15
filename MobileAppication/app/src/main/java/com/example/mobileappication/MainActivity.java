package com.example.mobileappication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPicasso, imageViewGlide;
    private Button btnListProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageViewGlide = findViewById(R.id.ivImageGlide);
        imageViewPicasso = findViewById(R.id.ivImagePicasso);
        btnListProduct = findViewById(R.id.btnProductActivity);

        Picasso.get()
                .load("https://www.coca-cola.com/content/dam/onexp/kh/en/brands/fanta/kh-en-fanta-orange.png/width3840.png")
                .into(imageViewPicasso);
        Glide.with(this).load("https://www.coca-cola.com/content/dam/onexp/kh/en/brands/fanta/kh-en-fanta-orange.png/width3840.png").into(imageViewGlide);

        btnListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);

            }
        });
    }
}