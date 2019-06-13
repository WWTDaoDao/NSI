package com.nsi.okexsay.ui.personcenter;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.utils.ActivityShot;
import com.nsi.okexsay.utils.QrCodeCreateUtil;
import com.nsi.okexsay.wiget.ActionSheetDialog;

public class MyPromoteActivity extends BaseActivity implements OnLongClickListener{

    private AppContext appContext;
    private SharedPreferences sp;
    private LinearLayout rootView;
    private ImageView img_qrCode;
    private TextView tv_invatation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_promote);

        rootView = findViewById(R.id.root_view);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        img_qrCode = findViewById(R.id.img_qrcode);
        tv_invatation = findViewById(R.id.text_invitation);
        rootView.setOnLongClickListener(this);

        tv_invatation.setText("邀请码："+sp.getString(SharedPreferencesKeys.FID,""));

        getImage();

    }

    private void getImage(){
        Bitmap qrCodeBitmap = QrCodeCreateUtil.createQRCodeBitmap(
                StaticField.PROMOTE_URL, 480, 480);
        img_qrCode.setImageBitmap(qrCodeBitmap);
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.root_view:
                showDialog();
                break;
        }
        return true;
    }

    private void showDialog() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("保存", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ActivityShot activityShot = new ActivityShot();
                                activityShot.getActivityShot(MyPromoteActivity.this);
                                showShortToast("图片已保存！");
                            }
                        }).show();
    }

}
