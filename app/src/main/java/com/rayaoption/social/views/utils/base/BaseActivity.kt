package com.rayaoption.social.views.utils.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), IBaseView {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        initViews()
        initObservers()
        initData()
    }




}