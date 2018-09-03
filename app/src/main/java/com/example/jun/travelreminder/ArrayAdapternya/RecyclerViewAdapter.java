package com.example.jun.travelreminder.ArrayAdapternya;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHoldernya> {

    List<item> list_data;
    RecyclerViewSetOnClickListenernya click;
    Context context;

    public RecyclerViewAdapter(Context context, RecyclerViewSetOnClickListenernya res) {
        this.context = context;
        this.click = res;
    }

    @Override
    public ViewHoldernya onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_output, parent, false);
        return new ViewHoldernya(view);
    }

    @Override
    public void onBindViewHolder(ViewHoldernya holder, int position) {
        item list = list_data.get(position);
        holder.tvBarang.setText("Barangnya : " + list.getItem());

//        int total = list.getJumlahBarang();
//        String jumlahBarangTotal = "Jumlah Barang : " + total;
//        holder.tvTotal.setText(jumlahBarangTotal);

    }

    public List<item> getTask() {
        return list_data;
    }

    public void setTask(List<item> test) {
        this.list_data = test;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list_data == null) {
            return 0;
        }
        return list_data.size();
    }

    public interface RecyclerViewSetOnClickListenernya {
        void onClickListenerNya(int a);
    }


    public class ViewHoldernya extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvBarang, tvTotal;

        public ViewHoldernya(View itemView) {
            super(itemView);

            tvBarang = (TextView) itemView.findViewById(R.id.tvBarang);
            tvTotal = (TextView) itemView.findViewById(R.id.tvTotal);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickItem = list_data.get(getAdapterPosition()).getIDnya();
            click.onClickListenerNya(clickItem);
        }
    }


}
