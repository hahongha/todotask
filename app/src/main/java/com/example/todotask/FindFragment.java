package com.example.todotask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FindFragment extends Fragment implements TagIconListener {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<Task> filteredTaskList = new ArrayList<>();
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<TagsModel> tagsModels = new ArrayList<>();
    private CreateDatabase data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find, container, false);

        recyclerView = v.findViewById(R.id.recyclerView_find);
        data = new CreateDatabase(getContext());
        searchView = v.findViewById(R.id.searchView_find);
        filteredTaskList = new ArrayList<>();

        // Khởi tạo TaskAdapter và liên kết với RecyclerView
        taskAdapter = new TaskAdapter(getContext(), filteredTaskList, tagsModels, this, data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(taskAdapter);

        filteredTaskList.addAll(data.getAllTask());
        taskList.addAll(data.getAllTask());
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

        return v;
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
                } else if (data.getTaskfromTag(task.getTag()).toLowerCase().contains(text)) {  //tìm kiếm nhiệm vụ theo tag
                    filteredTaskList.add(task);
                }
            }
        }
        taskAdapter.notifyDataSetChanged(); // Cập nhật hiển thị danh sách nhiệm vụ đã lọc
    }

//    private void filterTasks(String text) {
//        filteredTaskList.clear();
//        if (text.isEmpty()) {
//            filteredTaskList.addAll(taskList); // Hiển thị tất cả các nhiệm vụ nếu không có nội dung tìm kiếm
//        } else {
//            text = text.toLowerCase();
//            for (Task task : taskList) {
//                if (task.getNameTask().toLowerCase().contains(text)) {
//                    filteredTaskList.add(task); // Thêm nhiệm vụ vào danh sách đã lọc nếu tên nhiệm vụ chứa nội dung tìm kiếm
//                }
//            }
//        }
//        taskAdapter.notifyDataSetChanged(); // Cập nhật hiển thị danh sách nhiệm vụ đã lọc
//    }
    @Override
    public void onIconClick(Integer item) {

    }
}