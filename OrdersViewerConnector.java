package com.tranduythanh.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tranduythanh.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer>getAllOrdersViewer(SQLiteDatabase database)
    {
        ArrayList<OrdersViewer> datasets=new ArrayList<>();

        StringBuilder builder=new StringBuilder();

        builder.append(" SELECT ");
        builder.append(" o.ID AS OrderId, ");
        builder.append(" o.Code AS OrderCode, ");
        builder.append(" o.OrderDated, ");
        builder.append(" e.Name AS EmployeeName, ");
        builder.append(" c.Name AS CustomerName, ");
        builder.append(" SUM((od.Quantity * od.Price - od.Discount / 100.0 * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalOrderValue ");
        builder.append(" FROM Orders o ");
        builder.append(" JOIN Employee e ON o.EmployeeID = e.ID ");
        builder.append(" JOIN Customer c ON o.CustomerID = c.ID ");
        builder.append(" JOIN OrderDetails od ON o.ID = od.OrderID ");
        builder.append(" GROUP BY o.ID, o.Code, o.OrderDated, e.Name, c.Name; ");

        String sql=builder.toString();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int ID = cursor.getInt(0);
            String Code = cursor.getString(1);
            String OrderDate = cursor.getString(2);
            String EmployeeName = cursor.getString(3);
            String CustomerName = cursor.getString(4);
            double TotalOrderValue = cursor.getDouble(5);
            OrdersViewer ov = new OrdersViewer();
            ov.setId(ID);
            ov.setCode(Code);
            ov.setOrderDate(OrderDate);
            ov.setEmployeeName(EmployeeName);
            ov.setCustomerName(CustomerName);
            ov.setTotalOrderValue(TotalOrderValue);
            datasets.add(ov);
            //To do something ….
        }
        cursor.close();
        return datasets;

    }
    public ArrayList<OrdersDetail> getOrderDetailsByOrderID(SQLiteDatabase db, int orderId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT od.ProductName, od.Quantity, od.UnitPrice, od.Quantity * od.UnitPrice AS Total " +
                "FROM OrderDetails od WHERE od.OrderID = ?", new String[]{String.valueOf(orderId)});
        while(cursor.moveToNext()){
            OrderDetail od = new OrderDetail();
            od.setProductName(cursor.getString(0));
            od.setQuantity(cursor.getInt(1));
            od.setUnitPrice(cursor.getDouble(2));
            od.setTotalPrice(cursor.getDouble(3));
            list.add(od);
        }
        cursor.close();
        return list;
    }
}
