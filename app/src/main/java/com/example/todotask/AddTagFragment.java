package com.example.todotask;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AddTagFragment extends Fragment implements TagIconListener{

    private EditText edTagId;
    private EditText edTagName;
    private ImageView btnColor;

    private ImageView imgIcon;

    private FloatingActionButton btnAddTag;

    private FloatingActionButton btnCancelTag;

    private int defaultcolor;
    private RecyclerView recyclerIconView;

    private ArrayList<Integer> imageList;
    private  TagAddIconAdapter tagAddIconAdapter;

    private TextView txtTitle;


    private CreateDatabase data= new CreateDatabase(getContext());

    public AddTagFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static AddTagFragment newInstance(String param1, String param2) {
        AddTagFragment fragment = new AddTagFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tagAddView= inflater.inflate(R.layout.fragment_add_tag, container, false);

        txtTitle= tagAddView.findViewById(R.id.textViewNewTag);
        edTagName = tagAddView.findViewById(R.id.add_edt_name);
        btnColor = tagAddView.findViewById(R.id.imgPickColor);
        imgIcon= tagAddView.findViewById(R.id.imgPickIcon);
        btnAddTag= tagAddView.findViewById(R.id.tag_fab_tick);
        btnCancelTag= tagAddView.findViewById(R.id.tag_fab_close);
        recyclerIconView = tagAddView.findViewById(R.id.rc_list_tag_icon);
        defaultcolor= ContextCompat.getColor(getContext(), R.color.lavender);
        data = new CreateDatabase(getContext());

        imageList = new ArrayList<>();
        imageList.add(R.drawable.ic_tag_home);
        imageList.add(R.drawable.ic_tag_break);
        imageList.add(R.drawable.ic_tag_reading);
        imageList.add(R.drawable.ic_tag_school);
        imageList.add(R.drawable.ic_tag_housework);
        imageList.add(R.drawable.ic_tag_eating);
        imageList.add(R.drawable.ic_tag_sleeping);
        imageList.add(R.drawable.ic_tag_movie);
        imageList.add(R.drawable.ic_tag_sport);
        tagAddIconAdapter= new TagAddIconAdapter(imageList, this);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        recyclerIconView.setLayoutManager(layoutManager);

        recyclerIconView.setAdapter(tagAddIconAdapter);



        edTagName.setTag(R.id.id_resource_tag, 0);

        receiveData();

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openColorPicker();
            }
        });

        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int id =(int) edTagName.getTag(R.id.id_resource_tag);
                String name = edTagName.getText().toString();
                int color = defaultcolor;
                int img =(int) imgIcon.getTag(R.id.image_resource_tag);
                bundle.putInt("TagId",id);
                bundle.putString("TagName", name);
                bundle.putInt("TagIcon",img);
                bundle.putInt("TagColor", color);

                TagFragment tagFragment = new TagFragment();
                tagFragment.setArguments(bundle);


                getFragmentManager().beginTransaction().replace(R.id.frame_layout, tagFragment).commit();
            }
        });

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerIconView.setVisibility(View.VISIBLE);
            }
        });

        btnCancelTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagFragment tagFragment = new TagFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, tagFragment);

                transaction.addToBackStack(null);
                // Optional: Add the transaction to the back stack
                transaction.commit();
            }
        });

        return tagAddView;
    }
    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor= color;
                btnColor.setColorFilter(defaultcolor);
                imgIcon.setColorFilter(defaultcolor);
            }
        });
        ambilWarnaDialog.show();
    }


    @Override
    public void onIconClick(Integer item) {
        imgIcon.setImageResource(item);
        imgIcon.setTag(R.id.image_resource_tag, item);
        recyclerIconView.setVisibility(View.GONE);
    }
    private void receiveData()
    {
        Bundle result = getArguments();
        if(result!=null) {
            int id = result.getInt("TagIdEdit");
            String name = result.getString("TagNameEdit");
            int img = result.getInt("TagIconEdit");
            int color = result.getInt("TagColorEdit");
            edTagName.setText(name);
            edTagName.setTag(R.id.id_resource_tag, id);
            imgIcon.setImageResource(img);
            imgIcon.setTag(R.id.image_resource_tag, img);
            btnColor.setColorFilter(color);
            imgIcon.setColorFilter(color);
            defaultcolor= color;
        }
    }
}