package per.nullist.targetedcleaner.repository

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService
import per.nullist.targetedcleaner.entity.ProcessRepository

class LocalProcessRepository(context: Context): ProcessRepository {

    private val activityManager = context.getSystemService<ActivityManager>()

    override val allProcessPackages: List<String> get() =
        activityManager
            ?.runningAppProcesses
            ?.filter {
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                        it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_CACHED
                    }
                    else -> {
                        it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND
                    }
                }
            }
            ?.map { it.processName }
            ?: listOf()

    override fun killProcess(packageName: String) {
        activityManager?.killBackgroundProcesses(packageName)
    }
}
