package com.nguyenthuychi.nguyenthuychi_k224111445_k22411c_on;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Activity Student Info (Screen D)
public class StudentInfoActivity extends AppCompatActivity {

    TextView txtStudentID, txtStudentName, txtEmail, txtClassName;
    ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        txtStudentID = findViewById(R.id.txtStudentID);
        txtStudentName = findViewById(R.id.txtStudentName);
        txtEmail = findViewById(R.id.txtEmail);
        txtClassName = findViewById(R.id.txtClassName);
        imgAvatar = findViewById(R.id.imgAvatar);

        txtStudentID.setText("12345");
        txtStudentName.setText("Nguyen Thi A");
        txtEmail.setText("nguyentha@gmail.com");
        txtClassName.setText("Class M02");

        // Set Avatar (image từ local hoặc internet)
        imgAvatar.setImageResource(R.drawable.avatar);
    }
}
