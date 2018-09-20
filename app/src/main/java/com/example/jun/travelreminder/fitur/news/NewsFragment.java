package com.example.jun.travelreminder.fitur.news;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.newsviewmodel.LoadAllDataNews;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.model.news.Article;
import com.example.jun.travelreminder.network.RequestObserver;

public class NewsFragment extends Fragment implements NewsFragmentView, NewsFragmentOnClickListener {
    NewsFragmentPresenter presenter;
    NewsFragmentAdapter adapter;
    Context context;
    private RecyclerView rvNews;
    private LinearLayoutManager linearLayout;


    public static NewsFragment newInstance() {
        Bundle bundle = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        presenter.onCreateView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        presenter = new NewsFragmentPresenter(this);
        presenter.onAttach(context);
        adapter = new NewsFragmentAdapter(context);
        presenter.getData(RequestObserver.HAS_PULL);


    }


    @Override
    public void failedData(String message) {

    }


    @Override
    public void initView(View view) {
        rvNews = view.findViewById(R.id.rvNews);

//        presenter.getData(state);

        LoadAllDataNews views = ViewModelProviders.of(getActivity()).get(LoadAllDataNews.class);
        views.getListLiveData().observe(this, articles -> adapter.setTask(articles));

        linearLayout = new LinearLayoutManager(context);
        rvNews.setLayoutManager(linearLayout);
        rvNews.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void initListener() {

    }

    @Override
    public void startTask() {

    }

    @Override
    public void finishedTask() {

    }

    @Override
    public void failureTask(String message) {

    }


    @Override
    public void onClick(Article position) {

    }
}
