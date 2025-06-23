package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nguyenthuychi.nguyenthuychi_k22411c_k224111445.R;

import java.util.List;

import model.Task;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override public int getCount() { return taskList.size(); }
    @Override public Object getItem(int i) { return taskList.get(i); }
    @Override public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.task_item, viewGroup, false);

        TextView tvTitle = view.findViewById(R.id.tvTaskTitle);
        TextView tvInfo = view.findViewById(R.id.tvTaskInfo);

        Task task = taskList.get(i);
        tvTitle.setText(task.title);
        tvInfo.setText("Assigned to AccountID: " + task.accountId + " | Date: " + task.dateAssigned);

        if (task.isCompleted)
            view.setBackgroundColor(0xFFA5D6A7); // Green
        else
            view.setBackgroundColor(0xFFFFFFFF); // White

        return view;
    }
}
