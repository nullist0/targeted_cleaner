package per.nullist.targetedcleaner.livedata

import androidx.lifecycle.LiveData
import per.nullist.targetedcleaner.entity.PackageRepository

interface LivePackageRepository: PackageRepository {
    val safeAppPackagesLiveData: LiveData<Set<String>>
}