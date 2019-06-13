package com.ktry.example.tab.title

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/14
 *     desc   : 抽象出的 TabTitleView，方便以后扩展
 * </pre>
 */
interface ITabTitleView {

    /**
     * 选中
     */
    fun onSelected(index: Int, totalCount: Int)

    /**
     * 未选中
     */
    fun onUnSelected(index: Int, totalCount: Int)

}