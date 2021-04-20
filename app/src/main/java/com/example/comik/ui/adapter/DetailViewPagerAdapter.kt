package com.example.comik.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<FragmentHolder>()
    override fun getItem(position: Int) = fragments[position].fragment

    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = fragments[position].title

    fun addFragment(fragmentHolder: FragmentHolder) {
        fragments.add(fragmentHolder)
    }

    class FragmentHolder(
        var fragment: Fragment,
        var title: String
    )
}
