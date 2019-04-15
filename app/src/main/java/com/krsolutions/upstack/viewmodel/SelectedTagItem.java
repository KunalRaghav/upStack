package com.krsolutions.upstack.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.krsolutions.upstack.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectedTagItem extends AbstractItem<SelectedTagItem, SelectedTagItem.ViewHolder> {
    public StringHolder name;

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.text1;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.list_item_tag;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    public void setName(String name){
        this.name = new StringHolder(name);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<SelectedTagItem> {
        @BindView(R.id.text1)
        TextView name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(SelectedTagItem item, List<Object> payloads) {
            StringHolder.applyTo(item.name, name);
        }

        @Override
        public void unbindView(SelectedTagItem item) {
            name.setText(null);
        }
    }
}

