package per.nullist.targetedcleaner.entity

interface PackageRepository {
    suspend fun getAllInstalledPackages() : List<String>

    suspend fun getSafeAppPackages() : Set<String>
    suspend fun setSafeAppPackages(safeApps: Set<String>)

    suspend fun addSafeAppPackage(packageName: String)
    suspend fun removeSafeAppPackage(packageName: String)
}
