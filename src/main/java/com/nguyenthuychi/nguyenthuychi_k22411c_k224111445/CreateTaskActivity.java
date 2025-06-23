package com.nguyenthuychi.nguyenthuychi_k22411c_k224111445;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import connector.DatabaseHelper;
import model.CustomerCall;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText etTaskTitle;
    private Spinner spinnerEmployee;
    private Button btnSelectCustomer, btnCreateTask;
    private ListView lvCustomerPhones;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    // Dữ liệu cho spinner
    private final List<Employee> employeeList = new ArrayList<>();
    private final List<String> employeeNames = new ArrayList<>();

    // Dữ liệu gốc khách hàng
    private final List<CustomerCall> allCustomers = new ArrayList<>();

    // Chỉ số và số điện thoại đã chọn
    private final List<Integer> selectedIndices = new ArrayList<>();
    private final List<String> selectedPhones = new ArrayList<>();

    private ArrayAdapter<String> phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        // Ánh xạ view
        etTaskTitle       = findViewById(R.id.etTaskTitle);
        spinnerEmployee   = findViewById(R.id.spinnerEmployee);
        btnSelectCustomer = findViewById(R.id.btnSelectCustomer);
        lvCustomerPhones  = findViewById(R.id.lvCustomerPhones);
        btnCreateTask     = findViewById(R.id.btnCreateTask);

        // Khởi tạo DB
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.openDatabase();

        // Load dữ liệu
        loadEmployees();
        loadAllCustomers();

        // Setup spinner nhân viên
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, employeeNames);
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployee.setAdapter(employeeAdapter);

        // Setup listview số điện thoại đã chọn
        phoneAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, selectedPhones);
        lvCustomerPhones.setAdapter(phoneAdapter);

        // Bấm chọn ngẫu nhiên 5 khách hàng
        btnSelectCustomer.setOnClickListener(v -> selectRandomCustomers());

        // Bấm tạo task
        btnCreateTask.setOnClickListener(v -> createTask());
    }

    // Load danh sách nhân viên từ bảng Account
    private void loadEmployees() {
        employeeList.clear();
        employeeNames.clear();
        Cursor c = db.rawQuery(
                "SELECT ID, Username FROM Account WHERE TypeOfAccount=2", null);
        while (c.moveToNext()) {
            int id   = c.getInt(0);
            String u = c.getString(1);
            employeeList.add(new Employee(id, u));
            employeeNames.add(u);
        }
        c.close();
    }

    // Load toàn bộ khách hàng
    private void loadAllCustomers() {
        allCustomers.clear();
        Cursor c = db.rawQuery(
                "SELECT ID, Name, Phone FROM Customer", null);
        while (c.moveToNext()) {
            int id      = c.getInt(0);
            String name = c.getString(1);
            String phone= c.getString(2);
            allCustomers.add(new CustomerCall(id, name, phone, 0));
        }
        c.close();
    }

    // Chọn ngẫu nhiên 5 khách hàng
    private void selectRandomCustomers() {
        selectedIndices.clear();
        selectedPhones.clear();

        List<Integer> pool = new ArrayList<>();
        for (int i = 0; i < allCustomers.size(); i++) {
            pool.add(i);
        }
        Collections.shuffle(pool);

        int take = Math.min(5, pool.size());
        for (int i = 0; i < take; i++) {
            int idx = pool.get(i);
            selectedIndices.add(idx);
            selectedPhones.add(allCustomers.get(idx).phone);
        }

        phoneAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Selected " + take + " random customers", Toast.LENGTH_SHORT).show();
    }

    // Tạo một task mới và insert vào database
    private void createTask() {
        String title = etTaskTitle.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, "Enter task title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedIndices.isEmpty()) {
            Toast.makeText(this, "Select customers first", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy employeeId từ spinner
        int empPos = spinnerEmployee.getSelectedItemPosition();
        int empId  = employeeList.get(empPos).id;

        // Ngày hiện tại
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // 1) Insert vào TaskForTeleSales
        db.execSQL(
                "INSERT INTO TaskForTeleSales_sample (AccountID, TaskTitle, DateAssigned, IsCompleted) " +
                        "VALUES (?, ?, ?, 0)",
                new Object[]{empId, title, today}
        );

        // 2) Lấy taskId vừa insert
        Cursor c = db.rawQuery("SELECT last_insert_rowid()", null);
        int taskId = c.moveToFirst() ? c.getInt(0) : -1;
        c.close();

        // 3) Insert chi tiết vào TaskForTeleSalesDetails
        for (int idx : selectedIndices) {
            int custId = allCustomers.get(idx).detailId;
            db.execSQL(
                    "INSERT INTO TaskForTeleSalesDetail_sample (TaskForTeleSalesID, CustomerID, IsCalled) " +
                            "VALUES (?, ?, 0)",
                    new Object[]{taskId, custId}
            );
        }

        Toast.makeText(this, "Task created!", Toast.LENGTH_SHORT).show();

        // Trả về AdminActivity để reload
        setResult(RESULT_OK);
        finish();
    }

    // Class nội bộ để map nhân viên
    private static class Employee {
        int id;
        String name;
        Employee(int id, String name) { this.id = id; this.name = name; }
    }
}