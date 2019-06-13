package com.nsi.okexsay.ui.homepage.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.R;

public class TwoPurchaseAdapter extends BaseQuickAdapter<DevertisementListBean, BaseViewHolder> {

    public TwoPurchaseAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    public DevertisementListBean getItem(int position) {
        return super.getItem(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, DevertisementListBean item) {
        helper.setText(R.id.tv_Price, item.getPrice() + "").setText(R.id.tv_name, item.getUser().getFnickName())
                .setText(R.id.tv_Limit, item.getLimitMin() + "--" + item.getLimitMax())
                .setText(R.id.tv_number, item.getAmount() + "").setText(R.id.tv_type, "金额(" + item.getLegalCurrency().getCurrencySymbol() + ")");
        switch (item.getPayment()) {
            case "0,":
                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
                break;
            case "1,":
                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
                break;
            case "2,":
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


//        if (item.getPayment().equals("0")) {
//            helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
//            helper.getView(R.id.weixin).setVisibility(View.GONE);
//            helper.getView(R.id.legal_zhifubao).setVisibility(View.GONE);
//        } else {
//
//        }
//        if (item.getPayment().contains("1")) {
//            helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
//        }
//        if (item.getPayment().contains("2")) {
//            helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
//        }
    }
}
