package per.nullist.targetedcleaner.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view.MainActivityView
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.main.ViewModelFactory
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
            MainActivityView(viewModel, eventHandler)
        }
    }
}