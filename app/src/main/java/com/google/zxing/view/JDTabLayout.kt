package com.google.zxing.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.SparseBooleanArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.zxing.view.indicator.PositionBean
import com.ktry.example.tab.indicator.ITabIndicator
import com.ktry.example.tab.indicator.TabIndicator
import com.ktry.example.tab.title.ITabTitleView
import com.ktry.example.tab.title.TabTitleView
import com.nsi.okexsay.R
import com.nsi.okexsay.ui.OnTabSelectedListener
import java.util.ArrayList

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/14
 *     desc   : 自定义实现灵活的 TabLayout
 *
 *              已实现功能：TabTitleView 文字大小、文字颜色、是否选中加粗、平分屏幕宽度
 *                         TabIndicator 指示器颜色、宽高
 *
 *              优化点，也是将来要实现的部分：
 *                         1. 增加可滑动模式，Scrollable
 *                         2. 增加指示器样式种类以及动画
 *                         3. 滑动切换时，文字颜色过度
 *                         4. 标题栏支持显示图片
 *                         5. 抽象出 Tab 实体、Indicator 实体
 *                         6. 增加消息红点以及消息数量提示
 *
 * </pre>
 */
class JDTabLayout : FrameLayout {
    private var mOnTabSelectedListener: OnTabSelectedListener? = null

    // 当前显示位置
    private var mCurrentIndex = 0

    // 标题数组，这里用的是数组，当然也可以用list
    private var mTabTitles = arrayOf<String>()

    // 抽象出的标题以及指示器
    private lateinit var mTabTitleView: ITabTitleView
    private lateinit var mTabIndicator: ITabIndicator

    // 显示 TabTitleView 的容器
    private lateinit var tabTitleContainer: LinearLayout

    // 后期再优化
    private var mTabAdapter: TabLayoutAdapter? = null

    private var mScrollMode = ViewPager.SCROLL_STATE_IDLE

    // 存放所有 TabTextView 选中状态
    private var mTabTextViewArray = SparseBooleanArray()

    // 存放坐标点，用来计算 indicator 的显示位置
    private val mPositionList = ArrayList<PositionBean>()

    // ---------------------- 自定义属性 ----------------------
    private var tabTitleSize = 0
    private var tabTitleUnSelectedColor = Color.GRAY
    private var tabTitleSelectedColor = Color.BLACK
    // 选中之后是否加粗显示
    private var selectedTextIsBold = false

