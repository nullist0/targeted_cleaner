package per.nullist.targetedcleaner.repository

import android.content.Context
import per.nullist.targetedcleaner.entity.PackageRepository

class LocalPackageRepository(private val context: Context) : PackageRepository {
    private val packageManager = context.packageManager
    private val helper = SharedPreferenceHelper(context)

    override val allInstalledPackages: List<String>
        get() = packageManager
            .getInstalledApplications(0)
            .map { it.packageName }

    override var safeAppPackages: Set<String>
        get() = helper.safeAppPackages
        set(value) { helper.safeAppPackages = value }
}
