package com.example.todotask;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BinFragment extends Fragment implements TagIconListener {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<Task> filteredTaskList = new ArrayList<>();
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<TagsModel> tagsModels = new ArrayList<>();
    private CreateDatabase data;
    private int SelectedItemId;
    private Button DeleteAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bin, container, false);

        registerForContextMenu(v);
        recyclerView = v.findViewById(R.id.recyclerView_find);
        data = new CreateDatabase(getContext());
        searchView = v.findViewById(R.id.searchView_find);
        filteredTaskList = new ArrayList<>();

        //button
        DeleteAll = v.findViewById(R.id.Delete_all);

        // Khởi tạo TaskAdapter và liên kết với RecyclerView
        taskAdapter = new TaskAdapter(getContext(), filteredTaskList, tagsModels, this, data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(taskAdapter);

        filteredTaskList.addAll(data.getAllTaskinBin());
        taskList.addAll(data.getAllTaskinBin());
        taskAdapter.notifyDataSetChanged();

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTasks(newText);
                return true;
            }
        });


        //for bin

        TaskAdapter taskAdapter = new TaskAdapter(data); // Initialize your adapter
        taskAdapter.setOnItemLongClickListener(new TaskAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                SelectedItemId = position;
                Toast.makeText(getContext(), "id: " + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        DeleteAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                data.deleteAllTask(0);

                taskList.removeAll(data.getAllTaskinBin());
                recyclerView.removeAllViews();

            }
        });


        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.restore_task, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Task task = taskList.get(SelectedItemId);
        if(item.getItemId() == R.id.restore){
            data.Putback(task.getIdTask());

        }else if(item.getItemId() == R.id.delete)
            data.deleteTask(task.getIdTask());

        return super.onContextItemSelected(item);
    }

    private void filterTasks(String text) {
        filteredTaskList.clear();
        if (text.isEmpty()) {
            filteredTaskList.addAll(taskList); // Hiển thị tất cả các nhiệm vụ nếu không có nội dung tìm kiếm
        } else {
            text = text.toLowerCase();
            for (Task task : taskList) {
                if (task.getNameTask().toLowerCase().contains(text)) {
                    filteredTaskList.add(task); // Thêm nhiệm vụ vào danh sách đã lọc nếu tên nhiệm vụ chứa nội dung tìm kiếm
                }
            }
        }
        taskAdapter.notifyDataSetChanged(); // Cập nhật hiển thị danh sách nhiệm vụ đã lọc
    }
    @Override
    public void onIconClick(Integer item) {

    }

}