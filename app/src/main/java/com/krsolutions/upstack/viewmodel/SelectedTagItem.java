package com.krsolutions.upstack.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class SelectedTagItem {
    List<TagItem> SelectedTags;

    public SelectedTagItem(){};

    public List<TagItem> getSelectedTags(){
        if(SelectedTags!=null){
            return SelectedTags;
        }else {
            SelectedTags = new ArrayList<>();
            return SelectedTags;
        }
    }
}

