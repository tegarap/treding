package com.rayaoption.social.views.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.rayaoption.social.views.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun View.wheel() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate))
}

fun View.wheelHalf() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_half))
}

fun View.jump(y: Float) {
    animate().translationY(y)
}

fun View.wheel(duration: Long) {
    GlobalScope.launch {
         wheel()
        delay(duration)
        silent()
    }
}

fun View.silent() {
    clearAnimation()
}

fun View.bounce() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce))
}


fun ProgressBar.setUpMax(maxProgress: Int) {
    max = maxProgress * 100
}

fun ProgressBar.progressUp(progresTo: Int) {
    val animation = ObjectAnimator.ofInt(this, "progress", progress, progresTo * 100)
    animation.duration = 500
    animation.interpolator = DecelerateInterpolator()
    animation.start()
}