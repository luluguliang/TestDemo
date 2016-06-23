package com.example.dialogdemo.dialogdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity implements MyRecycleAdapter.OnRecyclerViewListener {
    private RecyclerView recycleView;
    private ArrayList<String> personList;
    private RecyclerView horizonRecycle;
    private MyRecycleAdapter myRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recycleView = (RecyclerView) findViewById(R.id.id_recycleView);
        //设置Item增加、移除动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setLayoutManager(new FullyLinearLayoutManager(this));
        initData();
        myRecycleAdapter = new MyRecycleAdapter(personList);
        myRecycleAdapter.setOnRecycleListener(this);
        recycleView.setAdapter(myRecycleAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        horizonRecycle = (RecyclerView) findViewById(R.id.id_horizontal_recycleView);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizonRecycle.setLayoutManager(layoutManager);
        horizonRecycle.setAdapter(new MyRecycleAdapter(personList));


    }

    private void initData() {
         personList = new ArrayList<>();
        for (int i = 0;i<20;i++){
            personList.add("zhangsan"+i);
        }
    }
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"被点击了"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(int position) {
        Toast.makeText(this,"被长按了"+position,Toast.LENGTH_SHORT).show();
        personList.remove(position);
        myRecycleAdapter.notifyItemRemoved(position);
        return true;
    }

}
