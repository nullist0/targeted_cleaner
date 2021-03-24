package per.nullist.targetedcleaner.component.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view.MainActivityView
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.view_model.ViewModelFactory
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandler
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandlerImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainSwitchViewModel by viewModels { ViewModelFactory(application) }
        val eventHandler: MainSwitchEventHandler by lazy {
            MainSwitchEventHandlerImpl(
                LocalLiveSettingRepository(applicationContext)
            )
        }

        setContent {
            MainActivityView(viewModel = viewModel, eventHandler = eventHandler)
        }
    }
}