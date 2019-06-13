package com.nsi.okexsay.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.nsi.okexsay.R;

/**
 * 图片工具
 */
public class GlideUtil {

    /**
     * 二级缓存的策略方式
     *
     * @param imgUrl
     * @param context
     * @param imageView
     */
//    public static void diskCacheStrategy(String imgUrl, Context context, ImageView imageView, int... placeHoldImg) {
//        Glide.with(context)
//                .load(imgUrl)
//                .centerCrop()
//                .transform(new CenterCrop(context),new GlideRoundTransform(context))
//                .placeholder(placeHoldImg.length == 0 ? R.drawable.ic_default : placeHoldImg[0])
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }

    public static void diskCacheStrategy(String imgUrl, Context context, ImageView imageView, int... placeHoldImg) {
        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .placeholder(placeHoldImg.length == 0 ? R.drawable.popupdowndrawable : placeHoldImg[0])
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }


    /**
     * 设置尺寸大小
     *
     * @param imgUrl
     * @param context
     * @param imageView
     */
    public static void override(String imgUrl, Context context, ImageView imageView) {
        Glide.with(context)
                .load("http://nm/photo/1f/1f7a.jpg")
                .override(600, 600)
                .into(imageView);
    }


    /**
     * 设置缩略图
     *
     * @param imgUrl
     * @param context
     * @param imageView
     */
    public static void thumbnail(String imgUrl, Context context, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .thumbnail(0.1f)
                .into(imageView);
    }


    /**
     * 跳过内存策略
     *
     * @param imgUrl
     * @param context
     * @param imageView
     */
    public static void skipMemoryCacheStrategy(String imgUrl, Context context, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .skipMemoryCache(true)
                .into(imageView);
    }

    /*
     *清空存储缓存
     */
    public void clearDiskCache(Context context) {
        //在子线程中进行
        Glide.get(context).clearDiskCache();
    }

    /*
     *清空内存缓存
     */
    public void clearMemory(Context context) {
        //在子线程中进行
        Glide.get(context).clearMemory();
    }
}
