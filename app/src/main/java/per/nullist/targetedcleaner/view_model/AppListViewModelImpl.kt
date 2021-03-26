package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppListViewModelImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: LivePackageRepository
) : AppListViewModel() {

    override val allInstalledApps: LiveData<List<AppInfo>> = liveData (Dispatchers.IO) {
        val apps = packageRepository.getAllInstalledPackages()
            .map { converter.convertToAppInfo(it) }
            .sortedBy { it.name }
        emit(apps)
    }

    override val safeApps: LiveData<Set<AppInfo>> by lazy {
        packageRepository.safeAppPackagesLiveData.map {
                stringSet -> stringSet.map { converter.convertToAppInfo(it) }.toSet()
        }
    }
}