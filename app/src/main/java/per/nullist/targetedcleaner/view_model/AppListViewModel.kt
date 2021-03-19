package per.nullist.targetedcleaner.view_model

import android.content.Context
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
import androidx.lifecycle.viewModelScope
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.data.AppInfo

abstract class AppListViewModel : ViewModel() {
    abstract val apps : MutableLiveData<List<AppInfo>>
    abstract val safeApps : MutableLiveData<Set<String>>

    abstract fun addSafeApp(packageName: String)
    abstract fun removeSafeApp(packageName: String)
}

class AppListViewModelImpl(
    private val pm: PackageManager,
    private val repository: PackageRepository
) : AppListViewModel() {
    private fun PackageManager.getAppInfo(packageName: String): AppInfo {
        val info = getApplicationInfo(packageName, 0)
        val icon = getApplicationIcon(info)
        return AppInfo(
            info.name,
            packageName,
            icon.toBitmap().asImageBitmap()
        )
    }

    override val apps by lazy {
        MutableLiveData<List<AppInfo>>().apply {
            value = repository.allInstalledPackages.map { pm.getAppInfo(it) }
        }
    }

    override val safeApps by lazy {
        MutableLiveData<Set<String>>().apply {
            value = repository.safeAppPackages
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