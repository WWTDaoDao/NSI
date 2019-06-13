package com.nsi.okexsay.ui.homepage.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import java.util.ArrayList

/**
 * <pre>
 *     author : Liuyh
 *     time   : 2019/05/13
 *     desc   :
 * </pre>
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var fragmentArray = ArrayList<Fragment>()

    override fun getItem(p0: Int): Fragment = fragmentArray[p0]

    override fun getCount(): Int = fragmentArray.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position) as Fragment
    }

    fun setFragments(fragments: ArrayList<Fragment>) {
        this.fragmentArray = fragments
    }

}