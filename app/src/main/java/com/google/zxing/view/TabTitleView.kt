package com.ktry.example.tab.title

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/14
 *     desc   : 自定义 TabTitleView，并实现 ITabTitleView
 * </pre>
 */
class TabTitleView : AppCompatTextView, ITabTitleView {

    private var mUnSelectedColor: Int = Color.GRAY
    private var mSelectedColor: Int = Color.RED
    private var isBold = false

    init {
        gravity = Gravity.CENTER
        val padding = dip2px(context, 10.0)
        setPadding(padding, 0, padding, 0)
        setSingleLine()
        ellipsize = TextUtils.TruncateAt.END
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSelected(index: Int, totalCount: Int) {
        setTextColor(mSelectedColor)
        paint.isFakeBoldText = isBold
    }

    override fun onUnSelected(index: Int, totalCount: Int) {
        setTextColor(mUnSelectedColor)
        paint.isFakeBoldText = false
    }

    fun setSelectedBold(isBold: Boolean) {
        this.isBold = isBold
    }

    fun setSelectedColor(selectedColor: Int) {
        this.mSelectedColor = selectedColor
    }

    fun setUnSelectedColor(unSelectedColor: Int) {
        this.mUnSelectedColor = unSelectedColor
    }

    private fun dip2px(context: Context, dpValue: Double): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density + 0.5).toInt()
    }

}