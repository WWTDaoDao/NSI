package com.nsi.okexsay.ui.personcenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.AccountManagementBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.adapter.AccountManagementAdapter;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.AccountManagement_in;
import com.nsi.okexsay.mvp.AccountManagement_p;
import com.nsi.okexsay.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


//账号管理
public class AccountManagementActivity extends BaseActivity
        implements AccountManagement_in.View,
        AccountManagementAdapter.OnClick {

    private RecyclerView idRecyclerview;
    private AccountManagementAdapter adapter;
    private List<AccountManagementBean> list_data = new ArrayList<>();
    private AppContext appContext;
    private SharedPreferences sp;
    private TextView tv_title;
    private ImageView homeBtnBack;

    private RelativeLayout addpayment;
    private AccountManagement_p present;

    //item_accuntman
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagementactivity);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        present = new AccountManagement_p(this, this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Shoudialog();
        list_data.clear();
        String token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        String fuserId = sp.getString(SharedPreferencesKeys.FID, "");
        present.load_bank_data(token, fuserId);
        present.load_weixin_data(token, fuserId);
        present.load_zhifubao_data(token, fuserId);
    }

    private void init() {
        tv_title = findViewById(R.id.home_text_title);
        homeBtnBack = (ImageView) findViewById(R.id.home_btn_back);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        addpayment = findViewById(R.id.addpayment);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_devider_red));
        idRecyclerview.addItemDecoration(dividerItemDecoration);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        idRecyclerview.setLayoutManager(layoutManager);
        tv_title.setText("资金账号");
        addpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountManagementActivity.this, AddPayment.class));
            }
        });
    }


    @Override
    public void set_bank_datas(List<AccountManagementBean> list) {
        DissDialog();
        list_data.addAll(list);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new AccountManagementAdapter(this, list_data);
            adapter.setOnClick(this);
            idRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void set_zhifubao_datas(List<AccountManagementBean> list) {
        DissDialog();
        list_data.addAll(list);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new AccountManagementAdapter(this, list_data);
            adapter.setOnClick(this);
            idRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void set_weixin_datas(List<AccountManagementBean> list) {
        DissDialog();
        list_data.addAll(list);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new AccountManagementAdapter(this, list_data);
            adapter.setOnClick(this);
            idRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void dell_back(int status, String res_data) {
        if (status == 1) {
            String index = adapter.index;
            for (int i=0;i<list_data.size();i++){
                if (list_data.get(i).getNumber().equals(index)){
                    list_data.remove(i);
                    idRecyclerview.removeItemDecorationAt(i);
                }
            }

        }
    }

    /**
     * 删除
     *
     * @param accountManagementBean
     */
    @Override
    public void delete(final AccountManagementBean accountManagementBean) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("删除");
        dialog.setMessage("确认要删除");
        dialog.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        delete_item(accountManagementBean);
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
        dialog.create();
        dialog.show();
    }

    private void delete_item(AccountManagementBean accountManagementBean) {
        String token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        String fuserId = sp.getString(SharedPreferencesKeys.FID, "");
        if (accountManagementBean.getType() == 123) {
            //银行卡
            present.req_delete_bank(token, fuserId, accountManagementBean.getId());
        } else {
            present.req_delete_zhifubao_weixin(token, fuserId, accountManagementBean.getId());
        }
    }

    @Override
    public void click(AccountManagementBean accountManagementBean) {

    }

    @Override
    public void add() {
        startActivity(new Intent(this, AddPayment.class));
    }
}
