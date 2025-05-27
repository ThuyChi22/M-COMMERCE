package com.tranduythanh.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imgEmployee;
    TextView txtEmployee;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgproduct;
    TextView txtproduct;
    ImageView imgadvancedproduct;
    TextView txtadvancedproduct;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });
    }

    private void openProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị khách hàng
                openCustomerManagementActivity();
            }
            });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi code mở màn hình quản trị khách hàng
                openCustomerManagementActivity();
            }
        });
        imgproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });
        txtproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });
        imgadvancedproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        txtadvancedproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity
                        ();
            }
        });
    }

    private void openAdvancedProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, AdvanceProductManagementActivity.class);
        startActivity(intent);
    }


    void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }
    void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }

    private void addViews() {
        imgEmployee=findViewById(R.id.imgProduct);
        txtEmployee=findViewById(R.id.txtEmployee);
        imgCustomer=findViewById(R.id.imgAdvancedProduct);
        txtCustomer=findViewById(R.id.txtCustomer);
        imgproduct=findViewById(R.id.imgproduct);
        txtproduct=findViewById(R.id.txtproduct);
    }


}