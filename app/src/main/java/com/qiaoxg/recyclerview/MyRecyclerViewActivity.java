package com.qiaoxg.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qiaoxg.recyclerview.adapter.MyRecyclerViewAdapter;


public class MyRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRv;

    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        initView();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MyRecyclerViewAdapter(this);
        recyclerViewRv.setAdapter(mAdapter);
    }

    private void initView() {
        recyclerViewRv = (RecyclerView) findViewById(R.id.recyclerView_rv);
        recyclerViewRv.setLayoutManager(new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false));
    }

}
