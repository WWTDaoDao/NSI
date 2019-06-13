package com.nsi.okexsay.ui.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsi.okexsay.Beans.VirtualtocptBean;
import com.nsi.okexsay.R;

import java.util.List;

public class PayExchangeTypeAdapter extends BaseAdapter {

    private List<VirtualtocptBean> mList;
    private Context mContext;

    public PayExchangeTypeAdapter(Context context, List<VirtualtocptBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<VirtualtocptBean> pList){
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.item_pay_exchange_type, null);
        if(convertView!=null)
        {
            TextView name = convertView.findViewById(R.id.text_name_type);
            TextView yue = convertView.findViewById(R.id.text_yue_type);
            TextView counts = convertView.findViewById(R.id.text_number_type);
            name.setText(mList.get(position).getShortname()+"兑换CPT");
            name.setText(mList.get(position).getShortname());
            name.setText(mList.get(position).getShortname());
        }
        return convertView;
    }


}
