package com.nsi.okexsay.ui.homepage;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.HomeDatasBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.HomePageFragment_in;
import com.nsi.okexsay.mvp.HomePageFragment_p;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.personcenter.IDCardActivity;
import com.nsi.okexsay.wiget.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class HomePageFragment extends BaseFragment
        implements View.OnClickListener, HomePageFragment_in.View {

    private View rootView;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;

    private boolean isPost = false;
    private boolean isPass = false;
    private int RESULT_OK = 0xA1;

    private TextView idUserPhone;
    private TextView idWelcomText;
    private LinearLayout idDuihuan;
    private LinearLayout idFuqian;
    private LinearLayout idShouqian;
    private LinearLayout idShoujichongzhi;
    private LinearLayout idShuifei;
    private LinearLayout idNuanqifei;
    private LinearLayout idRanqifei;
    private LinearLayout idDianfei;
    private LinearLayout idWeizhang;
    private LinearLayout idZhangdan;
    private LinearLayout idFenxiao;
    private LinearLayout idShangcheng;
    private LinearLayout idGengduo;
    private ImageView banner1;
    private ImageView banner2;
    private ImageView banner3;

    private HomePageFragment_p present;
    private int REQUEST_CODE = 0x01;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_health_monitor, null);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
        present = new HomePageFragment_p(getContext(), this);
        dialog = new LoadingDialog(getActivity());
        String isPass_str = sp.getString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "");
        String phone_numer = sp.getString(SharedPreferencesKeys.PHONE, "");
        if (isPass_str.equals("true")) {
            isPass = true;
        } else {
            isPass = false;
        }

        idUserPhone = (TextView) rootView.findViewById(R.id.id_user_phone);
        idUserPhone.setText(phone_numer);
        idWelcomText = (TextView) rootView.findViewById(R.id.id_welcom_text);

        long time = System.currentTimeMillis();
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("HH");
        int h = Integer.parseInt(sf.format(d));
        if (h > 11) {
            idWelcomText.setText("下午好");
        }else {
            idWelcomText.setText("上午好");
        }


        idDuihuan = (LinearLayout) rootView.findViewById(R.id.id_duihuan);
        idFuqian = (LinearLayout) rootView.findViewById(R.id.id_fuqian);
        idShouqian = (LinearLayout) rootView.findViewById(R.id.id_shouqian);

        idShoujichongzhi = (LinearLayout) rootView.findViewById(R.id.id_shoujichongzhi);
        idShuifei = (LinearLayout) rootView.findViewById(R.id.id_shuifei);
        idNuanqifei = (LinearLayout) rootView.findViewById(R.id.id_nuanqifei);
        idRanqifei = (LinearLayout) rootView.findViewById(R.id.id_ranqifei);
        idDianfei = (LinearLayout) rootView.findViewById(R.id.id_dianfei);
        idWeizhang = (LinearLayout) rootView.findViewById(R.id.id_weizhang);
        idZhangdan = (LinearLayout) rootView.findViewById(R.id.id_zhangdan);
        idFenxiao = (LinearLayout) rootView.findViewById(R.id.id_fenxiao);
        idShangcheng = (LinearLayout) rootView.findViewById(R.id.id_shangcheng);
        idGengduo = (LinearLayout) rootView.findViewById(R.id.id_gengduo);
        banner1 = (ImageView) rootView.findViewById(R.id.banner1);
        banner2 = (ImageView) rootView.findViewById(R.id.banner2);
        banner3 = (ImageView) rootView.findViewById(R.id.banner3);

        idDuihuan.setOnClickListener(this);
        idFuqian.setOnClickListener(this);
        idShouqian.setOnClickListener(this);
        idShoujichongzhi.setOnClickListener(this);
        idShuifei.setOnClickListener(this);
        idNuanqifei.setOnClickListener(this);
        idRanqifei.setOnClickListener(this);
        idDianfei.setOnClickListener(this);
        idWeizhang.setOnClickListener(this);
        idZhangdan.setOnClickListener(this);
        idFenxiao.setOnClickListener(this);
        idShangcheng.setOnClickListener(this);
        idGengduo.setOnClickListener(this);
        banner1.setOnClickListener(this);
        banner2.setOnClickListener(this);
        banner3.setOnClickListener(this);

        //获取数据
        present.load_home_data();
    }


    public void valiteAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("提示");
        dialog.setMessage("您还没有进行实名认证，请立即认证身份");
        dialog.setPositiveButton("去认证",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(SharedPreferencesKeys.IS_LOGIN, false);
                        editor.commit();
                        Intent intent = new Intent(getActivity(), IDCardActivity.class);
                        startActivity(intent);
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        dialog.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_shouqian:
                //收钱
                if (isPass) {
                    Intent intentOutChange = new Intent(getActivity(), CollectIconActivity.class);
                    startActivity(intentOutChange);
                } else {
                    valiteAlert();
                }
                break;
            case R.id.id_fuqian:
                //付钱
                if (isPass) {
                    if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1000);
                    } else {
                        Intent intent = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                    break;
//                    Intent intentInChange = new Intent(getActivity(), PayIconActivity.class);
//                    startActivity(intentInChange);
                } else {
                    valiteAlert();
                }
                break;

            case R.id.id_duihuan:
                //兑换
                if (isPass) {
                    Intent intentFind = new Intent(getActivity(), ExchangeActivity.class);
                    intentFind.putExtra("fromFlag", "HOME");
                    startActivity(intentFind);
                } else {
                    valiteAlert();
                }
                break;

            case R.id.id_shoujichongzhi:
                //手机充值

                break;

            case R.id.id_shuifei:
                //水费

                break;

            case R.id.id_nuanqifei:
                //暖气费

                break;

            case R.id.id_ranqifei:
                //电费

                break;

            case R.id.id_dianfei:
                //手机充值

                break;

            case R.id.id_weizhang:
                //违章

                break;

            case R.id.id_zhangdan:
                //账单

                break;
            case R.id.id_fenxiao:
                //分销

                break;
            case R.id.id_shangcheng:
                //商城

                break;
            case R.id.id_gengduo:
                //更多

                break;

            case R.id.banner1:
                //媒体报道
                Intent intent_m = new Intent(getActivity(), HomeNewsActivity.class);
                intent_m.putExtra("title", "媒体报道");
                intent_m.putExtra("type", "3");
                startActivity(intent_m);
                break;

            case R.id.banner2:
                //热门话题
                Intent intent_r = new Intent(getActivity(), HomeNewsActivity.class);
                intent_r.putExtra("title", "热门话题");
                intent_r.putExtra("type", "2");
                startActivity(intent_r);
                break;

            case R.id.banner3:
                //官方新闻
                Intent intent_g = new Intent(getActivity(), HomeNewsActivity.class);
                intent_g.putExtra("title", "官方新闻");
                intent_g.putExtra("type", "1");
                startActivity(intent_g);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            Log.d("scanResult======",scanResult);
            Intent intentInChange = new Intent(getActivity(), PayIconActivity.class);
            intentInChange.putExtra("scanResult",scanResult);
            startActivity(intentInChange);
        }//RESULT_OK = -1
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                ToastUtils.showToast(ChooseRiceActivity.this, "相机权限已申请");
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            } else {
                showShortToast("相机权限已被禁止,请在设置中打开");
            }
        }
    }
    /**
     * 设置数据
     */
    @Override
    public void init_data(HomeDatasBean homeDatasBean) {

    }
}
