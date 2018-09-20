package com.example.jun.travelreminder.fitur.output;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.base.BaseListAdapter;
import com.example.jun.travelreminder.base.BaseViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OutputAdapter extends BaseListAdapter<item, OutputAdapter.ViewHolder> {
    private Date dateToday, timeToday;
    private DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String stringForDate;

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
        public LinearLayout llTimer;
        public TextView tvDestination, tvItems, tvTimer, tvDateReminder;
        public ImageView ivShare, ivAlarmClock;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            tvDestination = (TextView) itemView.findViewById(R.id.tvDestination);
            tvItems = (TextView) itemView.findViewById(R.id.tvItems);
            ivShare = itemView.findViewById(R.id.ivShare);
            llTimer = itemView.findViewById(R.id.llTimer);
            tvTimer = itemView.findViewById(R.id.tvTimer);
            ivAlarmClock = itemView.findViewById(R.id.ivAlarmClock);
            tvDateReminder = itemView.findViewById(R.id.tvDateReminder);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(item item) {
            dateToday = item.getArrivaldate();
            tvDestination.setText(item.getDestination());
            tvItems.setText(item.getItem());

            if (item.getSelectedHour() <= 0) {
                llTimer.setVisibility(View.GONE);
            } else if (item.getSelectedHour() >= 0) {
                llTimer.setVisibility(View.VISIBLE);
                stringForDate = writeFormat.format(dateToday);
                dateToday = item.getArrivaldate();
                tvDateReminder.setText(stringForDate);
                tvTimer.setText(item.getSelectedHour() + ":" + item.getSelectedMin());
            }

        }
    }

}
