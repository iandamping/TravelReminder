package com.example.jun.travelreminder.fitur.output;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.DateUsers;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.base.BaseListAdapter;
import com.example.jun.travelreminder.base.BaseViewHolder;

import java.util.List;

public class OutputAdapter extends BaseListAdapter<item, OutputAdapter.ViewHolder> {

    public OutputAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_output;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, viewType), onItemClickListener);
    }

    @Override
    public List<item> getTask() {
        return super.getTask();
    }

    @Override
    public void setTask(List<item> item) {
        super.setTask(item);
    }

    public class ViewHolder extends BaseViewHolder<item> {
        public TextView tvDestination, tvDate;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            tvDestination = (TextView) itemView.findViewById(R.id.tvDestination);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(item item) {
            tvDestination.setText(item.getDestination());
            tvDate.setText(item.getItem());
//            tvDate.setText(Integer.toString(item.getJumlahBarang()));
        }
    }

    public class ViewHolders extends BaseViewHolder<DateUsers> {
        TextView tvDate;

        public ViewHolders(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(DateUsers item) {
            tvDate.setText(item.getArrivaldate().toString());
        }
    }
}
