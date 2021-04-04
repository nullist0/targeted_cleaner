package per.nullist.targetedcleaner.repository

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.livedata.getStringSetLiveData
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.NAME
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.SAFE_APP_PACKAGES

class LocalLivePackageRepository(
    context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LivePackageRepository {
    private val packageManager by lazy { context.packageManager }

    private val instance by lazy {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override val safeAppPackagesLiveData: LiveData<Set<String>>
        get() = instance.getStringSetLiveData(SAFE_APP_PACKAGES, setOf())

    override suspend fun getAllInstalledPackages(): List<String> {
        return withContext(dispatcher) {
            val intent = Intent().apply {
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
            val resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA)
            resolveInfos.map { it.activityInfo.packageName }
        }
    }

    override suspend fun getSafeAppPackages(): Set<String> {
        return withContext(dispatcher) {
            instance.getStringSet(SAFE_APP_PACKAGES, setOf()) ?: setOf()
        }
    }

    private fun setSafeAppPackages(safeApps: Set<String>) {
        instance.edit { putStringSet(SAFE_APP_PACKAGES, safeApps) }
    }

    override suspend fun addSafeAppPackage(packageName: String) {
        withContext(dispatcher) {
            val packages = getSafeAppPackages()
            setSafeAppPackages(packages + packageName)
        }
    }

    override suspend fun removeSafeAppPackage(packageName: String) {
        withContext(dispatcher) {
            val packages = getSafeAppPackages()
            setSafeAppPackages(packages - packageName)
        }
    }
}