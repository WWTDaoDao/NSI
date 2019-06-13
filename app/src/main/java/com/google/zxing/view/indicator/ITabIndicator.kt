package com.ktry.example.tab.indicator

import com.google.zxing.view.indicator.PositionBean

/**
 * <pre>
 *     author : Dream
 *     time   : 2019/05/13
 *     desc   : 抽象出的 TabIndicator，方便以后扩展
 * </pre>
 */
interface ITabIndicator {

    /**
     * state：
     *
     * SCROLL_STATE_IDLE - 初始状态
     *
     * SCROLL_STATE_DRAGGING - 滑动
     *
     * SCROLL_STATE_SETTLING - 释放
     *
     */
    fun onPageScrollStateChanged(state: Int)

    /**
     * position：当前页面位置
     *
     * positionOffset：当前页面偏移的百分比
     *
     * positionOffsetPixels：当前页面偏移的像素位置
     *
     */
    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)

    /**
     * position：选中页面的位置
     */
    fun onPageSelected(position: Int)

    /**
     * 提供坐标集合，用来计算 indicator 的显示位置
     */
    fun providePosition(positionList: ArrayList<PositionBean>)

}