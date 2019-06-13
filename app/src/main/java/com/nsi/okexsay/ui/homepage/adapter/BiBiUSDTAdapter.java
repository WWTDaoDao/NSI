package com.nsi.okexsay.ui.homepage.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nsi.okexsay.Beans.BiBiBean;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.R;

import java.text.DecimalFormat;

public class BiBiUSDTAdapter extends BaseQuickAdapter<BiBiBean, BaseViewHolder> {

    public BiBiUSDTAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    public BiBiBean getItem(int position) {
        return super.getItem(position);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, BiBiBean item) {
        helper.setText(R.id.id_name, item.getSymbol() + "").setText(R.id.id_unit, "/" + "USDT")
                .setText(R.id.id_money, item.getPrice() + "").setText(R.id.id_number, "24H成交量 " + item.getTotal());
        double dv = item.getPrice() * item.getExchangeRate();
        DecimalFormat df = new DecimalFormat("#.0000");
        String str = df.format(dv);
        helper.setText(R.id.id_money_text, str+"CNY");
        TextView textView= helper.getView(R.id.id_trent);
        TextView id_money= helper.getView(R.id.id_money);
        textView.setText(item.getRose()+"");
        if (item.getRose()<0){
            textView.setBackgroundResource(R.color.greenbibi);
            id_money.setTextColor(R.color.greenbibi);
        }else {
            textView.setBackgroundResource(R.color.redbibi);
            id_money.setTextColor(R.color.redbibi);
        }

        //  double
//        helper.setText(R.id.tv_Price, item.getPrice() + "").setText(R.id.tv_name, item.getUser().getFnickName())
//                .setText(R.id.tv_Limit, item.getLimitMin() + "--" + item.getLimitMax())
//                .setText(R.id.tv_number, item.getAmount() + "").setText(R.id.tv_type, "金额(" + item.getLegalCurrency().getCurrencySymbol() + ")");
//        switch (item.getPayment()) {
//            case "0,":
//                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
//                break;
//            case "1":
//                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
//                break;
//            case "2":
//                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
//                break;
//            case "0,1":
//                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.INVISIBLE);
//                break;
//            case "0,2":
//                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
//                break;
//            case "1,2":
//                helper.getView(R.id.yinhangka).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
//                break;
//            case "0,1,2":
//                helper.getView(R.id.yinhangka).setVisibility(View.VISIBLE);
//                helper.getView(R.id.weixin).setVisibility(View.VISIBLE);
//                helper.getView(R.id.legal_zhifubao).setVisibility(View.VISIBLE);
//                break;
//        }


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
