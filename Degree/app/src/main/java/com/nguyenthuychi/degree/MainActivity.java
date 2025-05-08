package com.example.k22411c_firstdegree;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các biến để quản lý ô nhớ của các view
    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtCoefficientA=findViewById(R.id.edtCoefficientA);
        edtCoefficientB=findViewById(R.id.edtCoefficientB);
        txtResults=findViewById(R.id.txtResults);
    }

    public void do_solution(View view) {
        String hsa=edtCoefficientA.getText().toString();
        double a = Double.parseDouble(hsa);
        //Lấy hệ số b trên giao diện
        double b = Double.parseDouble(edtCoefficientB.getText().toString());

        if (a == 0 && b == 0) {
            txtResults.setText(getResources().getText(R.string.title_infinity));
        } else if (a == 0 && b != 0) {
            txtResults.setText(getResources().getText(R.string.title_no_solutions));
        } else {
            double x = - b / a;
            txtResults.setText("x=" + x);
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResults.setText("");
        //di chuyển con trỏ nhập liệu
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}