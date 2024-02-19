package com.remine.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReportAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    /*override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = titleList.size

    override fun getPageTitle(position: Int): CharSequence = titleList[position]*/

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    override fun getItemCount(): Int = titleList.size

    fun getPageTitle(position: Int): CharSequence = titleList[position]

    fun addFragment(fragment: Fragment, title: String)
    {
        fragmentList.add(fragment)
        titleList.add(title)
    }

}