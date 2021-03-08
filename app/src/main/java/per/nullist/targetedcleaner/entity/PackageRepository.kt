package per.nullist.targetedcleaner.entity

interface PackageRepository {
    fun readAllInstalledPackages() : List<String>
    fun readAllPackagesNotToKill() : List<String>
}
