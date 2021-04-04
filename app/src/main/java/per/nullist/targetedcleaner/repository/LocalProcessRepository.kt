package per.nullist.targetedcleaner.repository

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.getSystemService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import per.nullist.targetedcleaner.entity.ProcessRepository

class LocalProcessRepository(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ProcessRepository {

    private val activityManager by lazy { context.getSystemService<ActivityManager>() }
    private val packageManager by lazy { context.packageManager }

    override suspend fun killProcess(packageName: String) {
        withContext(dispatcher) {
            activityManager?.killBackgroundProcesses(packageName)
        }
    }

    override suspend fun getAllRunnablePackages(): List<String> {
        return withContext(dispatcher) {
            val intent = Intent().apply {
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
            val resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA)
            resolveInfos.map { it.activityInfo.packageName }

//            activityManager
//                ?.runningAppProcesses
//                ?.filter {
//                    when {
//                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
//                            it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_CACHED
//                        }
//                        else -> {
//                            it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND
//                        }
//                    }
//                }
//                ?.map { it.processName }
//                ?: listOf()
        }
    }
}
