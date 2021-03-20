package per.nullist.targetedcleaner.view_model

import android.content.pm.PackageManager
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppListViewModel(
    private val pm: PackageManager,
    private val repository: PackageRepository
) : ViewModel() {
    private fun PackageManager.getAppInfo(packageName: String): AppInfo {
        val info = getApplicationInfo(packageName, 0)
        val icon = getApplicationIcon(info)
        return AppInfo(
            info.name,
            packageName,
            icon.toBitmap().asImageBitmap()
        )
    }

//    override val data : AppListLiveData by lazy {
//        TODO()
//    }

//    override val apps by lazy {
//        MutableLiveData<List<AppInfo>>().apply {
//            value = repository.allInstalledPackages.map { pm.getAppInfo(it) }
//        }
//    }
//
//    override val safeApps by lazy {
//        MutableLiveData<Set<String>>().apply {
//            value = repository.safeAppPackages
//        }
//    }

    // given and then
    override fun addSafeApp(packageName: String) {
//        safeApps.value?.let { value ->
//            safeApps.value = value + packageName
//        }
    }

    override fun removeSafeApp(packageName: String) {
//        safeApps.value?.let { value ->
//            safeApps.value = value - packageName
//        }
    }

    override fun draw(apps: List<AppInfo>) {
        TODO("Not yet implemented")
    }
}