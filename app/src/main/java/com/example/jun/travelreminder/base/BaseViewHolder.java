package com.example.jun.travelreminder.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by agusn on 4/12/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private BaseListAdapter.OnItemClickListener onItemClickListener;

    public BaseViewHolder(View itemView, BaseListAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        itemView.setOnClickListener(this);
    }

    public abstract void bind(T item);

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
