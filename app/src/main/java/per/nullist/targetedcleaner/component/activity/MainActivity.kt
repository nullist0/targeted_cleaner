package per.nullist.targetedcleaner.component.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import per.nullist.targetedcleaner.R

//import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
//import per.nullist.targetedcleaner.view.MainActivityView
//import per.nullist.targetedcleaner.view_model.MainSwitchViewModel
//import per.nullist.targetedcleaner.view_model.ViewModelFactory
//import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandler
//import per.nullist.targetedcleaner.view_model.event_handler.MainSwitchEventHandlerImpl

class MainActivity : AppCompatActivity() {
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