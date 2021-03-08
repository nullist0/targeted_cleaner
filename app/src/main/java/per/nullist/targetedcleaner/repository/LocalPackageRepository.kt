package per.nullist.targetedcleaner.repository

import per.nullist.targetedcleaner.entity.PackageRepository

class LocalPackageRepository : PackageRepository {
    override fun readAllInstalledPackages(): List<String> {
        TODO("Not yet implemented")
    }

    override fun readAllPackagesNotToKill(): List<String> {
        TODO("Not yet implemented")
    }
}