    private var tabIndicatorWidth = 0
    private var tabIndicatorHeight = 0
    private var tabIndicatorRadius = 0
    private var tabIndicatorColor = Color.BLUE

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    /**
     * 自定义属性的初始化
     */
    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.JDTabLayout)

        tabTitleSize = typedArray.getDimensionPixelSize(R.styleable.JDTabLayout_tab_title_size, sp2px(15f))
        tabTitleSelectedColor = typedArray.getColor(R.styleable.JDTabLayout_tab_title_selected_color, tabTitleSelectedColor)
        tabTitleUnSelectedColor = typedArray.getColor(R.styleable.JDTabLayout_tab_title_unselected_color, tabTitleUnSelectedColor)
        selectedTextIsBold = typedArray.getBoolean(R.styleable.JDTabLayout_tab_title_selected_bold, selectedTextIsBold)

        tabIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.JDTabLayout_tab_indicator_width, dp2px(40f))
        tabIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.JDTabLayout_tab_indicator_height, dp2px(3f))
        tabIndicatorRadius = typedArray.getDimensionPixelSize(R.styleable.JDTabLayout_tab_indicator_radius, dp2px(3f))
        tabIndicatorColor = typedArray.getColor(R.styleable.JDTabLayout_tab_indicator_color, tabIndicatorColor)

        typedArray.recycle()
    }

    /**
     * 将每个 titleView 的位置传给 indicator，以确定 indicator 的显示位置
     */
    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mTabAdapter == null) return
        for (i in 0 until childCount) {
            if (i == 0) {
                for (j in 0 until getTotalCount()) {
                    val titleView = tabTitleContainer.getChildAt(j) ?: continue
                    val positionBean = PositionBean(titleView.left, titleView.top, titleView.right, titleView.bottom)
                    mPositionList.add(positionBean)
                }
                // 初始化状态
                mTabIndicator.providePosition(mPositionList)
            }
        }
        if (mScrollMode == ViewPager.SCROLL_STATE_IDLE) {
            onPageSelected(mCurrentIndex)
            onPageScrolled(mCurrentIndex, 0.0f, 0)
        }
    }

    /**
     * 提供数据，TabIndicatorView，TabTitleView
     */
    private fun setAdapter() {
        mTabAdapter = object : TabLayoutAdapter() {
            override fun provideTabIndicatorView(context: Context): ITabIndicator {
                return createTabIndicator()
            }

            override fun provideTabTitleView(context: Context, index: Int): ITabTitleView {
                return createTabTitleView(index)
            }

            override fun getCount(): Int {
                return getTotalCount()
            }
        }
    }

    /**
     * 将基本布局加载进来，并动态创建 TabTitleView、TabIndicator
     */
    private fun createView() {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_tab, this, false)

        tabTitleContainer = view.findViewById<LinearLayout>(R.id.tabTitle_container)
        val tabIndicatorContainer = view.findViewById<LinearLayout>(R.id.tabIndicator_container)

        mTabAdapter?.let {
            for (i in mTabTitles.indices) {
                val tabTitleView = it.provideTabTitleView(context, i) as View
                val layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
                layoutParams.weight = it.getWeight()!!
                tabTitleView.layoutParams = layoutParams
                tabTitleContainer.addView(tabTitleView)
            }
            mTabIndicator = it.provideTabIndicatorView(context)
            val indicatorView = mTabIndicator as View
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            indicatorView.layoutParams = layoutParams
            tabIndicatorContainer.addView(indicatorView)
        }
        addView(view)
    }

    /**
     * 创建 TabTitleView
     */
    private fun createTabTitleView(index: Int): ITabTitleView {
        mTabTitleView = TabTitleView(context)
        val tabTitleView = mTabTitleView as TabTitleView
        tabTitleView.text = mTabTitles[index]
        tabTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTitleSize.toFloat())
        tabTitleView.setSelectedBold(selectedTextIsBold)
        tabTitleView.setSelectedColor(tabTitleSelectedColor)
        tabTitleView.setUnSelectedColor(tabTitleUnSelectedColor)
        tabTitleView.setOnClickListener {
            mOnTabSelectedListener?.onTabSelected(index)
        }
        return tabTitleView
    }

    /**
     * 创建 TabIndicator
     */
    private fun createTabIndicator(): ITabIndicator {
        mTabIndicator = TabIndicator(context)
        val tabIndicator = mTabIndicator as TabIndicator
        tabIndicator.setIndicatorWidth(tabIndicatorWidth)
        tabIndicator.setIndicatorHeight(tabIndicatorHeight)
        tabIndicator.setIndicatorRadius(tabIndicatorRadius)
        tabIndicator.setIndicatorColor(tabIndicatorColor)
        return tabIndicator
    }

    fun getTotalCount(): Int = mTabTitles.size

    /**
     * 设置标题
     */
    fun setTabTitles(tabTitles: Array<String>): JDTabLayout {
        mTabTitles = tabTitles
        return this
    }
    fun setOnTabSelectedListener(listener: OnTabSelectedListener): JDTabLayout {
        this.mOnTabSelectedListener = listener
        return this
    }
    /**
     * 设置选中的位置
     */
    fun setSelectedIndex(index: Int): JDTabLayout {
        mCurrentIndex = index
        return this
    }

    /**
     * 创建并刷新绘制当前 ViewGroup
     */
    fun create(): JDTabLayout {
        setAdapter()
        createView()
        invalidate()
        return this
    }

    /**
     * 也可抽象出接口，实现此接口，并重写 onPageScrollStateChanged，onPageScrolled，onPageSelected
     */
    fun onPageScrollStateChanged(scrollMode: Int) {
        mScrollMode = scrollMode
        mTabIndicator.onPageScrollStateChanged(scrollMode)
    }

    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        mTabIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    fun onPageSelected(position: Int) {
        changeTabTextView(position)
        mTabIndicator.onPageSelected(position)
    }

    /**
     * 改变 TabTextView 选中状态
     */
    private fun changeTabTextView(position: Int) {
        mCurrentIndex = position
        onTabSelected(position)
        onTabUnSelected()
    }

    /**
     * tab 选中，更改文字颜色
     */
    private fun onTabSelected(position: Int) {
        mTabTextViewArray.put(position, false)
        val tabTitleView = tabTitleContainer.getChildAt(position) as ITabTitleView
        tabTitleView.onSelected(mCurrentIndex, getTotalCount())
    }

    /**
     * tab 未选中，更改文字颜色
     */
    private fun onTabUnSelected() {
        for (index in 0 until getTotalCount()) {
            if (index == mCurrentIndex)
                continue
            val unSelected = mTabTextViewArray.get(index)
            if (!unSelected) {
                mTabTextViewArray.put(index, true)
                val titleView = tabTitleContainer.getChildAt(index) as ITabTitleView
                titleView.onUnSelected(index, getTotalCount())
            }
        }
    }

    private fun sp2px(spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, resources.displayMetrics).toInt()
    }

    private fun dp2px(dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, resources.displayMetrics).toInt()
    }

}