package com.ktry.example.tab.indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.google.zxing.view.indicator.PositionBean

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/14
 *     desc   : 自定义指示器 TabIndicator
 * </pre>
 */
class TabIndicator : View, ITabIndicator {


    private var mPaint = Paint()

    // mIndicatorWidth：indicator 宽度
    // mIndicatorHeight：indicator 高度
    // mIndicatorRadius：indicator 圆角
    // mIndicatorColor：indicator 颜色
    private var mIndicatorWidth = 0f
    private var mIndicatorHeight = 0f
    private var mIndicatorRadius = 0f
    private var mIndicatorColor = Color.BLUE

    private val mIndicatorRect = RectF()

    private var mPositionList = ArrayList<PositionBean>()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(mIndicatorRect, mIndicatorRadius, mIndicatorRadius, mPaint)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (mPositionList.isEmpty()) {
            return
        }

        val curPos = getPositionData(mPositionList, position)
        val nextPos = getPositionData(mPositionList, position + 1)

        // 确定坐标，绘制
        val leftX: Float
        val nextLeftX: Float
        val rightX: Float
        val nextRightX: Float

        leftX = curPos.left + (curPos.width() - mIndicatorWidth) / 2
        nextLeftX = nextPos.left + (nextPos.width() - mIndicatorWidth) / 2
        rightX = curPos.left + (curPos.width() + mIndicatorWidth) / 2
        nextRightX = nextPos.left + (nextPos.width() + mIndicatorWidth) / 2

        mIndicatorRect.left = leftX + (nextLeftX - leftX) * positionOffset
        mIndicatorRect.right = rightX + (nextRightX - rightX) * positionOffset
        mIndicatorRect.top = height.toFloat() - mIndicatorHeight
        mIndicatorRect.bottom = height.toFloat()

        invalidate()
    }

    override fun onPageSelected(position: Int) {

    }

    override fun providePosition(positionList: ArrayList<PositionBean>) {
        this.mPositionList = positionList
    }

    /**
     * 获取坐标点，如果越界，则构造一个 Position 并返回
     */
    private fun getPositionData(positionDataList: ArrayList<PositionBean>, index: Int): PositionBean {
        if (index >= 0 && index <= positionDataList.size - 1) {
            return positionDataList[index]
        } else {
            val result = PositionBean()
            val referenceData: PositionBean
            val offset: Int
            if (index < 0) {
                offset = index
                referenceData = positionDataList[0]
            } else {
                offset = index - positionDataList.size + 1
                referenceData = positionDataList[positionDataList.size - 1]
            }
            result.left = referenceData.left + offset * referenceData.width()
            result.top = referenceData.top
            result.right = referenceData.right + offset * referenceData.width()
            result.bottom = referenceData.bottom
            return result
        }
    }

    fun setIndicatorWidth(indicatorWidth: Int) {
        this.mIndicatorWidth = indicatorWidth.toFloat()
    }

    fun setIndicatorHeight(indicatorHeight: Int) {
        this.mIndicatorHeight = indicatorHeight.toFloat()
    }

    fun setIndicatorRadius(indicatorRadius: Int) {
        this.mIndicatorRadius = indicatorRadius.toFloat()
    }

    fun setIndicatorColor(indicatorColor: Int) {
        this.mIndicatorColor = indicatorColor
        mPaint.color = mIndicatorColor
    }

}