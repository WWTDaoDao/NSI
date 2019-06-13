package com.nsi.okexsay.ui.homepage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.wiget.LoadingDialog;

public class InChangeActivity extends BaseActivity implements View.OnClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    ImageView homeBtnBack;
    TextView homeTextTitle,homeTextRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inchange);
        initView();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeTextRight = findViewById(R.id.home_text_right);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextRight.setVisibility(View.VISIBLE);
        homeTextTitle.setText("虚拟币提现");
        homeTextRight.setText("消费明细");

        homeTextRight.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.home_text_right:
                break;
        }
    }
}
