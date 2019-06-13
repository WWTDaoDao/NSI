package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.HomeNewsBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.adapter.HomeNewsAdapter;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.HomeNews_in;
import com.nsi.okexsay.mvp.HomeNews_p;
import com.nsi.okexsay.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 首页 媒体报道、热门话题、官方新闻列表
 */
public class HomeNewsActivity extends BaseActivity
        implements HomeNews_in.View, HomeNewsAdapter.OnClick {

    private ImageView homeBtnBack;
    private TextView idTitle;
    private RecyclerView idRecyclerview;
    private HomeNewsAdapter adapter;
    private List<HomeNewsBean> list_data = new ArrayList<>();

    private HomeNews_p present;
    private String user_token, fuserId;
    private SharedPreferences sp;

    private String type, title;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_news);

        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        user_token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        fuserId = sp.getString(SharedPreferencesKeys.FID, "");

        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");

        initView();
    }

    private void initView() {

        present = new HomeNews_p(this, this);
        tv_title = findViewById(R.id.home_text_title);
        homeBtnBack = (ImageView) findViewById(R.id.home_btn_back);
        idTitle = (TextView) findViewById(R.id.id_title);
        homeBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        tv_title.setText(title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeNewsActivity.this);
        idRecyclerview.setLayoutManager(linearLayoutManager);
        idRecyclerview.setNestedScrollingEnabled(false);
        //设置增加或删除条目的动画
        idRecyclerview.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void onResume() {
        super.onResume();

        RequestParams params = new RequestParams();
        params.put("token", user_token);
        params.put("fuserId", fuserId);//用户ID);
        params.put("type", type);
        present.load_data(params);
    }

    @Override
    public void set_list_data(HomeNewsBean homeNewsBean) {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new HomeNewsAdapter(this, homeNewsBean);
            adapter.setOnClick(this);
            idRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void click(String id) {
        Intent intent = new Intent(HomeNewsActivity.this, Web_Activity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", "http://www.51integral.com/service/article.html?id=" + id);
        startActivity(intent);
    }
}
