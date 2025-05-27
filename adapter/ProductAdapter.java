package com.tranduythanh.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tranduythanh.k22411csampleproject.R;
import com.tranduythanh.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;
    public ProductAdapter(@NonNull Activity context, int resource){
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        // nhan ban giao dien theo từ b trí position mà dữ liệu dc duyệt qua
        View item=inflater.inflate(this.resource, null);
        // lúc này: toàn bộ view ằm trong layout resource (item_advanced_product)
        // sẽ được mô hình hoá hướng đối tợng và được quản lý bởi biêns item
        // tức là item là tổng tài view
        // như vậy muốn truy suất tới các view con trong nó thi phải thông tin qa item
        ImageView imgProduct=item.findViewById(R.id.imgadvancedproduct);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductId=item.findViewById(R.id.txtProductId);
        ImageView imgCart=item.findViewById(R.id.imgcart);

        // lấy mô hình đối tượng đang được nhân bản ở vị trí position (đối số 1)
        Product p = getItem(position);
        // rài dữ liệu của product trên giao diện trong item
        imgProduct.setImageResource(p.getImage_id());

        return item;
    }
}
