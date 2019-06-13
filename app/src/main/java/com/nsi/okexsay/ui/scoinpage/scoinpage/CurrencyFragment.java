package com.nsi.okexsay.ui.scoinpage.scoinpage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsi.okexsay.Beans.BiBiBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.BiBi_in;
import com.nsi.okexsay.mvp.BiBi_p;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.adapter.BiBiUSDTAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.finalteam.okhttpfinal.RequestParams;

public class CurrencyFragment extends BaseFragment implements BiBi_in.View{

    private View rootView;

    private RecyclerView idRecyclerview;
    private BiBiUSDTAdapter adapter;
    private BiBi_p present;
    private SharedPreferences sp;
    private String user_token, fuserId;
    private Timer timer=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bibi, null);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        present = new BiBi_p(getActivity(), this);
        idRecyclerview = (RecyclerView) rootView.findViewById(R.id.id_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        idRecyclerview.setLayoutManager(linearLayoutManager);
        idRecyclerview.setNestedScrollingEnabled(false);
        //设置增加或删除条目的动画
        idRecyclerview.setItemAnimator(new DefaultItemAnimator());
        sp = appContext.preferences;
        user_token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        fuserId = sp.getString(SharedPreferencesKeys.FID, "");
        adapter = new BiBiUSDTAdapter(R.layout.item_bibi);
        idRecyclerview.setAdapter(adapter);
        get_List();
    }
    @Override
    public void onResume() {
        super.onResume();
        timer = new Timer(true);
        timer.schedule(timerTask, 5000, 5000); //延时1000ms后执行，1000ms执行一次
    }
    TimerTask timerTask = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                //回到主线程执行结束操作
                get_List();
            }
        }
    };
    public static CurrencyFragment newInstance() {

        Bundle args = new Bundle();

        CurrencyFragment fragment = new CurrencyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void set_data(List<BiBiBean> list) {

    }
    private void get_List() {//获取数据
        Shoudialog();
        RequestParams params = new RequestParams();
        params.put("type", "CPT");
        params.put("token", user_token);
        params.put("fuserId", fuserId);//用户ID);
        present.load_data(params);
    }

    @Override
    public void onStop() {
        super.onStop();
        // 停止计时任务
        timer.cancel();
    }
}
