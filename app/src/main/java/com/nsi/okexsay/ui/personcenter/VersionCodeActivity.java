package com.nsi.okexsay.ui.personcenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;

public class VersionCodeActivity extends BaseActivity {

    private TextView title,name;
    private ImageView mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        title = findViewById(R.id.home_text_title);
        title.setText("关于我们");
        name = findViewById(R.id.name);
        name.setText(getString(R.string.app_name));
        mBtnBack = findViewById(R.id.home_btn_back);
        mBtnBack.setVisibility(View.VISIBLE);
        mBtnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        finish();
                    }
                });
    }

    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.app_name) + ":" +version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
