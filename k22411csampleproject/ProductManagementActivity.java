package com.tranduythanh.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tranduythanh.models.Category;
import com.tranduythanh.models.ListCategory;
import com.tranduythanh.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ArrayAdapter<Category> adapterCategory;
    ListCategory listCategory;
    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }
    private void addEvents(){
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Category c=adapterCategory.getItem(i);
                displayProductsByCategory(c);
            }

            private void displayProductsByCategory(Category c) {
                // xoá dữ liệu cũ trong lisview đi:
                adapterProduct.clear();
                //nạp mới lại dữ liê cho adapter
                adapterProduct.addAll(c.getProducts());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void addViews(){
        spinnerCategory=findViewById(R.id.spinner);
        adapterCategory=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_product,menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_new_product){
            Toast.makeText(ProductManagementActivity.this,"Mở màn hình thêm mới sản phẩm",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProductManagementActivity.this, CustomerDetailMainActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId()==R.id.menu_help) {
            Toast.makeText(ProductManagementActivity.this,"Mở website hướng dẫn suwr dụng",Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }
}