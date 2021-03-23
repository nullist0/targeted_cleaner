package per.nullist.targetedcleaner.view

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import per.nullist.targetedcleaner.view.widget.RatioExpanded
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.doOnLayout
import per.nullist.targetedcleaner.component.activity.AppListActivity
import per.nullist.targetedcleaner.component.activity.TimerSettingActivity
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandler
import kotlin.math.sqrt

class CircularRevealBoxScope(
    private val view: View?,
    private val radius: Float
) {
    fun reveal(x: Int, y: Int) {
        view?.run {
            visibility = View.VISIBLE
            ViewAnimationUtils.createCircularReveal(
                view,
                x,
                y,
                0f,
                radius
                ).run {
                    duration = 1000
                    start()
                }
        }
    }

    fun bringToFront() = view?.bringToFront()
}

fun Offset.toIntPair() = x.toInt() to y.toInt()

@Composable
fun CircularRevealBox(
    modifier: Modifier = Modifier,
    animate: Boolean = false,
    centerPoint: Pair<Int, Int> = 0 to 0,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        AndroidView(
            factory = { context ->
                ComposeView(context).apply {
                    setContent { content() }
                    doOnLayout {
                        if(animate) {
                            val (x, y) = centerPoint
                            val (h, w) = constraints.maxHeight.toFloat() to constraints.minWidth.toFloat()
                            ViewAnimationUtils.createCircularReveal(it, x, y, 0f, sqrt(h * h + w * w))
                        }
                    }
                } },
        )
    }
}

@Preview
@Composable
fun CircularRevealTest(

) {
    var clickedOffset by remember { mutableStateOf(0 to 0) }
    var animate by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
//            .clickable {
//                Log.d("Main", "ClickedA")
//            }
            .pointerInput(Unit) {
                detectTapGestures {
                    Log.d("Main", "Clicked ${it.toIntPair()}")
                    animate = true
                    clickedOffset = it.toIntPair()
                }
            }
    ) {
        CircularRevealBox(
            modifier = Modifier
                .fillMaxSize(),
            animate = animate,
            centerPoint = clickedOffset
        ) {
            Text("ON", modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan),)
        }
    }
}

@Preview
@Composable
fun RememberTest () {
    var clicked by remember { mutableStateOf(0) }
    var clicked2 by remember { mutableStateOf(0) }
    Column() {
        Text("button is $clicked")
        Button(onClick = { clicked += 1 }) {
            Text("change")
        }
        Text("button2 is $clicked2")
        Button(onClick = { clicked2 += 1 }) {
            Text("change2")
        }
    }
}

//@Preview
//@Composable
//fun OnOffButton() {
//    AndroidView(factory = { context ->
//        ComposeView(context).apply {
//            setContent {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = Color(0xFFF662FF)),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    Text(
//                        text = "OFF",
//                        fontSize = 85.sp,
//                        color = Color.White
//                    )
//                }
//            }
//        }
//        },
//    )
//    AndroidView(factory = { context ->
//        ComposeView(context).apply {
//            setContent {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = Color(0xFFF66257)),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    Text(
//                        text = "ON",
//                        fontSize = 85.sp,
//                        color = Color.White
//                    )
//                }
//            }
//        }
//    })
//}
//
//@Preview
//@Composable
//fun OnOffButton(
//    modifier: Modifier = Modifier
//) {
//    var isSelected by remember { mutableStateOf(false) }
//    var scopes : Pair<CircularRevealBoxScope?, CircularRevealBoxScope?> = null to null
//
////    if(isSelected) {
////        CircularRevealBox() {
////            scopes = scopes.copy(second = this)
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(color = Color(0xFFF662FF)),
////                contentAlignment = Alignment.Center,
////            ) {
////                Text(
////                    text = "OFF",
////                    fontSize = 85.sp,
////                    color = Color.White
////                )
////            }
////        }
////        CircularRevealBox() {
////            scopes = scopes.copy(first = this)
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(color = Color(0xFFF66257)),
////                contentAlignment = Alignment.Center,
////            ) {
////                Text(
////                    text = "ON",
////                    fontSize = 85.sp,
////                    color = Color.White
////                )
////            }
////        }
////    }
////    else {
////        CircularRevealBox() {
////            scopes = scopes.copy(first = this)
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(color = Color(0xFFF66257)),
////                contentAlignment = Alignment.Center,
////            ) {
////                Text(
////                    text = "ON",
////                    fontSize = 85.sp,
////                    color = Color.White
////                )
////            }
////        }
////        CircularRevealBox() {
////            scopes = scopes.copy(second = this)
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(color = Color(0xFFF662FF)),
////                contentAlignment = Alignment.Center,
////            ) {
////                Text(
////                    text = "OFF",
////                    fontSize = 85.sp,
////                    color = Color.White
////                )
////            }
////        }
////    }
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .pointerInput(Unit) {
//                detectTapGestures {
//                    Log.d("Main", it.toString())
//                    val (x, y) = it
//                    val (onScope, offScope) = scopes
//
//                    isSelected = !isSelected
//
//                    print(scopes)
//
//                    if (isSelected) {
//                        onScope?.run {
//                            bringToFront()
//                            reveal(x.toInt(), y.toInt())
//                        }
//                    } else {
//                        offScope?.run {
//                            bringToFront()
//                            reveal(x.toInt(), y.toInt())
//                        }
//                    }
//                }
//            }
//    )
//}

@Composable
fun MainActivityView(
    viewModel: MainSwitchViewModel,
    eventHandler: MainSwitchEventHandler
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        MainSwitch(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize(),
        )
        Row(
            Modifier.fillMaxWidth(),
        ) {
            RatioExpanded(
                ratio = 0.5f,
                modifier = Modifier
                    .background(color = Color(0xFFe1e1e1))
                    .clickable {
                        context.startActivity(Intent(context, TimerSettingActivity::class.java))
                    }
            ) {
                Text("Timer Setting")
            }
            RatioExpanded(
                ratio = 0.5f,
                modifier = Modifier
                    .background(color = Color(0xFFe1e1e1))
                    .clickable {
                        context.startActivity(Intent(context, AppListActivity::class.java))
                    }
            ) {
//                Text("Filter Setting")
                Icon(Icons.Default.FilterAlt, "filter setting icon")
            }
        }
    }
}

@Composable
fun MainSwitch(
    modifier: Modifier = Modifier
) {
    //TODO("Not yet implemented")
    Box() {}
}
