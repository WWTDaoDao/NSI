package com.nsi.okexsay.ui.homepage.adapter;

import android.support.annotation.LayoutRes;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nsi.okexsay.Beans.LawPurchaseBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.utils.TimeUtils;

public class LawPageCanceAdapter extends BaseQuickAdapter<LawPurchaseBean, BaseViewHolder> {

    public LawPageCanceAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    public LawPurchaseBean getItem(int position) {
        return super.getItem(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, LawPurchaseBean item) {
        helper.setText(R.id.TVorder_Number, "订单编号:" + item.getOrderNo()).setText(R.id.tv_Price, "价格:" + item.getC2c().getPrice() + "")
                .setText(R.id.tv_number, "数量:" + item.getAmountReal()).setText(R.id.TV_Data, "待定").setText(R.id.tv_JE,  item.getTotalPrice() + "   (" + item.getC2c().getLegalCurrency().getCurrencySymbol() + ")")
                .setText(R.id.tv_people,item.getC2c().getUser().getFnickName());
        if (item.getC2c().getCptOrUsdt().equals("1")) {
            helper.setText(R.id.type, "USDT/" + item.getC2c().getLegalCurrency().getCurrencySymbol());
        } else {
            helper.setText(R.id.type, "CPT/" + item.getC2c().getLegalCurrency().getCurrencySymbol());
        }
        if (item.getC2c().getTradeType().equals("1")) {
            helper.setText(R.id.tv_name, "卖");
            TextView textView = helper.getView(R.id.tv_name);
            textView.setBackgroundResource(R.drawable.shape_corners_light_yellow);
        } else {
            helper.setText(R.id.tv_name, "买");
            TextView textView = helper.getView(R.id.tv_name);
            textView.setBackgroundResource(R.drawable.shape_corners_light_blue);
        }
       // helper.setText(R.id.TV_Data, TimeUtils.getDateTimeFromMillisecond(item.getXiadanTime().getTime()));
    }
}
