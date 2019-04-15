package com.krsolutions.upstack.viewmodel;

import java.util.ArrayList;


public class TagItem{
    String name;
    Boolean checkStatus;

    public String getName() {
        return name;
    }

    public Boolean isChecked() {
        return checkStatus;
    }

    public TagItem(String name, Boolean checkStatus) {
        this.name = name;
        this.checkStatus = checkStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(Boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public TagItem() {
    }

    public static ArrayList<TagItem> generateTags(int num){
        ArrayList<TagItem> arrayList = new ArrayList<>();
        for(int i=0;i<num;i++){
            arrayList.add(new TagItem("Lang"+i,i%2==0));
        }
        return arrayList;
    }
}