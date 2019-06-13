package com.nsi.okexsay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsi.okexsay.Beans.BiBiBean;
import com.nsi.okexsay.R;

import java.util.List;

public class CurrentAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private List<BiBiBean> list_data;

    public CurrentAdapter(Context context, List<BiBiBean> list_data) {
        this.context = context;
        this.list_data = list_data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemView(inflater.inflate(R.layout.item_bibi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemView) viewHolder).bindData(list_data.get(i));
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class ItemView extends RecyclerView.ViewHolder {

        public ItemView(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(BiBiBean biBiBean) {

        }

    }
}
