package com.nsi.okexsay.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.view.JDTabLayout;
import com.nsi.okexsay.Beans.JurisdictionBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.LawPageCancelFragment;
import com.nsi.okexsay.ui.homepage.LawPageCompleteFragment;
import com.nsi.okexsay.ui.homepage.LawPagePaymentFragment;
import com.nsi.okexsay.ui.homepage.LawPageProgressivetenseFragment;
import com.nsi.okexsay.ui.homepage.adapter.FragmentViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class OperationActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private ViewPager customViewPager;
    private JDTabLayout jdTabLayout;
    private String[] dataTitle = {"进行中", "已付款", "已完成", "已取消"};
    private LawPageProgressivetenseFragment lawPageProgressivetenseFragment;//进行中
    private LawPagePaymentFragment lawPagePaymentFragment;//已付款
    private LawPageCompleteFragment lawPageCompleteFragment;//已完成
    private LawPageCancelFragment lawPageCancelFragment;//已取消
    private ArrayList<Fragment> fragmentList;
    private FragmentViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operationactivity);
        initView();
        add();
        setOnListener();
    }




    private void initView() {
        tv_title = findViewById(R.id.home_text_title);
        customViewPager = findViewById(R.id.viewPager);
        jdTabLayout = findViewById(R.id.tabLayout);
        tv_title.setText("买卖操作");
    }

    private void add() {
        lawPageProgressivetenseFragment = new LawPageProgressivetenseFragment();
        lawPagePaymentFragment = new LawPagePaymentFragment();
        lawPageCompleteFragment = new LawPageCompleteFragment();
        lawPageCancelFragment = new LawPageCancelFragment();
        fragmentList = new ArrayList();
        fragmentList.add(lawPageProgressivetenseFragment);
        fragmentList.add(lawPagePaymentFragment);
        fragmentList.add(lawPageCompleteFragment);
        fragmentList.add(lawPageCancelFragment);
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
    @Override
    public void onClick(View v) {

    }

}
