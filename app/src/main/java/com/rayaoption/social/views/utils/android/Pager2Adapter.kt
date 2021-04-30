package com.rayaoption.social.views.utils.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class Pager2Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments by lazy { arrayListOf<Fragment>() }
    private val titles by lazy { arrayListOf<String>() }

    override fun getItemCount(): Int = this.fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun addFragment(fragment: Fragment, title: String): Boolean {
        titles.add(title)
        return fragments.add(fragment)
    }

    fun getTitle(position: Int) = titles[position]
    fun getFragment(position: Int) = fragments[position]

    fun replace(position: Int, fragment: Fragment, manager: FragmentManager) {
        manager.beginTransaction().remove(fragments[position]).commit()
        fragments[position] = fragment
        notifyDataSetChanged()
    }

    fun clear() {
        fragments.clear()
        titles.clear()
    }

}