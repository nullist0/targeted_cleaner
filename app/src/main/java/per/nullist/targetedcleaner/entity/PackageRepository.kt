package per.nullist.targetedcleaner.entity

interface PackageRepository {
    val allInstalledPackages : List<String>
    var safeAppPackages : Set<String>
}
