package com.nsi.okexsay.ui.scoinpage.scoinpage;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class CoinPageFragment extends BaseFragment
        implements View.OnClickListener {

    private View rootView;

    private TextView idBibiTrading;
    private TextView idCurrencyTrading;
    private FrameLayout layFrame;

    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_notice_news, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        idBibiTrading = (TextView) rootView.findViewById(R.id.id_bibi_trading);
        idCurrencyTrading = (TextView) rootView.findViewById(R.id.id_currency_trading);
        layFrame = (FrameLayout) rootView.findViewById(R.id.layFrame);

        idBibiTrading.setOnClickListener(this);
        idCurrencyTrading.setOnClickListener(this);

        fragments = getFragments();
        setDefaultFragment();
    }

    /**
     * 设置Fragment
     *
     * @return
     */
    private ArrayList<BaseFragment> getFragments() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(BiBiFragment.newInstance());
        fragments.add(CurrencyFragment.newInstance());
        return fragments;
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BaseFragment fragment = BiBiFragment.newInstance();
        ft.replace(R.id.layFrame, fragment);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
        FragmentManager fm = getChildFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        BaseFragment fragment = null;

        switch (view.getId()) {
            case R.id.id_bibi_trading:
                //第一个页面
                fragment = fragments.get(0);
                idBibiTrading.setBackground(getResources().getDrawable(R.drawable.view_yj_blue));
                idCurrencyTrading.setBackground(getResources().getDrawable(R.drawable.view_yj_mrblue));
                idCurrencyTrading.setTextColor(getResources().getColor(R.color.btn_blue));
                idBibiTrading.setTextColor(getResources().getColor(R.color.white));
                break;

            case R.id.id_currency_trading:
                //第二个页面
                fragment = fragments.get(1);
                idBibiTrading.setBackground(getResources().getDrawable(R.drawable.view_yj_mblue));
                idCurrencyTrading.setBackground(getResources().getDrawable(R.drawable.view_yj_rblue));
                idCurrencyTrading.setTextColor(getResources().getColor(R.color.white));
                idBibiTrading.setTextColor(getResources().getColor(R.color.btn_blue));
                break;
        }

        // 使用当前Fragment的布局替代id_content的控件
        transaction.replace(R.id.layFrame, fragment);
        transaction.commit();
    }
}
