package com.example.todotask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment implements TagIconListener {

    private RecyclerView listTask;

    private ArrayList<TagsModel> tagsModels= new ArrayList<>();

    private ArrayList<Task> taskArrayList = new ArrayList<>();

    private TaskAdapter taskAdapter;

    private CreateDatabase data;
    private CalendarView calendarView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calendar, container, false);

        listTask = v.findViewById(R.id.recyclerView_Task);
        data = new CreateDatabase(getContext());
        //getData();
        calendarView = v.findViewById(R.id.calendarView);
        taskAdapter = new TaskAdapter(getContext(), taskArrayList, tagsModels, this, data);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());

        listTask.setLayoutManager(layoutManager);

        listTask.setAdapter(taskAdapter);
        receiveData();

        //removetask
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                return makeMovementFlags(0,
                        ItemTouchHelper.START);
                //return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
//                draggedItemIndex = viewHolder.getAdapterPosition();
//                int targetIndex = target.getAdapterPosition();
//
//                Collections.swap(taskArrayList, draggedItemIndex, targetIndex);
//
//                taskAdapter.notifyItemMoved(draggedItemIndex, targetIndex);


                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
//                    case ItemTouchHelper.END://di sang phai
//                        break;
                    case ItemTouchHelper.START://di sang trai
                        Task currentTask = taskArrayList.get(viewHolder.getAdapterPosition());

                        if(currentTask!= null){
                            Toast.makeText(getContext(), "id:"+currentTask.getIdTask(), Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getContext(), "khongthay", Toast.LENGTH_SHORT).show();

                        data.PutToBin(currentTask.getIdTask());



                        taskArrayList.remove(viewHolder.getAdapterPosition());
                        listTask.removeViewAt(viewHolder.getAdapterPosition());
                        taskAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        //taskAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        helper.attachToRecyclerView(listTask);


//     //   Tu dong hien task cua ngay hom nay
        Calendar calendar = Calendar.getInstance();
        //String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        String currentDate ="";
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10)  currentDate+= "0"+ calendar.get(Calendar.DAY_OF_MONTH);
        else currentDate+=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.MONTH)<9) currentDate+= "/0"+ String.valueOf(calendar.get(Calendar.MONTH)+1);
        else currentDate+="/"+ String.valueOf(calendar.get(Calendar.MONTH)+1);
        currentDate+="/"+ String.valueOf(calendar.get(Calendar.YEAR));

        taskArrayList.addAll(data.getDateTask(currentDate));
        //taskArrayList.addAll(data.getAllTask());
        taskAdapter.notifyDataSetChanged();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String s="";
                if (dayOfMonth < 10)  s+= "0"+ String.valueOf(dayOfMonth);
                else s+=String.valueOf(dayOfMonth);
                if (month < 9 ) s+= "/0"+ String.valueOf(month+1);
                else s+="/"+ String.valueOf(month+1);
                s+="/"+ String.valueOf(year);


                //taskArrayList = data.getDateTask(s);
                ArrayList<Task> tasksForSelectedDate = data.getDateTask(s);

                // Clear existing list and add fetched tasks
                taskArrayList.clear();
                taskArrayList.addAll(tasksForSelectedDate);
                taskArrayList.clear();
                taskArrayList.addAll(tasksForSelectedDate);
                taskAdapter.notifyDataSetChanged();
            }
        });

        return  v;
    }

    private void receiveData() {
        Bundle b = getArguments();
        if (b != null){
            int IdTask = b.getInt("IdTask");
            String NameTask = b.getString("NameTask");
            String DateTask = b.getString("DateTask");
            String TimeStart = b.getString("TimeStart");
            String TimeEnd = b.getString("TimeEnd");
            String Repeat = b.getString("Repeat");
            int TagID = b.getInt("Tag");


            Task newTask = new Task(IdTask,
                    NameTask, DateTask,
                    TimeStart, TimeEnd, Repeat, TagID, "test",1);
            if(IdTask!= 0){
                data.UpdateTask(IdTask, newTask);
            }else {
               // taskArrayList.add(newTask);
                data.addRepeatingTask(NameTask, DateTask,
                        TimeStart, TimeEnd, Repeat, TagID, "test");
              //  taskAdapter.notifyDataSetChanged();
            }
        }
    }



    @Override
    public void onIconClick(Integer item) {

        Bundle bundleEdit = new Bundle();
        Task cTask = taskArrayList.get(item);
        int id =cTask.getIdTask();
        String name = cTask.getNameTask();
        String date = cTask.getDate();
        String timeStart = cTask.getTimeStart();
        String timeEnd = cTask.getTimeEnd();
        String repeat = cTask.getRepeat();
        int idTag = cTask.getTag();
//
//
        bundleEdit.putInt("TaskIdEdit",id);
        bundleEdit.putString("TaskNameEdit", name);
        bundleEdit.putString("TaskDateEdit", date);
        bundleEdit.putString("TaskTStartEdit", timeStart);
        bundleEdit.putString("TaskTEndEdit", timeEnd);
        bundleEdit.putString("TaskRepeatEdit", repeat);
        bundleEdit.putInt("TaskTagEdit", idTag);




//
        AddFragment taskAddFragment = new AddFragment();
        taskAddFragment.setArguments(bundleEdit);
//
//
        getFragmentManager().beginTransaction().replace(R.id.frame_layout, taskAddFragment).commit();
    }



}