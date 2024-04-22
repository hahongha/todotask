package com.example.todotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private ArrayList<TagsModel> tagsModelArrayList;
    private Context context;

    private TagIconListener tagIconListener;

    private CreateDatabase database;

    public TagAdapter(Context context1,ArrayList<TagsModel> tagsModelArrayList, TagIconListener tagitemListener, CreateDatabase Database) {
        this.tagsModelArrayList = tagsModelArrayList;
        this.context = context1;
        this.tagIconListener= tagitemListener;
        this.database = Database;
    }
    public TagAdapter(Context context1,ArrayList<TagsModel> tagsModelArrayList, TagIconListener tagitemListener) {
        this.tagsModelArrayList = tagsModelArrayList;
        this.context = context1;
        this.tagIconListener = tagitemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tag_cardview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTagItem.setText(tagsModelArrayList.get(position).getTagContent());
        holder.imgTagItem.setImageResource(tagsModelArrayList.get(position).getLogoContent());
        //int color = ContextCompat.getColor(context,tagsModelArrayList.get(position).getColorContent());
        holder.cardView.setBackgroundColor(tagsModelArrayList.get(position).getColorContent());
    }

    @Override
    public int getItemCount() {
        return tagsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTagItem;
        private ImageView imgTagItem;

        private LinearLayout cardView;

        private FloatingActionButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTagItem = itemView.findViewById(R.id.txt_tag_item);
            imgTagItem = itemView.findViewById(R.id.img_tag_item);
            cardView= itemView.findViewById(R.id.line1);
            btnDelete= itemView.findViewById(R.id.btnDeleteTag);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        TagsModel tagsModel = tagsModelArrayList.get(position);
                        tagsModelArrayList.remove(position);
                        database.deleteTag(tagsModel.getIdTag());
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, getItemCount());

                    }
                }
            });

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
