package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.MutableLiveData
import per.nullist.targetedcleaner.view_model.data.AppInfo

data class AppListLiveData(
    val apps : MutableLiveData<List<AppInfo>>,
    val safeApps : MutableLiveData<Set<String>>
)
