package com.nsi.okexsay.ui.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.AddZhiFuBaoWeiXin_in;
import com.nsi.okexsay.mvp.AddZhiFuBaoWeiXin_p;
import com.nsi.okexsay.ui.base.BaseActivity;

import java.io.File;

import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 添加微信/支付宝收款方式
 */
public class AddZhiFuBaoWeiXinActivity extends BaseActivity
        implements View.OnClickListener, AddZhiFuBaoWeiXin_in.View {

    private RelativeLayout rlTitle;
    private ImageView btnBack;
    private TextView title;
    private TextView idTypeText;
    private EditText idUserName;
    private EditText idZhifubaoNumber;
    private ImageView idQrCodeImg;
    private EditText idPhoneCode;
    private TextView idGetPhoneCode;
    private Button idSubmit;


    private boolean is_choice_img = false;
    private String image_path = "";

    private AppContext appContext;
    private SharedPreferences sp;
    private String user_token, fuserId, stringnumber;
    private AddZhiFuBaoWeiXin_p present;
    private String pheonestr;
    //1、支付宝  2、微信
    private String type;
    private String sessionidstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zihfubao_weixin);

        type = getIntent().getStringExtra("type");

        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        user_token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        fuserId = sp.getString(SharedPreferencesKeys.FID, "");
        pheonestr = sp.getString(SharedPreferencesKeys.PHONE, "");
        stringnumber = sp.getString(SharedPreferencesKeys.REAL_NAME, "");
        present = new AddZhiFuBaoWeiXin_p(this, this);
        init();
    }

    private void init() {

        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        btnBack = (ImageView) findViewById(R.id.btn_back);
        title = (TextView) findViewById(R.id.title);
        idTypeText = (TextView) findViewById(R.id.id_type_text);
        idUserName = (EditText) findViewById(R.id.id_user_name);
        idZhifubaoNumber = (EditText) findViewById(R.id.id_zhifubao_number);
        idQrCodeImg = (ImageView) findViewById(R.id.id_qr_code_img);
        idPhoneCode = (EditText) findViewById(R.id.id_phone_code);
        idGetPhoneCode = (TextView) findViewById(R.id.id_get_phone_code);
        idSubmit = (Button) findViewById(R.id.id_submit);

        btnBack.setOnClickListener(this);
        idQrCodeImg.setOnClickListener(this);
        idGetPhoneCode.setOnClickListener(this);
        idSubmit.setOnClickListener(this);

        if (type.equals("1")) {
            idTypeText.setText("支付宝");
        } else {
            idTypeText.setText("微信");
        }
        idUserName.setText(stringnumber);
        get_Merchant();//验证是否是商户
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.id_qr_code_img:
                //选择二维码
                choosePhoto();
                break;

            case R.id.id_get_phone_code:
                //获取验证码
//                if (idZhifubaoNumber.getText().toString().trim().length() < 11) {
                //  Toast.makeText(appContext, "帐号输入不正确", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                req_get_phone_code();
                break;

            case R.id.id_submit:
                //提交
                if (!is_choice_img) {
                    Toast.makeText(appContext, "请先选择收款二维码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (idZhifubaoNumber.getText().toString().trim().length() < 6) {
                    showLongToast("请正确填写验证码！");
                    return;
                }
                req_submit();
                break;
        }
    }


    private void req_get_phone_code() {
        String code_type = "";
        if (type.equals("1")) {
            //支付宝
            code_type = "16";
        } else {
            if (type.equals("2")) {
                code_type = "17";
            }
        }
        RequestParams params = new RequestParams();
        //短信类型
        params.put("type", code_type);
        params.put("areaCode", "86");
        params.put("phone", pheonestr);
        params.put("vcode", 123456);
        params.put("fuserId", fuserId);//用户ID);
        present.get_phone_code(params);
    }

    private void req_submit() {
        Shoudialog();
        RequestParams params = new RequestParams();
        params.put("fName", idUserName.getText().toString().trim());
        params.put("filedata1", new File(image_path));
        params.put("accountnumber", idZhifubaoNumber.getText().toString().trim());
        params.put("fstate", type);
        params.put("phoneCode", idZhifubaoNumber.getText().toString().trim());
        params.put("sessionid", sessionidstr);
        params.put("token", user_token);
        params.put("fuserId", fuserId);//用户ID);
        present.req_submit(params);
    }

    private void get_Merchant() {
        Shoudialog();
        RequestParams params = new RequestParams();
        params.put("token", user_token);
        params.put("fuserId", fuserId);//用户ID);
        present.get_is_Merchant(params);
    }

    /**
     * 打开相册
     */
    public void choosePhoto() {
        //这是打开系统默认的相册(就是你系统怎么分类,就怎么显示,首先展示分类列表)
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                //路径
                image_path = picturePath;
                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                idQrCodeImg.setImageBitmap(bitmap);
                is_choice_img = true;

            } catch (Exception e) {
                //"上传失败");
            }
        }
    }

    @Override
    public void set_get_phone_code_status(String status) {
        DissDialog();
        JSONObject jsonObject = JSON.parseObject(status);
        sessionidstr = jsonObject.getString("sessionid");
        showLongToast(jsonObject.getString("msg"));
    }

    /**
     * 获取验证码返会
     *
     * @param params
     */
    @Override
    public void get_phone_code(RequestParams params) {
        DissDialog();

    }

    @Override
    public void set_Merchant_code(String msg) {
        DissDialog();
        JSONObject jsonObject = JSON.parseObject(msg);
        showLongToast(jsonObject.getString("msg"));
    }

    /**
     * 提交返回
     *
     * @param status
     */
    @Override
    public void set_submit_status(String status) {
        DissDialog();
        JSONObject jsonObject = JSON.parseObject(status);
        if (jsonObject.getInteger("code") == -1) {
            showLongToast(jsonObject.getString("msg"));
        } else {
            showLongToast("添加成功！");
            finish();
        }


    }
}
