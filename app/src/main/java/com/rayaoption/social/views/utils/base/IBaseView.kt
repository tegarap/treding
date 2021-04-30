package com.rayaoption.social.views.utils.base

import androidx.annotation.LayoutRes

interface IBaseView {
    @LayoutRes
    fun getLayoutResource(): Int
    fun initViews()
    fun initObservers()
    fun initData()

}