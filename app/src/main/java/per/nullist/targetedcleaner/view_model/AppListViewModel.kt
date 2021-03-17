package per.nullist.targetedcleaner.view_model

import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.data.AppInfo

abstract class AppListViewModel : ViewModel() {
    abstract val apps : MutableLiveData<List<AppInfo>>
    abstract val safeApps : MutableLiveData<Set<String>>

    abstract fun addSafeApp(packageName: String)
    abstract fun removeSafeApp(packageName: String)
}

class AppListViewModelImpl(val repository: PackageRepository) : AppListViewModel() {
    override val apps by lazy {
        MutableLiveData<List<AppInfo>>().apply {
        }
    }

    override val safeApps by lazy {
        MutableLiveData<Set<String>>().apply {

        }
    }

    override fun addSafeApp(packageName: String) {
        safeApps.value?.let { value ->
            safeApps.value = value + packageName
        }
    }

    override fun removeSafeApp(packageName: String) {
        safeApps.value?.let { value ->
            safeApps.value = value - packageName
        }
    }
}