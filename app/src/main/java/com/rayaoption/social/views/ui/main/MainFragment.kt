package com.rayaoption.social.views.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rayaoption.social.views.R
import com.rayaoption.social.views.databinding.FragmentMainBinding
import com.rayaoption.social.views.utils.android.gone
import com.rayaoption.social.views.utils.android.visible
import com.rayaoption.social.views.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(),
    NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    override fun getLayoutResource(): Int = R.layout.fragment_main

    override fun initViews() {
        val navHostFragment: NavHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.navInflater.inflate(R.navigation.nav_home_navigation)
        binding.navView.setupWithNavController(navController)
    }

    override fun initObservers() = Unit

    override fun initData() = Unit
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.navigation_trader, R.id.navigation_investment,
            R.id.navigation_wallet, R.id.navigation_help,
            R.id.navigation_account -> {
                binding.bottomAppBar.visible()
            }
            else -> {
                binding.bottomAppBar.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(this)
    }
}