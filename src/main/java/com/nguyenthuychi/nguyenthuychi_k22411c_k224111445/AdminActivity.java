package com.nguyenthuychi.nguyenthuychi_k22411c_k224111445;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import adapter.TaskAdapter;
import connector.DatabaseHelper;
import model.Task;

public class AdminActivity extends AppCompatActivity {

    Button btnCreateTask;
    ListView listTasks;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<Task> taskList = new ArrayList<>();
    TaskAdapter adapter;

    int adminId;
    String adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCreateTask = findViewById(R.id.btnCreateTask);
        listTasks = findViewById(R.id.listTasks);

        adminId = getIntent().getIntExtra("account_id", -1);
        adminName = getIntent().getStringExtra("username");

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.openDatabase();

        loadTasks();

        btnCreateTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateTaskActivity.class); // bạn có thể tạo màn hình này sau
            startActivity(intent);
        });
    }

    private void loadTasks() {
        taskList.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM TaskForTeleSales_sample", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int accId = cursor.getInt(1);
            String title = cursor.getString(2);
            String date = cursor.getString(3);
            boolean completed = cursor.getInt(4) == 1;
            taskList.add(new Task(id, accId, title, date, completed));
        }
        cursor.close();

        adapter = new TaskAdapter(this, taskList);
        listTasks.setAdapter(adapter);
    }
}