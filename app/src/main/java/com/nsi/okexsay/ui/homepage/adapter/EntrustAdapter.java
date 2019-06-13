package com.nsi.okexsay.ui.homepage.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.R;

public class EntrustAdapter extends BaseQuickAdapter<DevertisementListBean, BaseViewHolder> {
    private int mtype = -1;

    public EntrustAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    //表示买入卖出
    public void setType(int type) {
        this.mtype = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, DevertisementListBean item) {
        helper.setText(R.id.tv_Price, item.getPrice() + "")
                .setText(R.id.tv_Limit, item.getLimitMin() + "--" + item.getLimitMax())
                .setText(R.id.tv_number, item.getAmount() + "").setText(R.id.tv_type, "金额(" + item.getLegalCurrency().getCurrencySymbol() + ")")//item.getLegalCurrency().getCurrencySymbol()
                .addOnClickListener(R.id.Tv_diss);
        if (item.getStatus().equals("0")) {
            helper.setText(R.id.Tv_diss, "已撤销");
        } else {
            helper.setText(R.id.Tv_diss, "撤销");
        }
        switch (item.getPayment()) {
            case ",0":
                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
                break;
            case "1":
                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
                break;
            case "2":
                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
                break;
            case "0,1":
                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
                break;
            case "0,2":
                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
                break;
            case "1,2":
                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
                break;
            case "0,1,2":
                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
                break;
        }
        if (mtype == 1) {
            helper.setText(R.id.tv_name, "买入");
        } else {
            helper.setText(R.id.tv_name, "卖出");
        }
    }
}
