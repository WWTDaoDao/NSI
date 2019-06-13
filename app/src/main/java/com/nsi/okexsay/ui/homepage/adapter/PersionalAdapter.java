package com.nsi.okexsay.ui.homepage.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.Beans.PersionalBean;
import com.nsi.okexsay.R;

public class PersionalAdapter extends BaseQuickAdapter<PersionalBean.FvirtualwalletsBean, BaseViewHolder> {

    public PersionalAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    @Override
    public PersionalBean.FvirtualwalletsBean getItem(int position) {
        return super.getItem(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersionalBean.FvirtualwalletsBean item) {
//        helper.setText(R.id.tv_name, item.getFvirtualcointype()).setText(R.id.tv_name, item.getUser().getFrealName())
//                .setText(R.id.tv_Limit, item.getLimitMin() + "--" + item.getLimitMax())
//                .setText(R.id.tv_number, item.getAmount() + "").setText(R.id.tv_type, "金额(" + item.getLegalCurrency().getCurrencySymbol() + ")");
    }
}
