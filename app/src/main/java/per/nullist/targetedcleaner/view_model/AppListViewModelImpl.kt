package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppListViewModelImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: LivePackageRepository
) : AppListViewModel() {

    override val allInstalledApps: LiveData<List<AppInfo>> by lazy {
        MutableLiveData(packageRepository.allInstalledPackages.map { converter.convertToAppInfo(it) })
    }

    override val safeApps: LiveData<Set<AppInfo>> by lazy {
        packageRepository.safeAppPackagesLiveData.map {
                stringSet -> stringSet.map { converter.convertToAppInfo(it) }.toSet()
        }
    }
}