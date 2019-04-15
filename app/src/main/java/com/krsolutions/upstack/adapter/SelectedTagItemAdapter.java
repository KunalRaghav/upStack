package com.krsolutions.upstack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.krsolutions.upstack.R;
import com.krsolutions.upstack.viewmodel.TagItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedTagItemAdapter extends RecyclerView.Adapter<SelectedTagItemAdapter.ViewHolder> {

    List<TagItem> selectedTags;

    public SelectedTagItemAdapter(List<TagItem> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public void setSelectedTags(List<TagItem> selectedTags) {
        this.selectedTags = selectedTags;
    }

    @NonNull
    @Override
    public SelectedTagItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_tag_selected,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedTagItemAdapter.ViewHolder holder, int position) {
        holder.button.setText(selectedTags.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return selectedTags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        MaterialButton button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.selectedTag);
        }
    }
}
