package per.nullist.targetedcleaner.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*

import per.nullist.targetedcleaner.repository.LocalLivePackageRepository
import per.nullist.targetedcleaner.view.AppListActivityView
import per.nullist.targetedcleaner.view_model.AppListViewModel
import per.nullist.targetedcleaner.component.AppListActivity
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverterImpl
import per.nullist.targetedcleaner.view_model.event_handler.SafeAppsEventHandler
import per.nullist.targetedcleaner.view_model.event_handler.SafeAppsEventHandlerImpl

class AppListActivityImpl: AppListActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AppListViewModel by viewModels { ViewModelFactory(application) }
        val eventHandler : SafeAppsEventHandler by lazy {
            SafeAppsEventHandlerImpl(
                AppInfoPackageConverterImpl(applicationContext),
                LocalLivePackageRepository(applicationContext)
            )
        }

        setContent {
            AppListActivityView(viewModel, eventHandler)
        }
    }
}