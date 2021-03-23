package per.nullist.targetedcleaner.view_model

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppListViewModelImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: LivePackageRepository
) : AppListViewModel() {

    override val allInstalledApps: LiveData<List<AppInfo>> =
        MutableLiveData<List<AppInfo>>(listOf()).apply {
            viewModelScope.launch {

                val packages = packageRepository.allInstalledPackages
                postValue(packages.map { converter.convertToAppInfo(it) }.sortedBy { it.name })
            }
    }

    override val safeApps: LiveData<Set<AppInfo>> by lazy {
        packageRepository.safeAppPackagesLiveData.map {
                stringSet -> stringSet.map { converter.convertToAppInfo(it) }.toSet()
        }
    }
}