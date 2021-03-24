package per.nullist.targetedcleaner.repository

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.livedata.getStringSetLiveData
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.NAME
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.SAFE_APP_PACKAGES

class LocalLivePackageRepository(
    context: Context
) : LivePackageRepository {
    private val packageManager by lazy { context.packageManager }

    private val instance by lazy {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override var safeAppPackages : Set<String>
        get() = instance.getStringSet(SAFE_APP_PACKAGES, null) ?: setOf()
        set(value) { instance.edit { putStringSet(SAFE_APP_PACKAGES, value) } }

    override val safeAppPackagesLiveData: LiveData<Set<String>>
        get() = instance.getStringSetLiveData(SAFE_APP_PACKAGES, setOf())

    override val allInstalledPackages: Flow<List<String>> = flow {
             val intent = Intent().apply {
                 action = Intent.ACTION_MAIN
                 addCategory(Intent.CATEGORY_LAUNCHER)
             }
             val resolveInfos = packageManager
                 .queryIntentActivities(intent, PackageManager.GET_META_DATA)
             val packages = resolveInfos.map { it.activityInfo.packageName }
             emit(packages)
         }.flowOn(Dispatchers.IO)
}