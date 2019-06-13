package com.nsi.okexsay.ui.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsi.okexsay.Beans.PayAndColletionHistoryBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.utils.TimeUtils;

import java.util.List;

public class PayAndCollectionHistoryAdapter extends BaseAdapter {

    private List<PayAndColletionHistoryBean> mList;
    private Context mContext;

    public PayAndCollectionHistoryAdapter(Context context, List<PayAndColletionHistoryBean> pList) {
        this.mContext = context;
        this.mList = pList;
    }

    public void setList(List<PayAndColletionHistoryBean> pList){
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
        convertView=_LayoutInflater.inflate(R.layout.item_pay_collection_history, null);
        if(convertView!=null)
        {
            TextView address = convertView.findViewById(R.id.text_address);
            TextView time = convertView.findViewById(R.id.text_time);
            TextView counts = convertView.findViewById(R.id.text_count);

            address.setText(mList.get(position).getWithdraw_virtual_address());
            if(mList.get(position).getFtype().equals("1")){
                counts.setTextColor(mContext.getResources().getColor(R.color.btn_blue));
                counts.setText("+"+mList.get(position).getFamount());
            } else {
                counts.setTextColor(mContext.getResources().getColor(R.color.actionsheet_red));
                counts.setText("-"+mList.get(position).getFamount());
            }

            time.setText(TimeUtils.getDateTimeFromMillisecond(Long.parseLong(mList.get(position).getFcreateTime().getTime())));
        }
        return convertView;
    }

}
