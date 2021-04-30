package com.rayaoption.social.views.ui

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.annotation.DrawableRes
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.rayaoption.social.views.R
import com.rayaoption.social.views.databinding.ActivitySplashBinding
import com.rayaoption.social.views.ui.auth.login.LoginFragment
import com.rayaoption.social.views.ui.auth.register.RegisterFragment
import com.rayaoption.social.views.ui.main.MainFragment
import com.rayaoption.social.views.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.sign

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth

    override fun getLayoutResource(): Int = R.layout.activity_splash

    override fun initViews() {

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if(user != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                signIn()
            }

        },3000)
    }

    override fun initObservers() = Unit

    override fun initData() = Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Timber.tag("LoginFragment").w(response.toString())
            }
        }
    }

    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build())

        val layout = AuthMethodPickerLayout
            .Builder(R.layout.fragment_login)
            .setGoogleButtonId(R.id.btnLoginGoogle)
            .setFacebookButtonId(R.id.btnLoginFacebook)
            .build()

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setAuthMethodPickerLayout(layout)
                .setIsSmartLockEnabled(false) //enable or disable smartlock
                .setTheme(R.style.Theme_RayaSocial)
                .build(),
            RC_SIGN_IN
        )
    }
}
