package com.nsi.okexsay.ui.personcenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.nsi.okexsay.AppContext;

import com.nsi.okexsay.Beans.BankType;
import com.nsi.okexsay.Beans.FaBiType;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.mvp.BankCardReceipt_in;
import com.nsi.okexsay.mvp.BankCardReceipt_m;
import com.nsi.okexsay.mvp.BankCardReceipt_p;
import com.nsi.okexsay.ui.base.BaseActivity;

import cn.finalteam.okhttpfinal.RequestParams;

//上传银行卡号
public class BankCardReceiptActivity extends BaseActivity
        implements View.OnClickListener, BankCardReceipt_in.View {

    private TextView homeTextTitle;
    private RelativeLayout rlTitle;
    private ImageView homeBtnBack;
    private ImageButton homeBtnSystemNotes;
    private TextView homeTextRight;
    private EditText openName;
    private Spinner idChoiceType;
    private Spinner id_choice_bank;//开户银行
    private EditText bankNumber;
    private EditText agenbankNumber;
    private TextView idBankAddress;
    private EditText ETbranch;
    private EditText idBankMsg;
    private LinearLayout idOpenBank;
    private LinearLayout idOpenBankAddress;

    private EditText VerificationCode;
    private TextView sendMSG;
    private TextView postMsg;
    private String Phonestr = "";
    private SharedPreferences sp;
    private String sessionidstr;
    FaBiType[] data;
    BankType[] dataBank;
    private String choice_type = "";
    private int bank_type = 1;
    private String token, fuserId;

    CityPickerView mPicker;
    //开户行
    private String openBankType = "";
    private String stringnumber;
    private BankCardReceipt_p present;
    private AppContext appContext;
    private int currency_index = 1;//货币id
    private boolean isshagnhu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bankcardreceiptactivity);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        token = sp.getString(SharedPreferencesKeys.TOKEN, "");
        fuserId = sp.getString(SharedPreferencesKeys.FID, "");
        stringnumber = sp.getString(SharedPreferencesKeys.REAL_NAME, "");
        Phonestr = sp.getString(SharedPreferencesKeys.PHONE, "");
        //申明对象
        mPicker = new CityPickerView();
        mPicker.init(this);

        present = new BankCardReceipt_p(this, this);

        init();
        get_Merchant();
    }

    private void init() {
        homeTextTitle = (TextView) findViewById(R.id.home_text_title);
        homeTextTitle.setText("选择收款方式");

        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        homeBtnBack = (ImageView) findViewById(R.id.home_btn_back);
        homeBtnSystemNotes = (ImageButton) findViewById(R.id.home_btn_system_notes);
        homeTextRight = (TextView) findViewById(R.id.home_text_right);
        openName = (EditText) findViewById(R.id.open_name);
        idChoiceType = (Spinner) findViewById(R.id.id_choice_type);
        bankNumber = (EditText) findViewById(R.id.bankNumber);
        agenbankNumber = (EditText) findViewById(R.id.agenbankNumber);
        id_choice_bank = findViewById(R.id.id_choice_bank);
        idBankAddress = (TextView) findViewById(R.id.id_bank_address);

        idOpenBank = (LinearLayout) findViewById(R.id.id_open_bank);
        idOpenBankAddress = (LinearLayout) findViewById(R.id.id_open_bank_address);
        ETbranch = (EditText) findViewById(R.id.ETbranch);
        //开户行信息
        idBankMsg = (EditText) findViewById(R.id.id_bank_msg);

        VerificationCode = (EditText) findViewById(R.id.VerificationCode);
        sendMSG = (TextView) findViewById(R.id.sendMSG);
        postMsg = (TextView) findViewById(R.id.postMsg);

        openName.setText(stringnumber);
        dataBank = BankType.values();
        data = FaBiType.values();
        choice_type = data[0].toString();
        bank_type = 1;
        id_choice_bank.setAdapter(new ArrayAdapter<BankType>(this, android.R.layout.simple_list_item_1, dataBank));
        idChoiceType.setAdapter(new ArrayAdapter<FaBiType>(this, android.R.layout.simple_list_item_1, data));
        idChoiceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                switch (position) {
                    case 0:
                        currency_index = 1;
                        break;
                    case 1:
                        currency_index = 2;
                        break;
                    case 2:
                        currency_index = 3;
                        break;
                    case 3:
                        currency_index = 8;
                        break;
                    case 4:
                        currency_index = 9;
                        break;
                    case 5:
                        currency_index = 10;
                        break;
                    case 6:
                        currency_index = 11;
                        break;
                    case 7:
                        currency_index = 12;
                        break;
                    case 8:
                        currency_index = 13;
                        break;
                }
                //选择后
                choice_type = data[position].toString();
                if (choice_type.equals("CNY")) {
                    openBankType = "";
                    bank_type = 1;
                    id_choice_bank.setSelection(0, true);
                    idOpenBank.setVisibility(View.VISIBLE);
                    idOpenBankAddress.setVisibility(View.VISIBLE);
                    ETbranch.setVisibility(View.VISIBLE);
                } else {
                    ETbranch.setText("");
                    idBankAddress.setText("请选择");
                    bank_type = -1;
                    bank_sheng = "";
                    bank_shi = "";
                    bank_qu = "";
                    openBankType = "-1";
                    idOpenBank.setVisibility(View.GONE);
                    idOpenBankAddress.setVisibility(View.GONE);
                    ETbranch.setVisibility(View.GONE);
                    // ETbranch.clearFocus();
                }
                //  Toast.makeText(appContext, choice_type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        id_choice_bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                bank_type = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        idBankAddress.setOnClickListener(this);
        postMsg.setOnClickListener(this);
        sendMSG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_choice_bank:
                //选择开户行

                break;

            case R.id.id_bank_address:
                choice_city();
                break;
            case R.id.sendMSG:
                get_sendMsg(Phonestr);
                break;
            case R.id.postMsg:
                //提交
                if (jiance()) {
                    submit();
                }
                break;
        }
    }

    private boolean jiance() {

        if (openName.getText().toString().trim().equals("")) {
            Toast.makeText(appContext, "开户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (bankNumber.getText().toString().trim().length() < 6) {
            Toast.makeText(appContext, "请检查银行卡号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!agenbankNumber.getText().toString().trim().equals(bankNumber.getText().toString().trim())) {
            Toast.makeText(appContext, "两次银行卡号输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (choice_type.equals("CNY") && bank_sheng.equals("")) {
            Toast.makeText(appContext, "请选择省市区", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (choice_type.equals("CNY") && ETbranch.getText().toString().equals("")) {
            Toast.makeText(appContext, "请填写相关支行地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void submit() {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        //银行卡号
        params.put("account", bankNumber.getText().toString().trim());
        //验证码
        params.put("phoneCode", VerificationCode.getText().toString().trim());
        //开户行
        params.put("openBankType", bank_type);

        params.put("address", ETbranch.getText().toString());//支行信息
        params.put("prov", bank_sheng);
        params.put("city", bank_shi);
        params.put("dist", bank_qu);//区
        //开户姓名
        params.put("payeeAddr", openName.getText().toString());
        //开户行
        params.put("bankInfoopen", idBankMsg.getText().toString());
        //法币类型
        params.put("currencySymbol", choice_type);
        //法币类型对应ID
        params.put("legal_id", currency_index);
        //不知道传啥
        params.put("y_nFmerchant", isshagnhu);
        params.put("sessionid", sessionidstr);
        present.req_submit(params);
    }


    @Override
    public void set_bank_list() {

    }

    @Override
    public void set_submit_status(String status) {
        JSONObject jsonObject = JSON.parseObject(status);
        if (jsonObject.getString("code").equals("-1")) {
            showLongToast(jsonObject.getString("msg"));
        }else {
            showLongToast("银行卡添加成功！");
            finish();
        }
    }

    private void get_Merchant() {//是否是商户
        Shoudialog();
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        present.get_is_Merchant(params);
    }

    private void get_sendMsg(String msg) {//发送短信
        Shoudialog();
        RequestParams params = new RequestParams();
        params.put("type", 10);
        params.put("areaCode", "86");
        params.put("phone", msg);
        params.put("vcode", 123456);
        params.put("fuserId", fuserId);//用户ID);
        present.getSendMsg(params);
    }

    @Override
    public void set_Merchant_code(String msg) {
        DissDialog();
        JSONObject jsonObject = JSON.parseObject(msg);
        if (jsonObject.getBoolean("flag")) {
            showLongToast(jsonObject.getString("msg"));
            isshagnhu = true;
        } else {
            isshagnhu = false;
            openName.setFocusable(false);
            openName.setFocusableInTouchMode(false); // 如果之前没设置过点击事件，该处可省略
            openName.setOnClickListener(null);
            showLongToast(jsonObject.getString("msg"));

        }

    }

    @Override
    public void getSendMsg(String msg) {
        DissDialog();
        JSONObject jsonObject = JSON.parseObject(msg);
        sessionidstr = jsonObject.getString("sessionid");
        showLongToast(jsonObject.getString("msg"));
    }


    private String bank_sheng, bank_shi, bank_qu;

    private void choice_city() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //省份
                if (province != null) {
                    bank_sheng = province.getName();
                }

                //城市
                if (city != null) {
                    bank_shi = city.getName();
                }

                //地区
                if (district != null) {
                    bank_qu = district.getName();
                }

                String company_address = "";
                if (bank_sheng.equals(bank_shi)) {
                    company_address = bank_shi + bank_qu;
                } else {
                    company_address = bank_sheng + bank_shi + bank_qu;
                }
                idBankAddress.setText(company_address);
            }

            @Override
            public void onCancel() {
                Toast.makeText(BankCardReceiptActivity.this, "已取消", Toast.LENGTH_SHORT).show();
            }
        });

        //显示
        mPicker.showCityPicker();
    }

}
