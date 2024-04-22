package com.example.todotask;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TagAddIconAdapter extends RecyclerView.Adapter<TagAddIconAdapter.ViewHolder> {
    private ArrayList<Integer> imageList;
    private TagIconListener iconListener;

    public TagAddIconAdapter(ArrayList<Integer> tagsModelArrayList, TagIconListener listener) {
        this.imageList = tagsModelArrayList;
        this.iconListener= listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tag_icon,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imgIconItem.setImageResource(imageList.get(position));

        holder.imgIconItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconListener.onIconClick(imageList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIconItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIconItem = itemView.findViewById(R.id.img_tag_icon_item);
        }
    }
}
