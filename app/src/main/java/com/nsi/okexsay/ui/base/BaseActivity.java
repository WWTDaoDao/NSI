package com.nsi.okexsay.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.AppManager;
import com.nsi.okexsay.utils.LogUtil;
import com.nsi.okexsay.wiget.LoadingDialog;

public class BaseActivity extends FragmentActivity {

    private static final String TAG = "BaseActivity";
    public AppContext appContext;
    public LoadingDialog dialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (dialog==null){
            dialog = new LoadingDialog(this);
        }
        LogUtil.d(TAG, this.getClass().getSimpleName()
                + " onCreate() invoked!!");
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        appContext = (AppContext) getApplication();
        appContext.addActivity(this);
    }

    public void Shoudialog() {
        dialog.show();
    }

    public void DissDialog() {
        dialog.dismiss();
    }

    @Override
    protected void onStart() {
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtil.d(TAG, this.getClass().getSimpleName()
                + " onRestart() invoked!!");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogUtil.d(TAG, this.getClass().getSimpleName()
                + " onResume() invoked!!");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtil.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
        super.onStop();
    }

    protected void showShortToast(int pResId) {
        showShortToast(getString(pResId));
    }

    protected void showLongToast(String pMsg) {
        AppContext.showToast(pMsg);
    }

    protected void showShortToast(String pMsg) {
        AppContext.showToast(pMsg);
    }

    protected boolean hasExtra(String pExtraKey) {
        if (getIntent() != null) {
            return getIntent().hasExtra(pExtraKey);
        }
        return false;
    }

    protected void openActivityNew(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    public void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }
}
