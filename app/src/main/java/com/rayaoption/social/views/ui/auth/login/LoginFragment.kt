package com.rayaoption.social.views.ui.auth.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.rayaoption.social.views.R
import com.rayaoption.social.views.databinding.FragmentLoginBinding
import com.rayaoption.social.views.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth

    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun initViews() {

//        binding.pager.adapter = PagerAdapter(this.requireActivity())
//        binding.indicator.setViewPager2(binding.pager)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        if(user != null){
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment2)
        } else {
//            signIn()

            binding.btnLoginGoogle.setOnClickListener {
                signIn()
            }
            binding.btnLoginFacebook.setOnClickListener {
                signIn()
            }
        }
    }

    override fun initObservers() {
    }

    override fun initData() {
    }

    private class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return SlideFragment()
        }

        override fun getItemCount(): Int {
            return 4
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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
                .setTheme(R.style.Theme_RayaSocial)
                .build(),
            RC_SIGN_IN
        )
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception = task.exception
//            if(task.isSuccessful){
//                try {
//                    // Google Sign In was successful, authenticate with Firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    Log.d("LoginFragment", "firebaseAuthWithGoogle:" + account.id)
//                    firebaseAuthWithGoogle(account.idToken!!)
//                } catch (e: ApiException) {
//                    // Google Sign In failed, update UI appropriately
//                    Log.w("LoginFragment", "Google sign in failed", e)
//                }
//            } else {
//                Log.w("LoginFragment", exception.toString())
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this.requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("LoginFragment", "signInWithCredential:success")
//                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w("LoginFragment", "signInWithCredential:failure", task.exception)
//                }
//            }
//    }
}