package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.*;
import android.widget.*;
import com.nguyenthuychi.nguyenthuychi_k22411c_k224111445.R;
import model.CustomerCall;
import java.util.List;

public class CustomerCallAdapter extends BaseAdapter {
    private Context context;
    private List<CustomerCall> list;
    private SQLiteDatabase db;
    private Runnable onTaskCompleted;

    public CustomerCallAdapter(Context ctx, List<CustomerCall> list,
                               SQLiteDatabase db, Runnable onTaskCompleted) {
        this.context = ctx;
        this.list    = list;
        this.db      = db;
        this.onTaskCompleted = onTaskCompleted;
    }

    @Override public int getCount() { return list.size(); }
    @Override public Object getItem(int pos) { return list.get(pos); }
    @Override public long getItemId(int pos) { return list.get(pos).detailId; }

    @Override
    public View getView(int pos, View conv, ViewGroup parent) {
        View row = conv != null
                ? conv
                : LayoutInflater.from(context)
                .inflate(R.layout.customer_call_item, parent, false);

        TextView tvName  = row.findViewById(R.id.tvName);
        TextView tvPhone = row.findViewById(R.id.tvPhone);
        LinearLayout layout = row.findViewById(R.id.layoutItem);

        CustomerCall c = list.get(pos);
        tvName.setText(c.name);
        tvPhone.setText(c.phone);

        if (c.isCalled == 0) layout.setBackgroundColor(0xFFFFEE58);
        else                layout.setBackgroundColor(0xFFFFFFFF);

        row.setOnClickListener(v -> {
            if (c.isCalled == 0) {
                new AlertDialog.Builder(context)
                        .setTitle("Call " + c.name)
                        .setMessage("Dial " + c.phone + "?")
                        .setPositiveButton("Yes", (d,w) -> {
                            db.execSQL(
                                    "UPDATE TaskForTeleSalesDetail_sample SET IsCalled=1 WHERE ID=?",
                                    new Object[]{ c.detailId }
                            );
                            c.isCalled = 1;
                            notifyDataSetChanged();
                            onTaskCompleted.run();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return row;
    }
}
