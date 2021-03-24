package per.nullist.targetedcleaner.view.widget

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import kotlin.math.sqrt

class CircularRevealBoxScope(
    private val view: View?,
    private val radius: Float,
    private val animationDuration: Long = 500L
) {
    private fun Offset.toIntPair() = x.toInt() to y.toInt()
    private var animator: Animator? = null

    val isRunning: Boolean
        get() = animator?.isRunning ?: false

    fun forceReveal(point: Offset) {
        val (x, y) = point.toIntPair()
        val animator = ViewAnimationUtils.createCircularReveal(
            view, x, y, 0f, radius
        )

        animator.apply {
            duration = animationDuration

            doOnStart { view?.isVisible = true }
            doOnCancel { view?.isVisible = false }

            start()
        }
        this.animator = animator
    }

    fun forceConceal(point: Offset) {
        val (x, y) = point.toIntPair()
        val animator = ViewAnimationUtils.createCircularReveal(
            view, x, y, radius, 0f
        )

        view?.isVisible = true

        animator.apply {
            duration = animationDuration
            doOnEnd { view?.isVisible = false }
            doOnCancel { view?.isVisible = true }

            start()
        }

        this.animator = animator
    }
}

@Composable
fun CircularRevealBox(
    modifier: Modifier = Modifier,
    initialVisibility: Boolean = false,
    content: @Composable CircularRevealBoxScope.() -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        AndroidView(
            factory = { context ->
                ComposeView(context).apply {
                    val (h, w) = constraints.maxHeight.toFloat() to constraints.minWidth.toFloat()
                    val radius = sqrt(h * h + w * w) * 1.2f

                    isVisible = initialVisibility
                    setContent { CircularRevealBoxScope(this, radius).content() }
                }
            },
        )
    }
}

