package com.nsi.okexsay.ui.personcenter;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.UserBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.utils.DateUtils;
import com.nsi.okexsay.utils.FileUtils;
import com.nsi.okexsay.utils.IDCardUtils;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.wiget.ActionSheetDialog;
import com.nsi.okexsay.wiget.LoadingDialog;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

import static com.nsi.okexsay.utils.ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA;

public class IDCardActivity extends BaseActivity implements View.OnClickListener {

    private ScrollView scroll_root;
    private LinearLayout line_id_card1, line_id_card2, line_id_card3, line_pass;
    private Button btn_save, btn_show;
    private ImageView img_id_card1, img_id_card2, img_id_card3;
    private EditText edit_id_card, edit_real_name;
    private TextView tv_area, tv_name, tv_card_number;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    private ImageView homeBtnBack;
    private TextView homeTextTitle;
    private int CAMERA_REQUEST_CODE = 13;
    private int WRITE_REQUEST_CODE = 14;
    private String currentTime;
    private int imageFlag = 0;
    private File[] files = new File[3];

    private boolean isPost = false;
    private boolean isPass = false;
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);
        initView();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
         token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        String isPost_str = sp.getString(SharedPreferencesKeys.POST_REAL_VALIDATE, "");
        if (isPost_str.equals("true")) {
            isPost = true;
        } else {
            isPost = false;
        }
        String isPass_str = sp.getString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "");
        if (isPass_str.equals("true")) {
            isPass = true;
        } else {
            isPass = false;
        }
        dialog = new LoadingDialog(this);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextTitle.setText("实名认证");
        scroll_root = findViewById(R.id.scroll_root);
        edit_id_card = findViewById(R.id.edit_card_number);
        edit_real_name = findViewById(R.id.edit_real_name);
        line_id_card1 = findViewById(R.id.line_id_card1);
        line_id_card2 = findViewById(R.id.line_id_card2);
        line_id_card3 = findViewById(R.id.line_id_card3);
        btn_save = findViewById(R.id.btn_save);
        img_id_card1 = findViewById(R.id.img_id_card1);
        img_id_card2 = findViewById(R.id.img_id_card2);
        img_id_card3 = findViewById(R.id.img_id_card3);

        // 身份证通过认证之后显示的内容
        tv_area = findViewById(R.id.text_area);
        tv_name = findViewById(R.id.text_name);
        tv_card_number = findViewById(R.id.text_card_number);
        line_pass = findViewById(R.id.line_pass);
        btn_show = findViewById(R.id.btn_show);
        tv_name.setText(sp.getString(SharedPreferencesKeys.REAL_NAME, ""));

        line_id_card1.setOnClickListener(this);
        line_id_card2.setOnClickListener(this);
        line_id_card3.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        // 根据是否提交和审核，判断view的显示和隐藏
        if (isPost) {
            tv_name.setText(sp.getString(SharedPreferencesKeys.REAL_NAME, ""));
            tv_card_number.setText(sp.getString(SharedPreferencesKeys.ID_CARD, ""));
            line_pass.setVisibility(View.VISIBLE);
            scroll_root.setVisibility(View.GONE);
            if (isPass) {
                btn_save.setVisibility(View.GONE);
            } else {
                getUserInfo();
                btn_save.setVisibility(View.VISIBLE);
            }
        } else {
            scroll_root.setVisibility(View.VISIBLE);
            line_pass.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.line_id_card1:
                imageFlag = 0;
                showDialog();
                break;
            case R.id.line_id_card2:
                imageFlag = 1;
                showDialog();
                break;
            case R.id.line_id_card3:
                imageFlag = 2;
                showDialog();
                break;
            case R.id.btn_save:
                saveIDCardInfo();
                break;
        }
    }

    private void showDialog() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("从相册中选取", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    if (ContextCompat.checkSelfPermission(IDCardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(IDCardActivity.this, new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_REQUEST_CODE);
                                        return;
                                    } else {
//                                        new UploadUserIcon(this)
//                                                .startImagePick();
                                        Crop.pickImage(IDCardActivity.this);
                                    }
                                } else {
//                                    new UploadUserIcon(this)
//                                            .startImagePick();
                                    Crop.pickImage(IDCardActivity.this);
                                }

                            }
                        })
                .addSheetItem("拍照上传",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {

                            @Override
                            public void onClick(int which) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    if (ContextCompat.checkSelfPermission(IDCardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(IDCardActivity.this, new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_REQUEST_CODE);
                                        return;
                                    } else {
                                        if (ContextCompat.checkSelfPermission(IDCardActivity.this, Manifest.permission.CAMERA)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(IDCardActivity.this, new String[]
                                                    {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                                            return;
                                        } else
//                                        origUri = new UploadUserIcon(this)
//                                                .getCameraTempFile();
////                                        Toast.makeText(this,"_拍照上传_origUri"+origUri,Toast.LENGTH_SHORT).show();
//                                        new UploadUserIcon(this)
//                                                .startActionCamera(origUri);
                                            takePhoto();
                                    }
                                } else {
//                                    origUri = new UploadUserIcon(this)
//                                            .getCameraTempFile();
////                                    Toast.makeText(this,"_拍照上传_origUri"+origUri,Toast.LENGTH_SHORT).show();
//                                    new UploadUserIcon(this)
//                                            .startActionCamera(origUri);
                                    takePhoto();
                                }
                            }
                        }).show();
    }


    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        currentTime = String.valueOf(DateUtils.getCurrentTime());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.getSDImageUri(this, currentTime));
        try {
            startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYCAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK && data != null) {
                beginCrop(data.getData());
//                    handleCrop(resultCode, data);
            } else if (requestCode == Crop.REQUEST_CROP && data != null) {
                handleCrop(resultCode, data);
            } else if (requestCode == REQUEST_CODE_GETIMAGE_BYCAMERA) {
                beginCrop(FileUtils.getSDImageUri(this, currentTime));
//                    handleCrop(resultCode, FileUtils.getSDImageUri(this, currentTime));
            }
        }
    }

    private void handleCrop(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            Uri output = Crop.getOutput(data);
            Bitmap bitmap = FileUtils.getImage(output.getPath());
            updateImg(bitmap);
        } else if (resultCode == Crop.RESULT_ERROR) {
        }
    }

    private void updateImg(Bitmap bitmap) {
        String currentTime = String.valueOf(DateUtils.getCurrentTime());
        String cacheDir = getCacheDir().getPath();
        FileUtils.writrePiic(cacheDir, currentTime + ".png", bitmap, 100);
        File file = new File(cacheDir + File.separator + currentTime + ".png");
        files[imageFlag] = file;
        if (imageFlag == 0) {
            img_id_card1.setImageBitmap(bitmap);
        } else if (imageFlag == 1) {
            img_id_card2.setImageBitmap(bitmap);
        } else {
            img_id_card3.setImageBitmap(bitmap);
        }

    }

    private void saveIDCardInfo() {
        final String realName = edit_real_name.getText().toString().trim();
        final String idCard = edit_id_card.getText().toString().trim();
        if (TextUtils.isEmpty(realName) || TextUtils.isEmpty(idCard)
                || files[0] == null || files[1] == null || files[2] == null) {
            showShortToast("各项都不能为空！");
            return;
        }
        if (!IDCardUtils.IDCardValidate(idCard)) {
            showShortToast("请输入正确的身份证号码！");
            return;
        }
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("address", "86");
        params.put("identityType", "0");
        params.put("identityNo", idCard);
        params.put("realName", realName);
        params.put("token", token);
        params.put("filedata1", new File(files[0].getPath()));
        params.put("filedata2", new File(files[1].getPath()));
        params.put("filedata3", new File(files[2].getPath()));
        HttpRequest.post(StaticField.VALIDATE_INDTITY_TEST, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(SharedPreferencesKeys.POST_REAL_VALIDATE, true);
                    editor.putString(SharedPreferencesKeys.REAL_NAME, realName);
                    editor.putString(SharedPreferencesKeys.ID_CARD, idCard);
                    editor.commit();
                    showShortToast("提交成功！");
                    finish();
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                showShortToast(msg);
            }
        });
    }

    private void getUserInfo() {
        RequestParams params = new RequestParams();
        params.put("loginName", sp.getString(SharedPreferencesKeys.LOGIN_NAME, ""));
        params.put("password", sp.getString(SharedPreferencesKeys.PASSWORD, ""));
        HttpRequest.post(StaticField.LOGIN, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    UserBean userInfo = JsonUtil.parseObject(jsonObject.getString("fuser"), UserBean.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(SharedPreferencesKeys.IS_LOGIN, true);
                    editor.putString(SharedPreferencesKeys.USER_INFO, jsonObject.getString("fuser"));
                    editor.putString(SharedPreferencesKeys.FID, userInfo.getFid());
                    editor.putString(SharedPreferencesKeys.ID_CARD, userInfo.getFidentityNo());
                    editor.putString(SharedPreferencesKeys.LOGIN_NAME, userInfo.getFloginName());
                    editor.putString(SharedPreferencesKeys.NICK_NAME, userInfo.getFnickName());
                    editor.putString(SharedPreferencesKeys.PHONE, userInfo.getFtelephone());
                    editor.putBoolean(SharedPreferencesKeys.POST_REAL_VALIDATE, userInfo.getFpostRealValidate());
                    editor.putBoolean(SharedPreferencesKeys.POST_REAL_VALIDATE, userInfo.getFpostRealValidate());
                    if (userInfo.getFhasRealValidate()) {
                        editor.putString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "true");
                    } else {
                        editor.putString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "false");
                    }
                    editor.putString(SharedPreferencesKeys.TOKEN, userInfo.getToken());
                    editor.commit();
                    tv_name.setText(sp.getString(SharedPreferencesKeys.REAL_NAME, ""));
                    tv_card_number.setText(sp.getString(SharedPreferencesKeys.ID_CARD, ""));
                } else {
                    showShortToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                showShortToast(msg);
            }
        });
    }
}
