package com.nguyenthuychi.nguyenthuychi_k224111445_k22411c_on;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import models.Product;

public class ProductListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Product> productList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);
        listView = findViewById(R.id.listView);
        productList = new ArrayList<>();

        // Giả lập dữ liệu sản phẩm
        productList.add(new Product(1, "P001", "Sản phẩm A", 100.0, "https://linktoimage.com/product1.jpg"));
        productList.add(new Product(2, "P002", "Sản phẩm B", 150.0, "https://linktoimage.com/product2.jpg"));

        productAdapter = new ProductAdapter(this, R.layout.activity_product_list, productList);
        listView.setAdapter(productAdapter);
    }

    // Custom Adapter để hiển thị sản phẩm
    public class ProductAdapter extends ArrayAdapter<Product> {
        public ProductAdapter(Context context, int resource, ArrayList<Product> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.activity_product_list, parent, false);
            }

            Product product = getItem(position);

            TextView txtProductName = convertView.findViewById(R.id.txtProductName);
            TextView txtProductPrice = convertView.findViewById(R.id.txtProductPrice);
            ImageView imgProduct = convertView.findViewById(R.id.imgProduct);

            txtProductName.setText(product.ProductName);
            txtProductPrice.setText(String.valueOf(product.UnitPrice));

            // Tải hình ảnh sử dụng Multi-Threading (AsyncTask hoặc Thread)
            new DownloadImageTask(imgProduct).execute(product.ImageLink);

            return convertView;
        }
    }

    // AsyncTask để tải hình ảnh từ internet
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}