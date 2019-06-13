package com.nsi.okexsay.ui.personcenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.FindPasswordActivity;
import com.nsi.okexsay.ui.login.FindPasswordTwoActivity;
import com.nsi.okexsay.wiget.LoadingDialog;

public class SafeCenterActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rela_change_password;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    ImageView homeBtnBack;
    TextView homeTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_center);
        initView();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        rela_change_password = findViewById(R.id.rela_change_password);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextTitle.setText("安全中心");

        rela_change_password.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.rela_change_password:
                Intent intentFind = new Intent(this, FindPasswordActivity.class);
                startActivity(intentFind);
                break;
        }
    }

}
