package com.nsi.okexsay.ui.personcenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.wiget.LoadingDialog;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rela_clear, rela_logout;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    ImageView homeBtnBack;
    TextView homeTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        rela_clear = findViewById(R.id.rela_clear);
        rela_logout = findViewById(R.id.rela_logout);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextTitle.setText("系统设置");

        rela_clear.setOnClickListener(this);
        rela_logout.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.rela_clear:
                dialog.setLoadText("正在清除");
                dialog.show();
                CountDownTimer timer = new CountDownTimer(5 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        showShortToast("已清除缓存！");
                        dialog.dismiss();
                    }
                }.start();

                break;
            case R.id.rela_logout:
                logoutAlert();
                break;
        }
    }

    public void logoutAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("确定退出登录？");
        dialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(SharedPreferencesKeys.IS_LOGIN, false);
                        editor.commit();
                        Intent intent= new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        dialog.create();
        dialog.show();
    }

}
