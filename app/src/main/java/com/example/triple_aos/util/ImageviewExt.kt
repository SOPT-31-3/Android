package com.example.triple_aos.util

import android.animation.ObjectAnimator
import android.view.View
import android.widget.ImageView

fun ImageView.rotate(degree:Float){
    ObjectAnimator.ofFloat(this, View.ROTATION, this.rotation, this.rotation + degree)
        .setDuration(300)
        .start()
}
