package com.google.zxing.view

import android.content.Context
import com.ktry.example.tab.indicator.ITabIndicator
import com.ktry.example.tab.title.ITabTitleView

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/13
 *     desc   : TabLayoutAdapter，JDTabLayout 适配器，提供 ITabTitleView、ITabIndicator 等
 * </pre>
 */
abstract class TabLayoutAdapter {

    abstract fun getCount(): Int

    abstract fun provideTabTitleView(context: Context, index: Int): ITabTitleView

    abstract fun provideTabIndicatorView(context: Context): ITabIndicator

    fun getWeight(): Float? = 1f

}