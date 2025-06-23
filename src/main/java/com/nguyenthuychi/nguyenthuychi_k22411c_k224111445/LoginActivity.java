package com.nguyenthuychi.nguyenthuychi_k22411c_k224111445;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import connector.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private RadioGroup radioRole;
    private RadioButton radioAdmin, radioEmployee;
    private Button btnLogin;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername   = findViewById(R.id.etUsername);
        etPassword   = findViewById(R.id.etPassword);
        radioRole    = findViewById(R.id.radioRole);
        radioAdmin   = findViewById(R.id.radioAdmin);
        radioEmployee= findViewById(R.id.radioEmployee);
        btnLogin     = findViewById(R.id.btnLogin);

        dbHelper = new DatabaseHelper(this);
        db       = dbHelper.openDatabase();

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            int selectedId  = radioRole.getCheckedRadioButtonId();
            int role        = selectedId == R.id.radioAdmin ? 1
                    : selectedId == R.id.radioEmployee ? 2
                    : -1;

            if (username.isEmpty() || password.isEmpty() || role == -1) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = db.rawQuery(
                    "SELECT * FROM Account WHERE Username=? AND Password=? AND TypeOfAccount=?",
                    new String[]{username, password, String.valueOf(role)}
            );

            if (cursor.moveToFirst()) {
                int accountId = cursor.getInt(0);
                String uname  = cursor.getString(1);

                Toast.makeText(
                        this,
                        "Login successful as " + (role == 1 ? "Admin" : "Employee"),
                        Toast.LENGTH_SHORT
                ).show();

                Intent i;
                if (role == 1) {
                    i = new Intent(this, AdminActivity.class);
                } else {
                    i = new Intent(this, EmployeeTaskActivity.class);
                }
                i.putExtra("account_id", accountId);
                i.putExtra("username", uname);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(
                        this,
                        "Login failed. Please check your credentials or role.",
                        Toast.LENGTH_SHORT
                ).show();
            }
            cursor.close();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAbout) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
