package com.tranduythanh.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tranduythanh.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edtProductID, edtProductName, edtProductQuantity,
            edtProductPrice, edtProductCateId, edtProductImageID, edtProductDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        edtProductID = findViewById(R.id.edtProductID);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductQuantity = findViewById(R.id.edtProductQuantity);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductCateId = findViewById(R.id.edtProductCateId);
        edtProductImageID = findViewById(R.id.edtProductImageID);
        edtProductDescription = findViewById(R.id.edtProductDescription);
        displayProductInfo();
    }
    private void displayProductInfo() {
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("SELECTED_PRODUCT");
        if (p == null)
            return;
        edtProductID.setText(String.valueOf(p.getId()));
        edtProductName.setText(String.valueOf(p.getName()));
        edtProductQuantity.setText(String.valueOf(p.getQuantity()));
        edtProductPrice.setText(String.valueOf(p.getPrice()));
        edtProductCateId.setText(String.valueOf(p.getCate_id()));
        edtProductImageID.setText(String.valueOf(p.getImage_id()));
        edtProductDescription.setText(String.valueOf(p.getDescription()));
}}