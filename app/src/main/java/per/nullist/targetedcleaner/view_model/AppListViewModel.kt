package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import per.nullist.targetedcleaner.view_model.data.AppInfo

/**
 * Data binder class for App List Compose in AppListView
  */
abstract class AppListViewModel: ViewModel() {
    /**
     * Getter of app information list for view
     */
    abstract val allInstalledApps: LiveData<List<AppInfo>>

    /**
     * Getter of a set of apps which is safe from auto killer
     */
    abstract val safeApps: LiveData<Set<AppInfo>>
}
