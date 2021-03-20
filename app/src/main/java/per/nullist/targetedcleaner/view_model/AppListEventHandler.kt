package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.MutableLiveData
import per.nullist.targetedcleaner.view_model.data.AppInfo

interface AppListEventHandler {
    fun addSafeApp(packageName: String)
    fun removeSafeApp(packageName: String)
}