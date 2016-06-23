package com.example.dialogdemo.dialogdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter{
    private  List<String> personList;


    public MyRecycleAdapter(ArrayList<String> personList) {
        this.personList = personList;
    }

    public void setOnRecycleListener(OnRecyclerViewListener onRecycleListener) {
        this.onRecycleListener = onRecycleListener;
    }

    public OnRecyclerViewListener onRecycleListener;
    public  interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item_recycleview,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        ((MyViewHolder) holder).position = position;
        ((MyViewHolder) holder).tvAge.setText(personList.get(position));
        ((MyViewHolder) holder).tvName.setText(personList.get(position));

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvName;
        public TextView tvAge;
        public int position;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.id_person_name_tv);
            tvAge = (TextView)itemView.findViewById(R.id.id_person_age_tv);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onRecycleListener != null){
                onRecycleListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(onRecycleListener != null){
                return onRecycleListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
