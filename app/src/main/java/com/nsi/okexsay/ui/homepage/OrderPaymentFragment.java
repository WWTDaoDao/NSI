package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.LawPurchaseBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.adapter.LawPagePaymentAdapter;
import com.nsi.okexsay.ui.homepage.adapter.LawPageorderPaymentAdapter;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.utils.JsonUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 对方已付款
 */
public class OrderPaymentFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private AppContext appContext;
    private SharedPreferences sp;
    private int pageNo = 1;
    private int totalPage = 3;
    private List<LawPurchaseBean> areaList;
    private SmartRefreshLayout smartRefreshLayout;
    private LawPageorderPaymentAdapter adapter;
    private RecyclerView recyclerView;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.purchase_fragment, null);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
        adapter = new LawPageorderPaymentAdapter(R.layout.item_orderpem);
        recyclerView.setAdapter(adapter);
        setOnListener();
        Shoudialog();
        getProgres(pageNo);
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

    private void setOnListener() {
        smartRefreshLayout.setEnableAutoLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                Shoudialog();
                getProgres(pageNo);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                Shoudialog();
                getProgres(pageNo);
            }
        });
        //条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                LawPurchaseBean devertisementListBean= (LawPurchaseBean) adapter.getItem(position);
//                Intent intent=new Intent(getActivity(),PurchaseActivity.class);
//                intent.putExtra("fId",devertisementListBean.getFId()+"");
//                intent.putExtra("type",devertisementListBean.getC2c().getLegalCurrency().getCurrencySymbol());
//                intent.putExtra("transaction","出售");
//                startActivity(intent);
                //  Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                index = position;
                LawPurchaseBean devertisementListBean = (LawPurchaseBean) adapter.getItem(position);
                if (view.getId() == R.id.TV_payment) {
                    if (devertisementListBean.getC2c().getTradeType().equals("1")) {

                    } else {
                        Shoudialog();
                        // postConfirm(devertisementListBean.getC2c().getFId()+"",devertisementListBean.getFId()+"");
                        postConfirm(devertisementListBean.getFId() + "",position);

                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 已付款
     */
    private void getProgres(int mpageNo) {
        RequestParams params = new RequestParams();
        params.put("numPerPage", DEFAULT_PAGE_SIZE);//每页数量
        params.put("currentPage", mpageNo);//当前页码
        params.put("status", "3");//状态
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_DAJL, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                // Log.e("getProgresFragment", jsonObject.toJSONString());
                if ( pageNo== 1) {
                    totalPage = jsonObject.getInteger("maxPage");
                }
                // StaticField.IsMerchant = jsonObject.getString("shFlag");//判断是不是商户
                areaList = JsonUtil.parseList(jsonObject.getString("c2cList"), LawPurchaseBean.class);
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

    /**
     * 付款后确认订单
     */
    private void postConfirm(String orderFidString, final int pos) {
        RequestParams params = new RequestParams();
        params.put("orderFId", Integer.valueOf(orderFidString));//订单id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.YFK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                adapter.remove(pos);//刷新被删除的地方
                showLongToast("放币成功！");
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                showShortToast(msg);
            }
        });
    }

    //    /**
//     * 放币
//     */
//    private void postCoinRelease(String fIdstr,String orderIdstr) {
//        RequestParams params = new RequestParams();
//        params.put("fId", fIdstr);//广告id
//        params.put("orderId", orderIdstr);//订单ID
//        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
//        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
//        HttpRequest.post(StaticField.UPDATEDATA, params, new BaseHttpRequestCallback<ApiResponse>() {
//            @Override
//            public void onSuccess(ApiResponse apiResponse) {
//                super.onSuccess(apiResponse);
//                DissDialog();
//                addstr.remove(index);
//                adapter.notifyItemRemoved(index);//刷新被删除的地方
//                showLongToast("放币成功！");
//               JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
//               Log.e("postCoinReleaseorder", jsonObject.toJSONString());
////                // StaticField.IsMerchant = jsonObject.getString("shFlag");//判断是不是商户
////                areaList = JsonUtil.parseList(jsonObject.getString("c2cList"), LawPurchaseBean.class);
////                addstr.clear();
////                int nm=areaList.size();
////                for (int i=0;i<nm;i++) {
////                    if (areaList.get(i).getStatus().equals("3")){
////                        addstr.add(areaList.get(i));
////                    }
////                    if (i==nm-1){//检查到最后一个时候添加数据显示
////                        addData();
////                    }
////                }
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                DissDialog();
//                if (msg.equals("CODE_REE")) {
//                    showShortToast(getString(R.string.conderr));
//                } else {
//                    showShortToast(msg);
//                }
//
//            }
//        });
//    }
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
}