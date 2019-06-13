package com.nsi.okexsay.wiget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.nsi.okexsay.R;


/**
 * 加载对话框控件
 *
 * @author 刘欢
 * @version 1.0
 * @created 2016-09-06
 */
public class LoadingDialog extends Dialog {

    private Context mContext;
    private LayoutInflater inflater;
    private LayoutParams lp;
    private TextView loadtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失
    }

    public LoadingDialog(Context context) {
        super(context,
                R.style.Dialog);

        this.mContext = context;

        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.loadingdialog, null);
        loadtext = layout.findViewById(R.id.loading_text);
        setContentView(layout);

        // 设置window属性
        lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0; // 去背景遮盖
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);

    }

    public void setLoadText(String content) {
        loadtext.setText(content);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.setCancelable(true);
        return super.onKeyDown(keyCode, event);
    }
}