package com.nsi.okexsay.ui.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nsi.okexsay.R;
import com.nsi.okexsay.ui.base.BaseActivity;

//选中支付方式
public class AddPayment extends BaseActivity implements View.OnClickListener {
    private TextView tv_title, add_yinghangka, add_weixin, add_zhifubo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpayment);
        tv_title = findViewById(R.id.home_text_title);
        add_weixin = findViewById(R.id.add_weixin);
        add_yinghangka = findViewById(R.id.add_yinghangka);
        add_zhifubo = findViewById(R.id.add_zhifubo);
        tv_title.setText("选择收款方式");
        add_weixin.setOnClickListener(this);
        add_yinghangka.setOnClickListener(this);
        add_zhifubo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_weixin:
                Intent intent = new Intent(this, AddZhiFuBaoWeiXinActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.add_yinghangka:
                startActivity(new Intent(this, BankCardReceiptActivity.class));
                break;
            case R.id.add_zhifubo:
                Intent intent2 = new Intent(this, AddZhiFuBaoWeiXinActivity.class);
                intent2.putExtra("type", "1");
                startActivity(intent2);
                break;
        }
    }
}
