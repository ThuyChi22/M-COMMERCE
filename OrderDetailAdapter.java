package com.tranduythanh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tranduythanh.k22411csampleproject.R;
import com.tranduythanh.models.OrderDetails;

public class OrderDetailsAdapter extends ArrayAdapter<OrderDetails> {
    public OrderDetailsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_order_detail, parent, false);
        }

        OrderDetails detail = getItem(position);

        TextView txtProductName = convertView.findViewById(R.id.txtProductName);
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantity);
        TextView txtUnitPrice = convertView.findViewById(R.id.txtUnitPrice);
        TextView txtTotal = convertView.findViewById(R.id.txtTotal);

        txtProductName.setText("SP: " + detail.getProductName());
        txtQuantity.setText("SL: " + detail.getQuantity());
        txtUnitPrice.setText("Giá: " + detail.getUnitPrice());
        txtTotal.setText("Tổng: " + detail.getTotalPrice());

        return convertView;
    }
}

