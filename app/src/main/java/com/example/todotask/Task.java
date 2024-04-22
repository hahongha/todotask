package com.example.todotask;

public class Task {
    private int IdTask;
    private String NameTask;
    private String Date;
    private String TimeStart;
    private String TimeEnd;
    private String Repeat;
    private int Tag;
    private String Notification;
    private int Checked;

    public Task() {

    }

    public Task(int idTask, String NameTask, String Date, String TimeStart, String TimeEnd, String Repeat, int Tag, String Notification) {
        this.IdTask = idTask;
        this.NameTask = NameTask;
        this.Date = Date;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.Repeat = Repeat;
        this.Tag = Tag;
        this.Notification = Notification;
        this.Checked = 1;
    }

    public Task(int idTask, String NameTask, String Date, String TimeStart, String TimeEnd, String Repeat, int Tag, String Notification, int checked) {
        this.IdTask = idTask;
        this.NameTask = NameTask;
        this.Date = Date;
        this.TimeStart = TimeStart;
        this.TimeEnd = TimeEnd;
        this.Repeat = Repeat;
        this.Tag = Tag;
        this.Notification = Notification;
        this.Checked = checked;
    }

    public int getChecked(){return  Checked;}
    public void setChecked(int checked){ this.Checked = checked;}

    public String getNameTask() {
        return NameTask;
    }

    public int getIdTask() {
        return IdTask;
    }

    public void setIdTask(int idTask) {
        IdTask = idTask;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }

    public void setNameTask(String nameTask) {
        NameTask = nameTask;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String timeStart) {
        TimeStart = timeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        TimeEnd = timeEnd;
    }

    public String getRepeat() {
        return Repeat;
    }

    public void setRepeat(String repeat) {
        Repeat = repeat;
    }

    public int getTag() {
        return Tag;
    }

    public void setTag(int tag) {
        Tag = tag;
    }
}
