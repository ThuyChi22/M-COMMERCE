package com.nguyenthuychi.nguyenthuychi_k22411c_k224111445;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import adapter.CustomerCallAdapter;
import connector.DatabaseHelper;
import model.CustomerCall;

public class EmployeeTaskActivity extends AppCompatActivity {

    private ListView lvCustomerCalls;
    private ArrayList<CustomerCall> calls = new ArrayList<>();
    private CustomerCallAdapter adapter;
    private SQLiteDatabase db;
    private int currentTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task);

        lvCustomerCalls = findViewById(R.id.lvCustomerCalls);
        db = new DatabaseHelper(this).openDatabase();

        int accountId = getIntent().getIntExtra("account_id", -1);

        adapter = new CustomerCallAdapter(this, calls, db, this::checkTaskCompleted);
        lvCustomerCalls.setAdapter(adapter);

        loadTodayCalls(accountId);
    }

    private void loadTodayCalls(int accountId) {
        calls.clear();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // 1) Lấy TaskID
        Cursor tc = db.rawQuery(
                "SELECT ID FROM TaskForTeleSales_sample WHERE AccountID=? AND DateAssigned=?",
                new String[]{ String.valueOf(accountId), today });
        if (!tc.moveToFirst()) {
            Toast.makeText(this, "No task assigned today.", Toast.LENGTH_LONG).show();
            tc.close();
            return;
        }
        currentTaskId = tc.getInt(0);
        tc.close();

        // 2) JOIN bảng detail + customer để load Name, Phone, IsCalled
        Cursor c = db.rawQuery(
                "SELECT d.ID, c.Name, c.Phone, d.IsCalled " +
                        "FROM TaskForTeleSalesDetail_sample d " +
                        " JOIN Customer c ON d.CustomerID = c.ID " +
                        "WHERE d.TaskForTeleSalesID=?",
                new String[]{ String.valueOf(currentTaskId) }
        );
        while (c.moveToNext()) {
            int detailId = c.getInt(0);
            String name  = c.getString(1);
            String phone = c.getString(2);
            int isCalled = c.getInt(3);
            calls.add(new CustomerCall(detailId, name, phone, isCalled));
        }
        c.close();

        adapter.notifyDataSetChanged();
    }

    private void checkTaskCompleted() {
        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM TaskForTeleSalesDetail_sample " +
                        "WHERE TaskForTeleSalesID=? AND IsCalled=0",
                new String[]{ String.valueOf(currentTaskId) }
        );
        if (c.moveToFirst() && c.getInt(0) == 0) {
            db.execSQL(
                    "UPDATE TaskForTeleSales_sample SET IsCompleted=1 WHERE ID=?",
                    new Object[]{ currentTaskId }
            );
            Toast.makeText(this, "All calls done. Task completed!", Toast.LENGTH_SHORT).show();
        }
        c.close();
    }
}
