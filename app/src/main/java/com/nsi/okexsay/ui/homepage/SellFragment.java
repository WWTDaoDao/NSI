package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.adapter.PurchaseAdapter;
import com.nsi.okexsay.ui.homepage.adapter.TwoPurchaseAdapter;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.utils.JsonUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 出售
 */
public class SellFragment extends BaseFragment {
    private View rootView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private AppContext appContext;
    private SharedPreferences sp;
    private int pageNo = 1;
    private String mtype = "";
    private String mClassification = "1";
    private List<DevertisementListBean> areaList;
    private TwoPurchaseAdapter adapter;
    private int totalPage = 3;
    public String Type = "1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sell_fragment, null);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
        Shoudialog();
        Type="1";
        getAdvertisement(pageNo, "1", mtype);
        adapter = new TwoPurchaseAdapter(R.layout.item_legal_currency);
        recyclerView.setAdapter(adapter);
        setOnListener();
    }
    //获取相应的货币类型
    public void setType(String type, String Classification) {
        Type=Classification;
        mtype = type;
        mClassification = Classification;
        Shoudialog();
        getAdvertisement(pageNo, mClassification, mtype);
    }
    private void addData() {
        if (pageNo == 1) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.setNoMoreData(false);
            adapter.setNewData(areaList);
        } else if (pageNo > totalPage) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            adapter.addData(areaList);
            adapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        }

    }
    private void setOnListener() {
        smartRefreshLayout.setEnableAutoLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                Shoudialog();
                getAdvertisement(pageNo, mClassification, mtype);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                Shoudialog();
                getAdvertisement(pageNo, mClassification, mtype);;
            }
        });
        //条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (Type.equals("0")) {//Cpt：0，usdt：1
                    Type="CPT";
                } else {
                    Type="USDT";
                }
                DevertisementListBean devertisementListBean= (DevertisementListBean) adapter.getItem(position);
                Intent intent=new Intent(getActivity(),PurchaseActivity.class);
                intent.putExtra("fId",devertisementListBean.getFId()+"");
                intent.putExtra("type",Type);
                intent.putExtra("transaction","出售");
                startActivity(intent);
                //  Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViews(View rootView) {
        smartRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        recyclerView = rootView.findViewById(R.id.refreshView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_devider_red));
        recyclerView.addItemDecoration(dividerItemDecoration);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }
    /**
     * 获取广告列表
     */
    private void getAdvertisement(int mpageNo, String ClassificationStr, String typeId) {
        RequestParams params = new RequestParams();
        params.put("numPerPage", DEFAULT_PAGE_SIZE);//每页数量
        params.put("currentPage", mpageNo);//当前页码
        params.put("cptOrUsdt", ClassificationStr);//Cpt：0，usdt：1
        params.put("tradeType", "0");//购买：1，出售：0
        params.put("fbTypeId", typeId);//法币类型id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.ADVERTISEMENT, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (totalPage==1){
                    totalPage=jsonObject.getInteger("maxPage");
                }
                areaList = JsonUtil.parseList(jsonObject.getString("c2cList"), DevertisementListBean.class);
                addData();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }
}
