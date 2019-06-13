package com.nsi.okexsay.ui.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.view.JDTabLayout;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.OnTabSelectedListener;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.adapter.FragmentViewPagerAdapter;

import java.util.ArrayList;

public class OrderActivity  extends BaseActivity {
    private TextView tv_title;
    private ViewPager customViewPager;
    private JDTabLayout jdTabLayout;
    private String[] dataTitle = {"进行中", "已付款", "已完成", "已取消"};
    private OrderPageProgressivetenseFragment orderPageProgressivetenseFragment;//进行中
    private OrderPaymentFragment orderPaymentFragment;//已付款
    private OrderCompleteFragment orderCompleteFragment;//已完成
    private OrderCancelFragment orderCancelFragment;//已取消
    private ArrayList<Fragment> fragmentList;
    private FragmentViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderactivity);
        initView();
        add();
        setOnListener();
    }
    private void initView() {
        tv_title = findViewById(R.id.home_text_title);
        customViewPager = findViewById(R.id.viewPager);
        jdTabLayout = findViewById(R.id.tabLayout);
        tv_title.setText("订单记录");
    }
    private void add() {
        orderPageProgressivetenseFragment = new OrderPageProgressivetenseFragment();
        orderPaymentFragment = new OrderPaymentFragment();
        orderCompleteFragment = new OrderCompleteFragment();
        orderCancelFragment = new OrderCancelFragment();
        fragmentList = new ArrayList();
        fragmentList.add(orderPageProgressivetenseFragment);
        fragmentList.add(orderPaymentFragment);
        fragmentList.add(orderCompleteFragment);
        fragmentList.add(orderCancelFragment);
        jdTabLayout.setTabTitles(dataTitle).setSelectedIndex(0).create();
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), customViewPager, fragmentList);
        customViewPager.setAdapter(adapter);
        customViewPager.setCurrentItem(0);
    }
    private void setOnListener() {
        jdTabLayout.setTabTitles(dataTitle).setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                customViewPager.setCurrentItem(index);
//                if (index==3){
//                    EventBus.getDefault().postSticky(new JurisdictionBean("检查"));
//                }
            }
        }).create();
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                jdTabLayout.onPageScrolled(i, v, i1);

            }

            @Override
            public void onPageSelected(int i) {
                jdTabLayout.onPageSelected(i);
                // index=i;
//                if (index==3){
//                    EventBus.getDefault().postSticky(new JurisdictionBean("检查"));
//                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                jdTabLayout.onPageScrollStateChanged(i);
            }
        });
    }
}

