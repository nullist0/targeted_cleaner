package per.nullist.targetedcleaner.component.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view.MainActivityView
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.view_model.ViewModelFactory
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandlerImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainSwitchViewModel by viewModels { ViewModelFactory(application) }
        val eventHandler = MainSwitchEventHandlerImpl(
            LocalLiveSettingRepository(applicationContext)
        )

        setContent {
            MainActivityView(viewModel = viewModel, eventHandler = eventHandler)
        }
    }
}