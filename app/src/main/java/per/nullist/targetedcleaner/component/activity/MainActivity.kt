package per.nullist.targetedcleaner.component.activity

import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import per.nullist.targetedcleaner.view_model.AppListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test()
        }
    }
}

@Preview
@Composable
fun Test() {
    var view: View? = null

    Column() {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(color = Color.Red)
                .pointerInput(Unit) {
                    detectTapGestures {
                        val (x, y) = it
                        view?.run {
                            visibility = View.VISIBLE
                            ViewAnimationUtils
                                .createCircularReveal(
                                    view, x.toInt(), y.toInt(), 0f, 1000f
                                )
                                .start()
                        }
                    }
                }
        ) {
            Text(text = "reveal")
        }
        Box (
            modifier = Modifier.weight(1f)
        ){
            AndroidView(factory = {
                    context -> ComposeView(context).apply {
                visibility = View.INVISIBLE
                setContent {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Cyan)
                    ) {
                        Text(
                            text = "hi",
                        )
                    }
                }
            }
            }, update = {
                    v -> view = v
            })
        }
    }
}
