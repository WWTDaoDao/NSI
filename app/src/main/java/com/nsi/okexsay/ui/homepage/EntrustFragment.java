package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.Beans.DistributeMSG;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.adapter.EntrustAdapter;
import com.nsi.okexsay.ui.homepage.adapter.PurchaseAdapter;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.utils.JsonUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 委托单
 */
public class EntrustFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private AppContext appContext;
    private SharedPreferences sp;
    private EntrustAdapter adapter;
    private boolean isPass = false;
    private TextView textView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private int pageNo = 1;
    private int totalPage = 3;
    private String mtype = "1";
    private String mClassification = "1";
    private List<DevertisementListBean> areaList;
    private TextView send_entrust;
    private int index = 0;
    private TextView Sellout;//卖出
    private TextView Purchase;//买入
    private int Business = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.entrust_fragment, null);
        initViews(rootView);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!StaticField.IsMerchant.equals("1")) {//判断是不是商户
            textView.setVisibility(View.VISIBLE);
            send_entrust.setVisibility(View.GONE);
        } else {
            send_entrust.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            Shoudialog();
            getAdvertisement(pageNo, mtype, mClassification);
            adapter = new EntrustAdapter(R.layout.item_entrust);
            recyclerView.setAdapter(adapter);
            setOnListener();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //List的UI数据加载完
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(DistributeMSG messageEvent) {
        Shoudialog();
        getAdvertisement(pageNo, mtype, mClassification);
    }

    //获取相应的货币类型
//    public void setType(String type) {
//        mtype = type;
//        //  mClassification = Classification;
//        Shoudialog();
//        getAdvertisement(pageNo, mtype, mClassification);
//    }

    private void setOnListener() {
        smartRefreshLayout.setEnableAutoLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                Shoudialog();
                getAdvertisement(pageNo, mtype, mClassification);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                Shoudialog();
                getAdvertisement(pageNo, mtype, mClassification);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                index = position;
                DevertisementListBean devertisementListBean = (DevertisementListBean) adapter.getItem(position);
                if (view.getId() == R.id.Tv_diss) {
                    TextView tv = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.Tv_diss);
                    if (tv.getText().toString().equals("已撤销")) {
                        showLongToast("您已撤销");
                    } else {
                        Shoudialog();
                        getRevoke(devertisementListBean.getFId() + "");
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initViews(View rootView) {
        textView = rootView.findViewById(R.id.TV_msg);
        smartRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        recyclerView = rootView.findViewById(R.id.refreshView);
        send_entrust = rootView.findViewById(R.id.send_entrust);
        Sellout = rootView.findViewById(R.id.Sellout);
        Purchase = rootView.findViewById(R.id.Purchase);
        send_entrust.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        Purchase.setOnClickListener(this);
        Sellout.setOnClickListener(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_devider_red));
        recyclerView.addItemDecoration(dividerItemDecoration);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 获取个人广告列表
     */
    private void getAdvertisement(int mpageNo, String mtype, String ClassificationStr) {
        RequestParams params = new RequestParams();
        params.put("numPerPage", DEFAULT_PAGE_SIZE);//每页数量
        params.put("currentPage", mpageNo);//当前页码
        //  params.put("cptOrUsdt", mtype);//Cpt：0，usdt：1
        //  params.put("tradeType", ClassificationStr);//购买：0，出售：1
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TOGRMMONE, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                //Log.e("EntrustFragmentJson", jsonObject.toJSONString());
                if (totalPage == 1) {
                    totalPage = jsonObject.getInteger("maxPage");
                }
                areaList = JsonUtil.parseList(jsonObject.getString("c2cList"), DevertisementListBean.class);
                addData();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }

    /**
     * 撤销广告
     */
    private void getRevoke(String ID) {
        RequestParams params = new RequestParams();
        params.put("fId", ID);//广告ID
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_DEL, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                Log.e("EntrustRevoke", jsonObject.toJSONString());
                if (jsonObject.toJSONString().contains("code")) {
                    if (jsonObject.getString("code").equals("1")) {
                        showLongToast("撤单成功！");
                        adapter.remove(index);
                    } else {
                        showLongToast("撤单失败请稍后再试！");
                    }
                } else {
                    if (jsonObject.getString("delFlag").equals("0")) {
                        showLongToast("交易方正在操作中，无法撤销！");
                    } else if (jsonObject.getString("code").equals("1")) {
                        showLongToast("撤单成功！");
                        adapter.remove(index);
                    } else {
                        showLongToast("撤单失败请稍后再试！");
                    }

                }

            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }

    private void addData() {
        if (pageNo == 1) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.setNoMoreData(false);
            adapter.setType(Business);
            adapter.setNewData(areaList);
        } else if (pageNo > totalPage) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            adapter.setType(Business);
            adapter.addData(areaList);
            adapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_entrust:
                Intent intent = new Intent(getActivity(), ReleaseAdvertisement.class);
                startActivity(intent);
                break;
            case R.id.Purchase://买入
                pageNo = 1;
                mClassification = "0";
                Business = 1;
                Shoudialog();
                getAdvertisement(pageNo, mtype, mClassification);
                break;
            case R.id.Sellout://卖出
                pageNo = 1;
                Business = -1;
                mClassification = "1";
                Shoudialog();
                getAdvertisement(pageNo, mtype, mClassification);
                break;
        }
    }
}
