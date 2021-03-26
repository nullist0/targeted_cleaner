package per.nullist.targetedcleaner.main.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository

import per.nullist.targetedcleaner.view.TimerSettingActivityView
import per.nullist.targetedcleaner.view_model.TimerSettingViewModel
import per.nullist.targetedcleaner.main.ViewModelFactory
import per.nullist.targetedcleaner.view_model.converter.IntervalConverterImpl
import per.nullist.targetedcleaner.view_model.event_handler.TimerSettingEventHandler
import per.nullist.targetedcleaner.view_model.event_handler.TimerSettingEventHandlerImpl

class TimerSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: TimerSettingViewModel by viewModels{ ViewModelFactory(application) }
        val eventHandler: TimerSettingEventHandler = TimerSettingEventHandlerImpl(
            IntervalConverterImpl(),
            LocalLiveSettingRepository(applicationContext)
        )

        setContent {
            TimerSettingActivityView(viewModel, eventHandler)
        }
    }
}