package com.example.todotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private  ArrayList<TagsModel> tagsModelArrayList;

    private ArrayList<Task> TaskData;
    private TagIconListener tagIconListener;
    private Context context;
    private CreateDatabase database;

    //for bin
    private OnItemLongClickListener onItemLongClickListener;


    public TaskAdapter(CreateDatabase data){
    }
    public TaskAdapter(Context context, ArrayList<Task> TaskData, ArrayList<TagsModel> tagsModelArrayList,
                       TagIconListener tagIconListener, CreateDatabase database){
        this.tagsModelArrayList = tagsModelArrayList;
        this.TaskData = TaskData;
        this.context = context;
        this.tagIconListener= tagIconListener;
        this.database = database;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent,false);
        TaskViewHolder viewHolder = new TaskViewHolder(view);
        return viewHolder;
    }

    //for bin
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }



    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.TaskName.setText(TaskData.get(position).getNameTask());
        holder.TaskStart.setText(TaskData.get(position).getTimeStart());
        holder.TaskEnd.setText(TaskData.get(position).getTimeEnd());
        holder.DateTask.setText(TaskData.get(position).getDate());

        int TagID = TaskData. get(position).getTag();
        TagsModel tagsModel = database.getIdTag(TagID);

        holder.btnTagTask.setIconResource(tagsModel.getLogoContent());
        holder.btnTagTask.setBackgroundColor(tagsModel.getColorContent());
        holder.btnTagTask.setText(tagsModel.getTagContent());
        holder.btnTagTask.setTag(R.id.material_btn_tag,tagsModel.getIdTag());

        //for bin
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return TaskData.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView TaskName;
        public TextView TaskStart;
        public TextView TaskEnd;
        private TextView DateTask;
        private MaterialButton btnTagTask;


        public TaskViewHolder(@NonNull View view) {
            super(view);
            TaskName = view.findViewById(R.id.lst_tv_nameTask);
            TaskStart = view.findViewById(R.id.lst_tv_timeStart);
            TaskEnd = view.findViewById(R.id.lst_tv_timeEnd);
            DateTask = view.findViewById(R.id.lst_tv_dateTask);
            btnTagTask = view.findViewById(R.id.material_btn_tag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        tagIconListener.onIconClick(position);
                    }else Toast.makeText(context, "not click", Toast.LENGTH_SHORT).show();
                }

            });

        }


    }
}
