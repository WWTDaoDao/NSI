package com.google.zxing.view.indicator

/**
 * <pre>
 *     author : liuyh
 *     time   : 2019/05/14
 *     desc   :
 * </pre>
 */
class PositionBean(var left: Int, var top: Int, var right: Int, var bottom: Int) {

    constructor(): this(0, 0, 0, 0)

    fun width(): Int {
        return right - left
    }

}