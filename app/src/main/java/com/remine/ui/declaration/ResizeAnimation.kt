package com.remine.ui.declaration
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import kotlin.random.Random

class ResizeAnimation(private val view: View, private val minHeight: Int, private val maxHeight: Int) : Animation() {

    private var targetHeight = minHeight

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val range = maxHeight - minHeight
        val randomChange = Random.nextInt(range)
        targetHeight = minHeight + randomChange

        val currentHeight = view.height
        val newHeight = currentHeight + (targetHeight - currentHeight) * interpolatedTime
        view.layoutParams.height = newHeight.toInt()
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}
