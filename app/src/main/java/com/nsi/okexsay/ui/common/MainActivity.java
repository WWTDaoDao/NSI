package com.nsi.okexsay.ui.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nsi.okexsay.AppManager;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.HomePageFragment;
import com.nsi.okexsay.ui.lawpage.LawPageFragment;
import com.nsi.okexsay.ui.personcenter.PersonCenterFragment;
import com.nsi.okexsay.ui.scoinpage.scoinpage.CoinPageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RelativeLayout rlTitle;
    private ImageView btn_back;
    private TextView tv_title;
    private ImageView img_notice;
    private FragmentTabAdapter tabAdapter = null;
    public List<Fragment> fragmentsList = new ArrayList<>();
    private RadioGroup mTabRg;
    private String indexFlag = "0";
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        rlTitle = findViewById(R.id.sign_up_activity_title);
        btn_back = findViewById(R.id.home_btn_back);
        tv_title = findViewById(R.id.home_text_title);
        img_notice = findViewById(R.id.home_btn_system_notes);
        tv_title.setText(getResources().getText(R.string.health_monitor));
        btn_back.setVisibility(View.GONE);
        fragmentsList.add(new HomePageFragment());
        fragmentsList.add(new LawPageFragment());
        fragmentsList.add(new CoinPageFragment());
        fragmentsList.add(new CoinPageFragment());
        fragmentsList.add(new PersonCenterFragment());
        mTabRg = findViewById(R.id.tab_rg_menu);
        tabAdapter = new FragmentTabAdapter(this, fragmentsList,
                R.id.realtabcontent, mTabRg);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
                    @Override
                    public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
                                                         int checkedId, int index) {
                        if (index == 0) {
                            indexFlag = "0";
                            rlTitle.setVisibility(View.GONE);
                            tv_title.setText(getResources().getText(R.string.health_monitor));
                        } else if (index == 1) {
                            indexFlag = "1";
                            rlTitle.setVisibility(View.GONE);
                            tv_title.setText(getResources().getText(R.string.home_doctor));
                        } else if (index == 2) {
                            indexFlag = "3";
                            rlTitle.setVisibility(View.GONE);
                            tv_title.setText(getResources().getText(R.string.notice_new));
                        } else if (index == 3) {
                            indexFlag = "3";
                            rlTitle.setVisibility(View.GONE);
                            tv_title.setText(getResources().getText(R.string.huodong));
                        } else if (index == 4) {
                            indexFlag = "3";
                            rlTitle.setVisibility(View.GONE);
                            tv_title.setText(getResources().getText(R.string.person_center));
                        }
                    }
                });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(),
                        R.string.double_press_exiting_app, Toast.LENGTH_SHORT)
                        .show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
