package com.nsi.okexsay.ui.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsi.okexsay.Beans.IconExchangeHistoryBean;
import com.nsi.okexsay.R;

import java.util.List;

public class IconExchangeHistoryAdapter extends BaseAdapter {

    private List<IconExchangeHistoryBean> mList;
    private Context mContext;

    public IconExchangeHistoryAdapter(Context context, List<IconExchangeHistoryBean> pList) {
        this.mContext = context;
        this.mList = pList;
    }

    public void setList(List<IconExchangeHistoryBean> pList){
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
        convertView=_LayoutInflater.inflate(R.layout.item_icon_exchange_history, null);
        if(convertView!=null)
        {
            TextView name = convertView.findViewById(R.id.text_name);
            TextView rate = convertView.findViewById(R.id.text_rate);
            TextView counts = convertView.findViewById(R.id.text_number);
            TextView time = convertView.findViewById(R.id.text_time);
            TextView status = convertView.findViewById(R.id.text_status);

            name.setText(mList.get(position).getShortVrfName());
            rate.setText("1:"+mList.get(position).getVrexchangerate());
            counts.setText(mList.get(position).getVrCptexchangeNum());
            time.setText(mList.get(position).getVrDate().getTime());
        }
        return convertView;
    }

}
