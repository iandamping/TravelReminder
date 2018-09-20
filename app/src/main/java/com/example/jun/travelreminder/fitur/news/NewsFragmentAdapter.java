package com.example.jun.travelreminder.fitur.news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.base.BaseListAdapter;
import com.example.jun.travelreminder.base.BaseViewHolder;
import com.example.jun.travelreminder.model.news.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsFragmentAdapter extends BaseListAdapter<Article, NewsFragmentAdapter.ViewHolder> {
    public NewsFragmentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_news_fragmetn;
    }

    @Override
    public List<Article> getTask() {
        return super.getTask();
    }

    @Override
    public void setTask(List<Article> item) {
        super.setTask(item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, viewType), onItemClickListener);
    }


    public class ViewHolder extends BaseViewHolder<Article> {
        TextView tvAuthor, tvDate, tvTitle, tvDesc;
        ImageView ivImage;

        private ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDate = itemView.findViewById(R.id.tvItems);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(Article item) {
            tvTitle.setText(item.getTitle());
            tvDesc.setText(item.getDescription());
            tvDate.setText(item.getPublishedAt());
            tvAuthor.setText(item.getAuthor());
            Picasso.with(context).load(item.getUrlToImage()).into(ivImage);
        }
    }
}
