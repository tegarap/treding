package com.rayaoption.social.views.ui.auth.register

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.rayaoption.social.views.R
import com.rayaoption.social.views.databinding.FragmentRegisterBinding
import com.rayaoption.social.views.ui.auth.login.LoginFragment
import com.rayaoption.social.views.ui.main.MainFragment
import com.rayaoption.social.views.utils.base.BaseActivity
import com.rayaoption.social.views.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun getLayoutResource(): Int = R.layout.fragment_register

    override fun initViews() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }

    override fun initObservers() {
    }

    override fun initData() {
    }
}