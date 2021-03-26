package per.nullist.targetedcleaner.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.core.math.MathUtils.clamp

import per.nullist.targetedcleaner.view.widget.RatioExpanded
import per.nullist.targetedcleaner.view_model.TimerSettingViewModel
import per.nullist.targetedcleaner.view_model.event_handler.TimerSettingEventHandler

import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

fun min(a: Int, b: Int): Int = if(a > b) b else a

@Composable
fun TimerView(
    modifier: Modifier = Modifier,
    interval: Int,
    onChangeTimer: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val size = min(maxHeight, maxWidth) * 0.8f
            val sizeInt = min(constraints.maxHeight, constraints.maxWidth) * 0.8f
            val center = Offset(sizeInt/2f, sizeInt/2f)

            CircularProgressIndicator(
                progress = interval / 60f,
                modifier = Modifier
                    .size(size)
                    .align(Alignment.Center)
                    .pointerInput(this) {
                        detectDragGestures { change, _ ->
                            change.consumeAllChanges()
                            val current = change.position
                            val relativePoint = current - center
                            val (x, y) = relativePoint.y to -relativePoint.x
                            val nextInterval =
                                clamp((60 * (atan2(y, x) + PI) / (2 * PI)).roundToInt(), 15, 60)
                            onChangeTimer(nextInterval)
                        }
                    },
                strokeWidth = 25.dp
            )
        }
        Text("$interval 분마다 실행")
    }
}

@Composable
fun TimerNavigator(
    modifier: Modifier = Modifier,
    onChangeTimer: (Int) -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        RatioExpanded {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { onChangeTimer(15) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("15분 마다")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { onChangeTimer(45) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("45분 마다")
                }
            }
        }
        RatioExpanded {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { onChangeTimer(30) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("30분 마다")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clickable { onChangeTimer(60) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("60분 마다")
                }
            }
        }
    }
}

@Composable
fun TimerSettingActivityView(
    viewModel: TimerSettingViewModel,
    eventHandler: TimerSettingEventHandler
) {
    val interval by viewModel.interval.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar {
                Text("App List",
                    Modifier
                        .padding(start = 20.dp))
            }
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                TimerView(
                    interval = interval ?: 15,
                ) {
                    eventHandler.setInterval(it)
                }
            }
            TimerNavigator(Modifier.background(Color(0xFFe1e1e1))) {
                eventHandler.setInterval(it)
            }
        }
    }
}
