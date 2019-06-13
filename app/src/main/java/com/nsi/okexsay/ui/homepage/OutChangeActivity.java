package com.nsi.okexsay.ui.homepage;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.utils.QrCodeCreateUtil;
import com.nsi.okexsay.wiget.LoadingDialog;

public class OutChangeActivity extends BaseActivity implements View.OnClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    ImageView homeBtnBack;
    TextView homeTextTitle,homeTextRight;
    private ImageView imgInQcode;
    private Button btnCopy;
    private TextView textInAddress;

    private String apkUrl = "https://zhongjian69970.oss-cn-hongkong.aliyuncs.com/upload/APP/Integral.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_change);
        initView();
        getImage();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeTextRight = findViewById(R.id.home_text_right);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextRight.setVisibility(View.VISIBLE);
        homeTextTitle.setText("CPT充值");
        homeTextRight.setText("消费明细");
        imgInQcode = findViewById(R.id.img_in_qrcode);
        btnCopy = findViewById(R.id.btn_copy);
        textInAddress = findViewById(R.id.text_in_address);

        btnCopy.setOnClickListener(this);
        homeTextRight.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    private void getImage(){
        Bitmap qrCodeBitmap = QrCodeCreateUtil.createQRCodeBitmap(
                apkUrl, 480, 480);
        imgInQcode.setImageBitmap(qrCodeBitmap);
    }

    private void copyAddress(){
        ClipboardManager cmb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(textInAddress.getText());
        showShortToast("地址已复制");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.home_text_right:
                showShortToast("消费明细");
                break;
            case R.id.btn_copy:
                copyAddress();
                break;
        }
    }
}
