package com.example.jun.travelreminder.fitur.output;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jun.travelreminder.Databasenya.ROOM.ViewModel.itemviewmodel.LoadMainViewModel;
import com.example.jun.travelreminder.Databasenya.ROOM.model_entity.item;
import com.example.jun.travelreminder.R;
import com.example.jun.travelreminder.base.BaseListAdapter;
import com.example.jun.travelreminder.fitur.input.InputActivity;
import com.example.jun.travelreminder.fitur.input.InputDetail;

import java.util.List;

public class OutputFragment extends Fragment implements OutputFragmentView, View.OnClickListener {
    FloatingActionButton fab;
    private Context context;
    private RecyclerView rvInput;
    private OutputAdapter adapter;
    private OutputFragmentPresenter presenter;
    private GridLayoutManager gridLayout;
    private LinearLayoutManager linearLayout;

    public static OutputFragment newInstance() {
        OutputFragment fragment = new OutputFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        presenter = new OutputFragmentPresenter(this);
        presenter.onAttach(context);
        adapter = new OutputAdapter(context);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_output, container, false);
        presenter.onCreateView(view);
        return view;
    }

    @Override
    public void showData() {
        LoadMainViewModel viewModel = ViewModelProviders.of(getActivity()).get(LoadMainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<item>>() {
            @Override
            public void onChanged(@Nullable List<item> list_semuas) {
                adapter.setTask(list_semuas);
            }
        });

    }

    @Override
    public void initView(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        rvInput = (RecyclerView) view.findViewById(R.id.rvInput);
//        gridLayout = new GridLayoutManager(context, 2);
//        rvInput.setLayoutManager(gridLayout);
//        rvInput.setAdapter(adapter);
//        DividerItemDecoration decoration = new DividerItemDecoration(context.getApplicationContext(), VERTICAL);
        linearLayout = new LinearLayoutManager(context);
        rvInput.setLayoutManager(linearLayout);
        rvInput.setAdapter(adapter);
//        rvInput.addItemDecoration(decoration);

    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new BaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(getActivity(), InputDetail.class);
                i.putExtra(InputActivity.EXTRA_TASK_ID_KEY, position + 1);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
//                AppExecutor.getsInstance().getDiskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        int position = viewHolder.getAdapterPosition();
//                        List<item> list_semuanya = adapter.getTask();
//                        MainApplication.getDatabase().dao_item().deleteAll(list_semuanya.get(position));
////                        mDb.dao_item().deleteById(position)
//                    }
//                });
//            }
//        }).attachToRecyclerView(rvInput);

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
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onClick(View view) {

    }
}
