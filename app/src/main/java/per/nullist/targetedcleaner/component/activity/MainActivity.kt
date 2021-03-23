package per.nullist.targetedcleaner.component.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import per.nullist.targetedcleaner.R

import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view.MainActivityView
import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
import per.nullist.targetedcleaner.view_model.ViewModelFactory
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandler
import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandlerImpl

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContent {
//            val viewModel: MainSwitchViewModel by viewModels { ViewModelFactory(application) }
//            val eventHandler: MainSwitchEventHandler by lazy {
//                MainSwitchEventHandlerImpl(
//                    LocalLiveSettingRepository(applicationContext)
//                )
//            }
//            MainActivityView(viewModel = viewModel, eventHandler = eventHandler)
//        }
    }
}