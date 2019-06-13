package com.nsi.okexsay.ui.lawpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.view.CustomViewPager;
import com.google.zxing.view.JDTabLayout;
import com.library.OnPopupItemClickListener;
import com.library.PopupView;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.EventOrderBean;
import com.nsi.okexsay.Beans.Internationals;
import com.nsi.okexsay.Beans.JurisdictionBean;
import com.nsi.okexsay.Beans.TypeIdBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.OnTabSelectedListener;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.EntrustFragment;
import com.nsi.okexsay.ui.homepage.OperationFragment;
import com.nsi.okexsay.ui.homepage.OrderFragment;
import com.nsi.okexsay.ui.homepage.PurchaseFragment;
import com.nsi.okexsay.ui.homepage.SellFragment;
import com.nsi.okexsay.ui.homepage.adapter.FragmentViewPagerAdapter;
import com.nsi.okexsay.ui.homepage.adapter.ViewPagerAdapter;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.ui.personcenter.SettingActivity;
import com.nsi.okexsay.utils.JsonUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 法币
 */
public class LawPageFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private PopupView Classification;//货币归类
    private PopupView type;//货币种类
    private List<CharSequence> ClassificationData;
    private List<CharSequence> typeData;
    private int pageNo = 1;
    private AppContext appContext;
    private SharedPreferences sp;
    private List<TypeIdBean> areaList;//获取类型
    private String ClassificationIndex = "1";
    private String typeDataId = "";
    private PurchaseFragment purchaseFragment;
    private SellFragment sellFragment;
    private EntrustFragment entrustFragment;
    private OperationFragment operationFragment;
    private OrderFragment orderFragment;
    private ArrayList<Fragment> fragmentList;
    private ViewPager customViewPager;
    private FragmentViewPagerAdapter adapter;
    private JDTabLayout jdTabLayout;
    private String[] dataTitle = {"购买", "出售", "委托单", "操作", "订单"};
    private int index = 0;
    private TextView tv_title;
    private LinearLayout ling;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_doctor, null);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
        areaList = new ArrayList<>();//获取类型
        Shoudialog();
        getAdvertisement(pageNo, "1", "0", "");
        TypeIdBean typeIdBean = new TypeIdBean();
        typeIdBean.setCurrencySymbol("全部");
        typeIdBean.setId(-1);
        typeIdBean.setAreaName("");
        typeIdBean.setRemarks("");
        typeIdBean.setLegalName("");
        areaList.add(typeIdBean);
        addData();
        setOnListener();
    }


    private void initViews(View rootView) {
        Classification = rootView.findViewById(R.id.popup_Classification);
        type = rootView.findViewById(R.id.popup_type);
        customViewPager = rootView.findViewById(R.id.viewPager);
        jdTabLayout = rootView.findViewById(R.id.tabLayout);
        tv_title = rootView.findViewById(R.id.home_text_title);
        ling = rootView.findViewById(R.id.ling);
        tv_title.setText("法币");
        tv_title.setVisibility(View.GONE);
        ling.setVisibility(View.INVISIBLE);
    }

    private void addData() {
        typeData = new ArrayList<>();
        ClassificationData = new ArrayList<>();
        ClassificationData.add("USDT");
        ClassificationData.add("CPT");
        Classification.setItemsFromList(ClassificationData);
        Classification.setPostion(0);
        purchaseFragment = new PurchaseFragment();
        sellFragment = new SellFragment();
        entrustFragment = new EntrustFragment();
        operationFragment = new OperationFragment();
        orderFragment = new OrderFragment();
        fragmentList = new ArrayList();
        fragmentList.add(purchaseFragment);
        fragmentList.add(sellFragment);
        fragmentList.add(entrustFragment);
        fragmentList.add(operationFragment);
        fragmentList.add(orderFragment);
        jdTabLayout.setTabTitles(dataTitle).setSelectedIndex(0).create();
        adapter = new FragmentViewPagerAdapter(getChildFragmentManager(), customViewPager, fragmentList);
        customViewPager.setAdapter(adapter);
        customViewPager.setCurrentItem(0);
        Classification.setBackgroundDrawable(null);
        type.setBackgroundDrawable(null);
    }

    private void setOnListener() {
        jdTabLayout.setTabTitles(dataTitle).setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                customViewPager.setCurrentItem(index);
                if (index == 3) {
                    EventBus.getDefault().postSticky(new JurisdictionBean("检查"));
                } else if (index == 4) {
                    EventBus.getDefault().postSticky(new EventOrderBean("检查"));
                }
            }
        }).create();
        //法币归类
        Classification.setOnItemClickListener(new OnPopupItemClickListener() {
            @Override
            public void onItemClickListener(int id, int position, CharSequence title) {
                if (position == 0) {
                    ClassificationIndex = "1";
                } else {
                    ClassificationIndex = "0";
                }
                switch (index) {
                    case 0:
                        purchaseFragment.setType(typeDataId, ClassificationIndex);
                        break;
                    case 1:
                        sellFragment.setType(typeDataId, ClassificationIndex);
                        break;
                    case 2:
                        //   entrustFragment.setType(ClassificationIndex);
                        break;
                    case 3:

                        break;
                    case 4:
                        break;
                }
            }
        });
        //货币类型
        type.setOnItemClickListener(new OnPopupItemClickListener() {
            @Override
            public void onItemClickListener(int id, int position, CharSequence title) {
                typeDataId = areaList.get(position).getId() + "";
                if (typeDataId.equals("-1")) {
                    typeDataId = "";
                }
                switch (index) {
                    case 0:
                        purchaseFragment.setType(typeDataId, ClassificationIndex);
                        break;
                    case 1:
                        sellFragment.setType(typeDataId, ClassificationIndex);
                        break;
                    case 2:
                        //      entrustFragment.setType(typeDataId, ClassificationIndex);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                jdTabLayout.onPageScrolled(i, v, i1);

            }

            @Override
            public void onPageSelected(int i) {
                jdTabLayout.onPageSelected(i);
                index = i;
                if (index == 0) {//选项回执
                    if (purchaseFragment.Type.equals("0")) {
                        Classification.setPostion(1);
                    } else {
                        Classification.setPostion(0);
                    }
                } else if (index == 1) {
                    if (sellFragment.Type.equals("0")) {
                        Classification.setPostion(1);
                    } else {
                        Classification.setPostion(0);
                    }
                }
                if (index == 3) {
                    EventBus.getDefault().postSticky(new JurisdictionBean("检查"));
                } else if (index == 4) {
                    EventBus.getDefault().postSticky(new EventOrderBean("检查"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                jdTabLayout.onPageScrollStateChanged(i);
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    /**
     * 获取广告列表
     */
    private void getAdvertisement(int mpageNo, String ClassificationStr, final String transactionStr, final String typeId) {
        RequestParams params = new RequestParams();
        params.put("numPerPage", DEFAULT_PAGE_SIZE);//每页数量
        params.put("currentPage", mpageNo);//当前页码
        params.put("cptOrUsdt", ClassificationStr);//Cpt：0，usdt：1
        params.put("tradeType", transactionStr);//购买：0，出售：1
        params.put("fbTypeId", typeId);//法币类型id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.ADVERTISEMENT, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                areaList.addAll(JsonUtil.parseList(jsonObject.getString("fbList"), TypeIdBean.class));
                for (int i = 0; i < areaList.size(); i++) {
                    typeData.add(areaList.get(i).getCurrencySymbol());
                }
                type.setItemsFromList(typeData);
                type.setPostion(0);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }
}
