package com.example.todotask;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddFragment extends Fragment implements TagIconListener {
    private RecyclerView recyclerTags;
    private ArrayList<TagsModel> tagsTask= new ArrayList<>();
    private TagAdapter tagAdapterTask= new TagAdapter(getContext(),tagsTask,this);
    private EditText edtName;
    private EditText edtDate;

    private EditText edtTimeStart;
    private EditText edtTimeEnd;
    private MaterialButton btnTag;
    private MaterialButton btnSetAlarm;
    private FloatingActionButton fab;

    private CreateDatabase data;

    private int hour , minutes; // This 2 variable for selectedTimeStart
    private AutoCompleteTextView dropDownMenuRepeat;
    //    String[] options = new String[]{"Do not repeat", "Daily","Weekly","Monthly","Annually"};
    ArrayAdapter<String> adapterItems ;

    DatePickerDialog.OnDateSetListener listener;
    private static final String ACTION_SET_ALARM = "com.example.app.SET_ALARM";
    private static final int REQUEST_CODE_ALARM = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add, container, false);

        View v = inflater.inflate(R.layout.fragment_add,container,false);

        edtName = v.findViewById(R.id.add_edt_name);
        edtName.setTag(R.id.id_task,0);


        edtDate = v.findViewById(R.id.add_edt_date);
        edtTimeStart = v.findViewById(R.id.add_edt_start);
        edtTimeEnd = v.findViewById(R.id.add_edt_end);
        fab = v.findViewById(R.id.add_fab);

        data = new CreateDatabase(getContext());

        //tags
        getData();
        recyclerTags = v.findViewById(R.id.rc_list_tag);
        tagAdapterTask = new TagAdapter(getContext(),tagsTask,this);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerTags.setLayoutManager(layoutManager);
        recyclerTags.setAdapter(tagAdapterTask);

        btnTag = v.findViewById(R.id.add_btn_tag);
        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerTags.setVisibility(View.VISIBLE);
            }
        });
        dropDownMenuRepeat = v.findViewById(R.id.add_tv_repeat);
        adapterItems = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line,
                new String[]{"Do not repeat", "Daily","Weekly","Monthly","Annually"});
        dropDownMenuRepeat.setAdapter(adapterItems);

        // set ALARM NOTIFICATION
        btnSetAlarm = v.findViewById(R.id.add_btn_notification);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        final int minute = calendar.get(Calendar.MINUTE);


        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(),android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String datetime = "";
                        if (dayOfMonth <10) datetime += "0" + dayOfMonth + "/" ;
                        else datetime += dayOfMonth + "/";
                        if (month < 10) datetime+= "0"+ month + "/" + year;
                        else datetime+= month + "/" + year;
                        edtDate.setText(datetime);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

//      edtTimeStart.setOnClickListener(v1 -> selectTime());
        edtTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTimeStart();
            }
        });

        edtTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeEnd();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id =(int) edtName.getTag(R.id.id_task);
                    String name = edtName.getText().toString();
                    String date = edtDate.getText().toString();
                    String timeStart = edtTimeStart.getText().toString();
                    String timeEnd = edtTimeEnd.getText().toString();
                    String repeat = dropDownMenuRepeat.getText().toString();
                    int tagID = (int) btnTag.getTag(R.id.material_btn_tag);

                    Bundle b = new Bundle();
                    b.putInt("IdTask", id);
                    b.putString("NameTask",name);
                    b.putString("DateTask",date);
                    b.putString("TimeStart",timeStart);
                    b.putString("TimeEnd",timeEnd);
                    b.putString("Repeat",repeat);
                    b.putInt("Tag",tagID);

                    // dong goi roi gui sang ben CalendarFragment
                    CalendarFragment calendarFragment = new CalendarFragment();
                    calendarFragment.setArguments(b);
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, calendarFragment).commit();
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getContext().getApplicationContext(),"Please input all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //nhan data
        receiveData();




        return v;
    }
    // C1:
    private void selectedTimeStart(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourSelected, int minute) {
                hour = hourSelected;
                minutes = minute;
                edtTimeStart.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minutes));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                android.R.style.Theme_DeviceDefault_Dialog,onTimeSetListener, hour,minutes, true);
        timePickerDialog.setTitle("Set Time");
        timePickerDialog.show();
        setAlarm();
    }
    // C2:
    private void selectTimeEnd(){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minutes = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minutes) {
                currentTime.set(Calendar.HOUR_OF_DAY,hour);
                currentTime.set(Calendar.MINUTE,minutes);

                String timeformat = "HH:mm";
                SimpleDateFormat dateFormat = new SimpleDateFormat(timeformat,Locale.US);
                edtTimeEnd.setText(dateFormat.format(currentTime.getTime()));
            }
        },hour,minutes,true);
        timePickerDialog.setTitle("Set time");
        timePickerDialog.show();
        //setAlarm(currentTime.getTimeInMillis());
    }


    @Override
    public void onIconClick(Integer item) {
        TagsModel newmodels = tagsTask.get(item);
        btnTag.setText(newmodels.getTagContent());
        btnTag.setIconResource(newmodels.getLogoContent());
        btnTag.setBackgroundColor(newmodels.getColorContent());
        btnTag.setTag(R.id.material_btn_tag,newmodels.getIdTag());
        recyclerTags.setVisibility(View.GONE);
    }

    private void getData() {
        tagsTask = new ArrayList<>();
        tagsTask=data.getAllTag();
    }

    private void receiveData(){
        Bundle result = getArguments();
        if(result!=null) {
            int id = result.getInt("TaskIdEdit");
            String name = result.getString("TaskNameEdit");
            String date = result.getString("TaskDateEdit");
            String timeStart = result.getString("TaskTStartEdit");
            String timeEnd = result.getString("TaskTEndEdit");
            String repeat = result.getString("TaskRepeatEdit");
            int idTag = result.getInt("TaskTagEdit");

            edtName.setText(name);
            edtName.setTag(R.id.id_task, id);
            edtDate.setText(date);
            edtTimeStart.setText(timeStart);
            edtTimeEnd.setText(timeEnd);
            dropDownMenuRepeat.setText(repeat);

            TagsModel tag = data.getIdTag(idTag);
            btnTag.setText(tag.getTagContent());
            btnTag.setBackgroundColor(tag.getColorContent());
            btnTag.setIconResource(tag.getLogoContent());
            btnTag.setTag(R.id.material_btn_tag,tag.getIdTag());
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm() {
        // Get the start time in milliseconds
        long startTime = convertTimeToMillis(edtTimeStart.getText().toString());

        // Create the alarm intent
        Intent alarmIntent = new Intent(getContext(), AlarmReceiver.class);
        alarmIntent.setAction(ACTION_SET_ALARM);
        alarmIntent.putExtra("START_TIME", startTime);

        // Create the pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), REQUEST_CODE_ALARM, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);

        // Set the alarm
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
        }

        // Show a toast message or perform any other action to indicate that the alarm has been set
        Toast.makeText(getContext(), "Alarm set for the start time", Toast.LENGTH_SHORT).show();
    }
    private long convertTimeToMillis(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            Date date = sdf.parse(time);
            if (date != null) {
                return date.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}