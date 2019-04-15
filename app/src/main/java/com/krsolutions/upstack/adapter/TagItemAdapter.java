package com.krsolutions.upstack.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.krsolutions.upstack.R;
import com.krsolutions.upstack.viewmodel.SelectedTagItem;
import com.krsolutions.upstack.viewmodel.TagItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagItemAdapter extends RecyclerView.Adapter<TagItemAdapter.ViewHolder> {

    private List<TagItem> TagItems;
    private Context parentContext;
    private static final String TAG = "TagItemAdapter";
    private SelectedTagItem selectedTagItem;
    private SelectedTagItemAdapter selectedTagItemAdapter;

    public TagItemAdapter(List<TagItem> tagItems, Context parentContext, SelectedTagItem selectedTagItem, SelectedTagItemAdapter selectedTagItemAdapter) {
        TagItems = tagItems;
        this.parentContext = parentContext;
        this.selectedTagItem = selectedTagItem;
        this.selectedTagItemAdapter = selectedTagItemAdapter;
    }

    public TagItemAdapter(List<TagItem> tagItems, Context parentContext, SelectedTagItem selectedTagItem) {
        TagItems = tagItems;
        this.parentContext = parentContext;
        this.selectedTagItem = selectedTagItem;
    }

    public TagItemAdapter(List<TagItem> tagItems, Context context) {
        TagItems = tagItems;
        parentContext=context;
    }

    @NonNull
    @Override
    public TagItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View tagView = inflater.inflate(R.layout.list_item_tag,parent,false);
        ViewHolder viewHolder = new ViewHolder(tagView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagItemAdapter.ViewHolder holder, int position) {
        holder.tagName.setText(TagItems.get(position).getName());
        if(TagItems.get(position).isChecked()){
            holder.selectedImage.setImageDrawable(parentContext.getDrawable(R.drawable.ic_star_black_24dp));
        }else {
            holder.selectedImage.setImageDrawable(parentContext.getDrawable(R.drawable.ic_star_border_black_24dp));
        }
    }

    @Override
    public int getItemCount() {
        return TagItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tagName;
        public ImageView selectedImage;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.text1);
            selectedImage = itemView.findViewById(R.id.imgSelected);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Log.d(TAG, "onClick: \n" + "Clicked at " + pos);
                    if (!TagItems.get(pos).isChecked()) {
                        TagItems.get(pos).setChecked(true);
                        selectedImage.setImageDrawable(parentContext.getDrawable(R.drawable.ic_star_black_24dp));
                        selectedTagItem.getSelectedTags().add(TagItems.get(pos));
                    } else {
                        TagItems.get(pos).setChecked(false);
                        selectedImage.setImageDrawable(parentContext.getDrawable(R.drawable.ic_star_border_black_24dp));
                        for (int i = 0; i < selectedTagItem.getSelectedTags().size(); i++) {
                            Log.d(TAG, "onClick: trying to find " + i);
                            Log.d(TAG, "onClick: " + selectedTagItem.getSelectedTags().get(i).getName());
                            if (selectedTagItem.getSelectedTags().get(i).getName().toString().contentEquals(tagName.getText().toString())) {
                                Log.d(TAG, "onClick: trying removal");
                                selectedTagItem.getSelectedTags().remove(i);
                                break;
                            }
                        }
                    }
                    Log.d(TAG, "onClick: \nSelected Tags: " + selectedTagItem.getSelectedTags());
                    fun();
                }
            });

        }
        void fun(){
            Log.d(TAG, "ViewHolder: trying to update tags");
            selectedTagItemAdapter.setSelectedTags(selectedTagItem.getSelectedTags());
            selectedTagItemAdapter.notifyDataSetChanged();
        }
    }
}
