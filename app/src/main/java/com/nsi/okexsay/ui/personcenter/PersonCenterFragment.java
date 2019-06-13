package com.nsi.okexsay.ui.personcenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.ui.base.BaseFragment;
import com.nsi.okexsay.ui.homepage.PersionalActivity;
import com.nsi.okexsay.ui.login.FindPasswordActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.login.RegisterActivity;
import com.nsi.okexsay.utils.DateUtils;
import com.nsi.okexsay.wiget.LoadingDialog;

public class PersonCenterFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    private TextView tv_phone, tv_uid, tv_change_user;
    private LinearLayout line_safe, line_idcard;
    private RelativeLayout rela_promote, rela_about_us, rela_settin, rela_version, rela_capital, ReProAssets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person_center, null);
        appContext = (AppContext) getActivity().getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(getActivity());
        tv_phone = rootView.findViewById(R.id.text_phone);
        tv_uid = rootView.findViewById(R.id.text_uid);
        tv_change_user = rootView.findViewById(R.id.text_change_user);
        line_safe = rootView.findViewById(R.id.line_safe);
        line_idcard = rootView.findViewById(R.id.line_idcard);
        rela_promote = rootView.findViewById(R.id.rela_promote);
        rela_about_us = rootView.findViewById(R.id.rela_about_us);
        rela_settin = rootView.findViewById(R.id.rela_setting);
        rela_version = rootView.findViewById(R.id.rela_version);
        rela_capital = rootView.findViewById(R.id.rela_capital);
        ReProAssets = rootView.findViewById(R.id.ReProAssets);
        tv_phone.setText(sp.getString(SharedPreferencesKeys.PHONE, ""));
        tv_uid.setText("UID:" + sp.getString(SharedPreferencesKeys.FID, ""));
        ReProAssets.setOnClickListener(this);
        line_safe.setOnClickListener(this);
        line_idcard.setOnClickListener(this);
        tv_change_user.setOnClickListener(this);
        rela_promote.setOnClickListener(this);
        rela_about_us.setOnClickListener(this);
        rela_settin.setOnClickListener(this);
        rela_version.setOnClickListener(this);
        rela_capital.setOnClickListener(this);
        return rootView;
    }

    public String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_change_user:
                Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.line_safe:
                Intent intentFind = new Intent(getActivity(), SafeCenterActivity.class);
                startActivity(intentFind);
                break;
            case R.id.line_idcard:
                Intent intentID = new Intent(getActivity(), IDCardActivity.class);
                startActivity(intentID);
                break;
            case R.id.rela_promote:
                Intent intentPromote = new Intent(getActivity(), MyPromoteActivity.class);
                startActivity(intentPromote);
                break;
            case R.id.rela_about_us:
                Intent intentAbout = new Intent(getActivity(), VersionCodeActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.rela_setting:
                Intent intentSetting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.ReProAssets:
                Intent intentactivity = new Intent(getActivity(), PersionalActivity.class);
                startActivity(intentactivity);
                break;
            case R.id.rela_capital:
                String isPost_str = sp.getString(SharedPreferencesKeys.POST_REAL_VALIDATE, "");
                if (isPost_str.equals("true")) {
                    Intent AccountManagement = new Intent(getActivity(), AccountManagementActivity.class);
                    startActivity(AccountManagement);
                } else {
                    showLongToast("请申请实名制！");
                }

                break;
            case R.id.rela_version:
                showShortToast("当前版本号为：" + getVersion());
                break;
        }
    }
}
