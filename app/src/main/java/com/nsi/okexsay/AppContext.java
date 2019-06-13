package com.nsi.okexsay;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nsi.okexsay.utils.UnCeHandler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedList;
import java.util.List;

public class AppContext extends Application {

    public static Context context;
    public SharedPreferences preferences;
    public static Toast mToast;
    private List<Activity> activitys = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    public void init() {
        //设置该CrashHandler为程序的默认处理器
//        UnCeHandler catchExcep = new UnCeHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchExcep);

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setFooterHeight(50f);
                layout.setPrimaryColorsId(R.color.color_c1, android.R.color.white);
                ClassicsFooter classicsFooter = new ClassicsFooter(context);
                classicsFooter.setTextSizeTitle(14f);
                classicsFooter.setSpinnerStyle(SpinnerStyle.Translate);
                return classicsFooter;
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setHeaderHeight(55f);
                layout.setPrimaryColorsId(R.color.color_c1, android.R.color.white);
                ClassicsHeader classicsHeader = new ClassicsHeader(context);
                classicsHeader.setTextSizeTitle(11f);
                classicsHeader.setTextSizeTime(12f);
                classicsHeader.setDrawableArrowSize(14f);
                return classicsHeader;
            }
        });

        activitys = new LinkedList<>();
        context = getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Fresco.initialize(this);
    }

    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {
        for (Activity activity : activitys) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }
    }
}
