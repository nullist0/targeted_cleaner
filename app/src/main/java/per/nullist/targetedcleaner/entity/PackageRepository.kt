package per.nullist.targetedcleaner.entity

import kotlinx.coroutines.flow.Flow

interface PackageRepository {
    val allInstalledPackages : Flow<List<String>>
    var safeAppPackages : Set<String>
}
