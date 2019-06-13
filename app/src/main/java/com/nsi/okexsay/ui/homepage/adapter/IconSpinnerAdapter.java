package com.nsi.okexsay.ui.homepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsi.okexsay.Beans.VirtualtocptBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.homepage.interfaces.OnIconClickListener;

import java.util.List;

public class IconSpinnerAdapter extends BaseAdapter {

    private List<VirtualtocptBean> mList;
    private Context mContext;
    private OnIconClickListener onIconClickListener;

    public IconSpinnerAdapter(Context context, List<VirtualtocptBean> pList) {
        this.mContext = context;
        this.mList = pList;
        this.onIconClickListener = (OnIconClickListener)context;
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
        convertView=_LayoutInflater.inflate(R.layout.spinner_icon_style, null);
        if(convertView!=null)
        {
            LinearLayout line = convertView.findViewById(R.id.line);
            TextView textView = convertView.findViewById(R.id.textView);
            textView.setText(mList.get(position).getShortname());
            line.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onIconClickListener.onChangeData(position);
                        }
                    }
            );
        }
        return convertView;
    }

}
