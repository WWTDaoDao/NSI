package com.nsi.okexsay.ui.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.utils.DateUtils;

/**
 * 启动动画界面
 */
public class SplashActivity extends BaseActivity {
    String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    AppContext appContext;
    SharedPreferences sp;
    private int WRITE_REQUEST_CODE = 14;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) { // 判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            // 如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;// finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        } else {
            view = View.inflate(this, R.layout.splash_activity, null);
            setContentView(view);
            appContext = (AppContext) getApplication();
            sp = appContext.preferences;
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, perms, WRITE_REQUEST_CODE);
                return;
            } else {
                startAnimation(view);
            }

        }
    }

    private void startAnimation(View view) {
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                afterSplash();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });

    }

    private void afterSplash() {
        if (sp.getBoolean(SharedPreferencesKeys.IS_LOGIN, false)) {
            Intent intentMain = new Intent();
            intentMain.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intentMain);
        } else {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.splash_fade_in_anim, R.anim.splash_fade_out_anim);
        }
    }

    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == WRITE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                new UploadUserIcon(PersonMessageActivity.this)
//                        .startImagePick();
                startAnimation(view);
            } else {
                Toast.makeText(appContext, "请在设置中设置存储的权限", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}
