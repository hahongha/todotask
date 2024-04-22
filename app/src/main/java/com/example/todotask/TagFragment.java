package com.example.todotask;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class TagFragment extends Fragment implements TagIconListener{

    private RecyclerView recyclerView;
    private ArrayList<TagsModel> tagsModels= new ArrayList<>();
    private CreateDatabase database ;
    private TagAdapter tagAdapter= new TagAdapter(getContext(),tagsModels,this, database);

    private FloatingActionButton btnAddTag;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tag, container ,false);
        database = new CreateDatabase(getContext()); // khai bao db
        getData();
        recyclerView = v.findViewById(R.id.recyclerView_Tag);
        btnAddTag= v.findViewById(R.id.tag_fab_add);


        tagAdapter = new TagAdapter(getContext(),tagsModels,this,database);
        receiveDataTag();

        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTagFragment tagAddFragment = new AddTagFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, tagAddFragment);

                transaction.addToBackStack(null);
                // Optional: Add the transaction to the back stack
                transaction.commit();
            }
        });

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(tagAdapter);



        return v;
    }

    private void getData() {
        tagsModels = database.getAllTag();
    }
    private void receiveDataTag() {
        Bundle bundleTag = getArguments();
        if (bundleTag != null) {
            int id = bundleTag.getInt("TagId");
            String name = bundleTag.getString("TagName");
            int img = bundleTag.getInt("TagIcon");
            int color = bundleTag.getInt("TagColor");

            TagsModel newTag = new TagsModel(id,name,img,color);

            Toast.makeText(getContext(), "id:"+id, Toast.LENGTH_SHORT).show();


            if(id!= 0){
                database.UpdateTag(id, name, img, color);
                int position = FindPosition(id);
                tagsModels.set(position, newTag);
                tagAdapter.notifyDataSetChanged();
            }else {
                tagsModels.add(newTag);
                database.addTag(name, img, color);
                tagAdapter.notifyDataSetChanged();
            }
        }
    }
    private int FindPosition(int id ){
        for (int i = 0; i < tagsModels.size(); i++) {
            if(tagsModels.get(i).getIdTag()==id)
                return i;
        }
        return 0;
    }


    @Override
    public void onIconClick(Integer item) {
        Bundle bundleEdit = new Bundle();
        int id = tagsModels.get(item).getIdTag();
        String name = tagsModels.get(item).getTagContent();
        int color = tagsModels.get(item).getColorContent();
        int img =tagsModels.get(item).getLogoContent();


        bundleEdit.putInt("TagIdEdit",id);
        bundleEdit.putString("TagNameEdit", name);
        bundleEdit.putInt("TagIconEdit",img);
        bundleEdit.putInt("TagColorEdit", color);

        AddTagFragment tagAddFragment = new AddTagFragment();
        tagAddFragment.setArguments(bundleEdit);


        getFragmentManager().beginTransaction().replace(R.id.frame_layout, tagAddFragment).commit();
    }
}