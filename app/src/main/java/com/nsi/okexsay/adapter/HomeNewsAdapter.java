package com.nsi.okexsay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsi.okexsay.Beans.HomeNewsBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.http.StaticField;

public class HomeNewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private HomeNewsBean homeNewsBean;

    public HomeNewsAdapter(Context context, HomeNewsBean homeNewsBean) {
        this.context = context;
        this.homeNewsBean = homeNewsBean;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IremView(inflater.inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((IremView) viewHolder).bindData(i);
    }

    @Override
    public int getItemCount() {
        return homeNewsBean.getFarticleFtitle().size();
    }

    class IremView extends RecyclerView.ViewHolder {

        private ImageView idNewsImage;
        private TextView idNewsTitle;
        private TextView idNewsMsg;
        private TextView idNewsTime;

        public IremView(@NonNull View itemView) {
            super(itemView);
            idNewsImage = (ImageView) itemView.findViewById(R.id.id_news_image);
            idNewsTitle = (TextView) itemView.findViewById(R.id.id_news_title);
            idNewsMsg = (TextView) itemView.findViewById(R.id.id_news_msg);
            idNewsTime = (TextView) itemView.findViewById(R.id.id_news_time);
        }

        public void bindData(final int num) {
            if (context == null) {
                return;
            }
            Log.e("uuuuuuu", StaticField.HOST_BASE + homeNewsBean.getFarticlefurl().get(num));
            Glide.with(context).load(StaticField.HOST_BASE + homeNewsBean.getFarticlefurl().get(num)).asBitmap().into(idNewsImage);
            idNewsTitle.setText(homeNewsBean.getFarticleFtitle().get(num));
            idNewsMsg.setText(homeNewsBean.getFarticleFcontent_short().get(num));
            idNewsTime.setText(homeNewsBean.getFarticleList().get(num));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClick != null) {
                        onClick.click(homeNewsBean.getFarticleFid().get(num));
                    }
                }
            });
        }
    }


    public interface OnClick {
        void click(String id);
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

}
