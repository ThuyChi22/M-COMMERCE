
package com.tranduythanh.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tranduythanh.models.Customer;

public class CustomerDetailMainActivity extends AppCompatActivity {
    EditText edt_customer_id;
    EditText edit_customer_name;
    EditText edt_customer_email;
    EditText edt_customer_phone;
    EditText edt_customer_username;
    EditText edt_customer_password;

    Button btnNew;
    Button btnSave;
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_detail_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_save_customer();
            }
        });
    }

    private void process_save_customer() {
        // lấy dữ liệu trên giao diện và mô hình hoá vào đối tượng Customer
        Customer c= new Customer();
        c.setId(Integer.parseInt(edt_customer_id.getText().toString()));
        c.setName(edit_customer_name.getText().toString());
        c.setEmail(edt_customer_email.getText().toString());
        c.setPhone(edt_customer_phone.getText().toString());
        c.setUsername(edt_customer_username.getText().toString());
        c.setPassword(edt_customer_password.getText().toString());

        // lấy intent từ màn hnh gọi nó:
        Intent intent= getIntent();
        // đóng gói dữ liệu vào intent:
        intent.putExtra("NEW_CUSTOMER", c);
        // óng dấu là gởi gói hàng này đi
        setResult(500, intent);
        // đóng màn hình này lại, để màn hình gọi nó nhận được kết quả
        finish();
    }

    private void addViews(){
        edt_customer_id=findViewById(R.id.edt_customer_id);
        edit_customer_name=findViewById(R.id.edit_customer_name);
        edt_customer_email=findViewById(R.id.edt_customer_email);
        edt_customer_phone=findViewById(R.id.edt_customer_phone);
        edt_customer_username=findViewById(R.id.edt_customer_username);
        edt_customer_password=findViewById(R.id.edt_customer_password);
        display_infor();
        btnNew=findViewById(R.id.btn_new);
        btnSave=findViewById(R.id.btn_save);
        btnRemove=findViewById(R.id.btn_remove);
    }
    private void display_infor(){
        // Lấy intent từ bên customermanagementactivity gửi qua:
        Intent intent= getIntent();
        // lấy dữ liệu selected customer từ intent:
    Customer c=(Customer) intent.getSerializableExtra("SELECTED_CUSTOMER");
    if(c==null)
        return;
    // hiển thị thông tin customer lên giao diện
    edt_customer_id.setText(c.getId()+""); // tất cả kiểu dữ liệu là số, đặc biệt là số nguyên --> Gắn vào text --> Sẽ hiểu thanhf string trong xml, bị SAI,nên phải + cái chuỗi
        edit_customer_name.setText(c.getName());
        edt_customer_email.setText(c.getEmail());
        edt_customer_phone.setText(c.getPhone());
        edt_customer_username.setText(c.getUsername());
        edt_customer_password.setText(c.getPassword());


    }
}