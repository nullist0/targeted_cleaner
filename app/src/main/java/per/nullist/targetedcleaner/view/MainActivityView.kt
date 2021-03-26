package per.nullist.targetedcleaner.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import per.nullist.targetedcleaner.view.widget.RatioExpanded
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import per.nullist.targetedcleaner.main.activity.AppListActivity
import per.nullist.targetedcleaner.main.activity.TimerSettingActivity
import per.nullist.targetedcleaner.view.widget.CircularRevealBox
import per.nullist.targetedcleaner.view.widget.CircularRevealBoxScope
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandler

@Composable
fun MainSwitch(
    modifier: Modifier = Modifier,
    initialValue: Boolean,
    onChangeChecked : (Boolean) -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    Box(
        modifier.fillMaxSize()
    ) {
        var scope: CircularRevealBoxScope? = null
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF422000)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "OFF",
                fontSize = 85.sp,
                color = Color.White
            )
        }
        CircularRevealBox(
            initialVisibility = value
        ) {
            scope = this
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFF66257)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "ON",
                    fontSize = 85.sp,
                    color = Color.White
                )
            }
        }
        Box(
            modifier
                .fillMaxSize()
                .pointerInput(this) {
                    detectTapGestures {
                        scope?.run {
                            if (!isRunning) {
                                value = !value
                                when {
                                    value -> forceReveal(it)
                                    else -> forceConceal(it)
                                }
                                onChangeChecked(value)
                            }
                        }
                    }
                }
        )
    }
}

@Composable
fun MainNavigator(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(modifier) {
        RatioExpanded(
            ratio = 0.5f,
            modifier = Modifier
                .background(Color(0xFFe1e1e1))
                .clickable {
                    context.startActivity(Intent(context, TimerSettingActivity::class.java))
                }
        ) {
            Icon(
                Icons.Default.Timer,
                "timer setting icon",
                Modifier.size(30.dp)
            )
        }
        RatioExpanded(
            ratio = 0.5f,
            modifier = Modifier
                .background(Color(0xFFe1e1e1))
                .clickable {
                    context.startActivity(Intent(context, AppListActivity::class.java))
                }
        ) {
            Icon(
                Icons.Default.FilterAlt,
                "filter setting icon",
                Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun MainActivityView(
    viewModel: MainSwitchViewModel,
    eventHandler: MainSwitchEventHandler
) {
    val isRunning by viewModel.isRunning.observeAsState()

    Column(Modifier.fillMaxHeight()) {
        MainSwitch(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize(),
            initialValue = isRunning ?: false,
            onChangeChecked = { eventHandler.setIsRunning(it) }
        )
        MainNavigator(Modifier.fillMaxWidth())
    }
}
