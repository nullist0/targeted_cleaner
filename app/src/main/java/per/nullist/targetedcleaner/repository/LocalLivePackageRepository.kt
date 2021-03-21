package per.nullist.targetedcleaner.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import per.nullist.targetedcleaner.livedata.LivePackageRepository
import per.nullist.targetedcleaner.livedata.getStringSetLiveData
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.NAME
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.SAFE_APP_PACKAGES

class LocalLivePackageRepository(
    context: Context
) : LivePackageRepository {
    private val packageManager = context.packageManager

    private val instance : SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    override var safeAppPackages : Set<String>
        get() = instance.getStringSet(SAFE_APP_PACKAGES, null) ?: setOf()
        set(value) { instance.edit { putStringSet(SAFE_APP_PACKAGES, value) } }

    override val safeAppPackagesLiveData: LiveData<Set<String>>
        get() = instance.getStringSetLiveData(SAFE_APP_PACKAGES, setOf())

    override val allInstalledPackages: List<String>
        get() = packageManager
            .getInstalledApplications(0)
            .map { it.packageName }
}
