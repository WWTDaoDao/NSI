package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nsi.okexsay.Beans.DistributeMSG;
import com.nsi.okexsay.Beans.JurisdictionBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.OperationActivity;
import com.nsi.okexsay.ui.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.JarURLConnection;

/**
 * 操作
 */
public class OperationFragment extends BaseFragment {
    private View rootView;
    private TextView textView;
    private boolean isnumber = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        rootView = inflater.inflate(R.layout.operation_fragment, null);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    //List的UI数据加载完
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(JurisdictionBean messageEvent) {
        if (isnumber){//避免重复加载
            isnumber=false;
            setYonghu();
        }
    }

    //判断是否是用户
    private void setYonghu() {
        if (!StaticField.IsMerchant.equals("1")) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            Intent intent = new Intent(getActivity(), OperationActivity.class);
            startActivity(intent);
        }
    }

    private void initViews(View rootView) {
        textView = rootView.findViewById(R.id.TV_msg);
        //  setYonghu();
    }

    @Override
    public void onStop() {
        super.onStop();
        isnumber=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
