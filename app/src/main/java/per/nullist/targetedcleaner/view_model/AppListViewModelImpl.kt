package per.nullist.targetedcleaner.view_model

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppListViewModelImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: LivePackageRepository
) : AppListViewModel() {

    override val allInstalledApps: LiveData<List<AppInfo>> =
        packageRepository.allInstalledPackages
            .map { list -> list.map { converter.convertToAppInfo(it) } }
            .map { list -> list.sortedBy { it.name } }
            .asLiveData()

    override val safeApps: LiveData<Set<AppInfo>> by lazy {
        packageRepository.safeAppPackagesLiveData.map {
                stringSet -> stringSet.map { converter.convertToAppInfo(it) }.toSet()
        }
    }
}