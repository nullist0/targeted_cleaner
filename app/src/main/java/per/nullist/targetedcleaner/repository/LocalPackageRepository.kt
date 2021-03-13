package per.nullist.targetedcleaner.repository

import android.content.Context
import per.nullist.targetedcleaner.entity.PackageRepository

class LocalPackageRepository(private val context: Context) : PackageRepository {
    private val packageManager = context.packageManager

    override fun readAllInstalledPackages(): List<String> = packageManager
        .getInstalledApplications(0)
        .map { it.packageName }

    override fun readAllPackagesNotToKill(): List<String> {
        return SharedPreferenceHelper
            .instance(context)
            .getStringSet("notToKillPackages", setOf<String>())
            ?.toList()
            ?: listOf()
    }
}
