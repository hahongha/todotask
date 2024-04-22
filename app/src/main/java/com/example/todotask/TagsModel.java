package com.example.todotask;

public class TagsModel {
    private int idTag;
    private String tagContent;
    private int logoContent;

    private int colorContent;

    public TagsModel(int id,String content, int logo){
        this.idTag = id;
        this.tagContent= content;
        this.logoContent= logo;
        this.colorContent =-657927;
    }

    public TagsModel(int id,String content, int logo, int color){
        this.idTag = id;
        this.tagContent= content;
        this.logoContent= logo;
        this.colorContent = color;
    }
    public TagsModel(){
        this.idTag = 0;
        this.tagContent = "Nothing";
        this.logoContent = R.drawable.ic_add_tick;
        this.colorContent = -13681225;
    }

    public int getLogoContent() {
        return logoContent;
    }

    public void setLogoContent(int logoContent) {
        this.logoContent = logoContent;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public int getColorContent() {
        return colorContent;
    }

    public void setColorContent(int colorContent) {
        this.colorContent = colorContent;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int IdTag) {
        this.idTag = IdTag;
    }
}
