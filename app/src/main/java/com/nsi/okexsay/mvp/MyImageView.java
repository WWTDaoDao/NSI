package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 根据图片大小自动改变高度展示图片
 * 控件自适应图片大小
 * Created by GLX on 2017/4/5.
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    public MyImageView(Context context) {
        super(context);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int src_resource = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        this.setImageBitmap(getDrawable(getResources(), src_resource));
        init();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int src_resource = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
        this.setImageBitmap(getDrawable(getResources(), src_resource));
        init();
    }

    private Bitmap getDrawable(Resources res, int id) {
        bitmap = BitmapFactory.decodeStream(res.openRawResource(id));
        bitmapW = bitmap.getWidth();
        bitmapH = bitmap.getHeight();

        return bitmap;
    }

    private LinearLayout.LayoutParams layoutParams;
    private Bitmap bitmap;
    private int bitmapW;
    private int bitmapH;

    private void init() {

    }

    /**
     * onAttachedToWindow是在第一次onDraw前调用的。也就是我们写的View在没有绘制出来时调用的，但只会调用一次。
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /**
     * 该方法指定自定义控件在屏幕上的大小。
     * 两个参数是由上一层控件（父控件）传入的
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();

        if(d!=null){
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void setImg(int resid) {
//        bitmap = BitmapFactory.decodeResource(getResources(), resid);
//        bitmapW = bitmap.getWidth();
//        bitmapH = bitmap.getHeight();
//        setBackgroundResource(resid);

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawBitmap(bitmap, 0, 0, null);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
