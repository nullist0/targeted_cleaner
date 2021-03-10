package per.nullist.targetedcleaner.component.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    Column(
        Modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize()
                .background(color = Color(0xFFF66257))
                .clickable { },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "ON",
                fontSize = 85.sp,
                color = Color.White
            )
        }
        Row(
            Modifier.fillMaxWidth(),
        ) {
            for(i in 1..2) {
                BoxWithConstraints(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = Color.Cyan)
                        .clickable { }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(
                            maxWidth,
                            maxWidth
                        ),
                    ) {
                        Text(
                            text = "x",
                            textAlign = TextAlign.Center,
                            fontSize = 60.sp
                        )
                    }
                }
            }
        }
    }
}
