package com.nsi.okexsay.ui.personcenter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.R;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.utils.DateUtils;
import com.nsi.okexsay.utils.FileUtils;
import com.nsi.okexsay.wiget.ActionSheetDialog;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

import static com.nsi.okexsay.utils.ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA;

public class MyInfo extends BaseActivity {

    private Button btn_back;
    private int CAMERA_REQUEST_CODE = 13;
    private int WRITE_REQUEST_CODE = 14;
    private String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
    }


    private void initView() {
        btn_back = (Button) findViewById(R.id.btn);
        btn_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                    }
                }
        );
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
                                    if( ContextCompat.checkSelfPermission(MyInfo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            != PackageManager.PERMISSION_GRANTED ){
                                        ActivityCompat.requestPermissions(MyInfo.this,new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_REQUEST_CODE);
                                        return;
                                    }else{
//                                        new UploadUserIcon(this)
//                                                .startImagePick();
                                        Crop.pickImage(MyInfo.this);
                                    }
                                } else {
//                                    new UploadUserIcon(this)
//                                            .startImagePick();
                                    Crop.pickImage(MyInfo.this);
                                }

                            }
                        })
                .addSheetItem("拍照上传",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {

                            @Override
                            public void onClick(int which) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    if( ContextCompat.checkSelfPermission(MyInfo.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            != PackageManager.PERMISSION_GRANTED){
                                        ActivityCompat.requestPermissions(MyInfo.this,new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_REQUEST_CODE);
                                        return;
                                    } else{
                                        if(ContextCompat.checkSelfPermission(MyInfo.this, Manifest.permission.CAMERA)
                                                != PackageManager.PERMISSION_GRANTED ){
                                            ActivityCompat.requestPermissions(MyInfo.this,new String[]
                                                    {Manifest.permission.CAMERA},CAMERA_REQUEST_CODE);
                                            return;
                                        }else
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
        Log.d("Log","takePhoto=====");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        currentTime = String.valueOf(DateUtils.getCurrentTime());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.getSDImageUri(this, currentTime));
        try {
            startActivityForResult(intent,REQUEST_CODE_GETIMAGE_BYCAMERA);
        } catch (Exception e) {
            Log.d("Log","e=====",e);
            e.printStackTrace();
        }
    }
    private void beginCrop(Uri source) {
        Log.d("source=====",source.toString());
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("resultCode888888=====",resultCode+"");

//        personDataName.setText(sp.getString(SharedPreferencesKeys.NAME, ""));
        if (requestCode == 1 && resultCode == 100) {  //修改电话号码
//            String phone = data.getStringExtra("phone");
//            personDataPhone.setText(phone);
            Log.d("resultCode999=====",resultCode+"");
        } else {
            if (resultCode == RESULT_OK) {
                Log.d("resultCode=====",resultCode+"");
                if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK && data != null) {
                    Log.d("resultCode0000=====",resultCode+"");
                    beginCrop(data.getData());
                } else if (requestCode == Crop.REQUEST_CROP && data != null) {
                    Log.d("resultCode11111=====",resultCode+"");
                    handleCrop(resultCode, data);
                } else if (requestCode == REQUEST_CODE_GETIMAGE_BYCAMERA) {
                    Log.d("resultCode222=====",resultCode+"");
                    beginCrop(FileUtils.getSDImageUri(this, currentTime));
                }
            }
        }
    }

    private void handleCrop(int resultCode, Intent data) {
        Log.d("resultCode11111=====",resultCode+"");
        if (resultCode == RESULT_OK && data != null) {
            Uri output = Crop.getOutput(data);
            Log.d("output=====",output.toString());
            Bitmap bitmap = FileUtils.getImage(output.getPath());
            showShortToast("output.getPath()====" +output.getPath());
            updateImg(bitmap);
        } else if (resultCode == Crop.RESULT_ERROR) {
        }
    }

    private void uploadUserIcon( File uri) {
        RequestParams params =  new RequestParams();
        params.put("filedata",new File(uri.getPath()));
        showShortToast("param====" +params.toString());
        HttpRequest.post(StaticField.GET_INTERNATIONALS, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                Log.d("apiResponse========",apiResponse.toString());
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                int resultCode = jsonObject.getInteger("resultCode");
                if (resultCode == 0) {
                    showShortToast("头像上传成功");
                } else {
                    String resultMsg = jsonObject.getString("resultMsg");
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showShortToast(msg);
            }
        });
    }

    private void updateImg(Bitmap bitmap) {
        Log.d("updateImg====",bitmap.toString());
        String currentTime = String.valueOf(DateUtils.getCurrentTime());
        String cacheDir = getCacheDir().getPath();
        FileUtils.writrePiic(cacheDir, currentTime + ".png", bitmap, 100);
        File file = new File(cacheDir + File.separator + currentTime + ".png");
        Log.d("file====",file.toString());
        uploadUserIcon(file);

    }
    

}
