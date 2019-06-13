package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.VirtualtocptBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.adapter.SelcetIconTypeAdapter;

import java.util.ArrayList;
import java.util.List;


public class SelectIconTypeActivity extends BaseActivity implements View.OnClickListener {
    private AppContext appContext;
    private SharedPreferences sp;
    private ListView lv_type;
    private ImageView homeBtnBack;
    private TextView homeTextTitle;
    private List<VirtualtocptBean> dataList = new ArrayList<>();
    private SelcetIconTypeAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_icon_type);
        initView();
    }

    private void initView(){
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        lv_type = findViewById(R.id.lv_type);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextTitle.setText("选择币种");
        homeBtnBack.setOnClickListener(this);

        dataList= (List<VirtualtocptBean>)this.getIntent().getSerializableExtra("typeList");

        listAdapter = new SelcetIconTypeAdapter(this, dataList);
        lv_type.setAdapter(listAdapter);

        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                backTo(arg2);
            }
        });
    }

    public void backTo(int position){
        Intent intent = new Intent();
        intent.putExtra("position", position);
        SelectIconTypeActivity.this.setResult(RESULT_OK, intent);
        SelectIconTypeActivity.this.finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
        }
    }
}
