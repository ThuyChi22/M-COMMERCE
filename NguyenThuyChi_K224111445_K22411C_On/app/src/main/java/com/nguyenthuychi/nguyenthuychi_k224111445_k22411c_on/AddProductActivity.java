package com.nguyenthuychi.nguyenthuychi_k224111445_k22411c_on;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Product;

// Activity Add Product (Screen C)
public class AddProductActivity extends AppCompatActivity {

    EditText edtProductCode, edtProductName, edtUnitPrice;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        edtProductCode = findViewById(R.id.edtProductCode);
        edtProductName = findViewById(R.id.edtProductName);
        edtUnitPrice = findViewById(R.id.edtUnitPrice);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String productCode = edtProductCode.getText().toString();
            String productName = edtProductName.getText().toString();
            double unitPrice = Double.parseDouble(edtUnitPrice.getText().toString());

            Product newProduct = new Product(0, productCode, productName, unitPrice, "");
            // Thêm sản phẩm vào bộ nhớ (dữ liệu giả)
            productList.add(newProduct);
            finish();
        });
    }
}
