package per.nullist.targetedcleaner.view_model.converter

import android.content.Context
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import per.nullist.targetedcleaner.view_model.data.AppInfo

class AppInfoPackageConverterImpl(context: Context) : AppInfoPackageConverter {
    private val packageManager = context.packageManager

    override fun convertToAppInfo(packageName: String): AppInfo {
        return packageManager.run {
            val info = getApplicationInfo(packageName, 0)
            val name = getApplicationLabel(info).toString()
            val icon = getApplicationIcon(packageName).toBitmap().asImageBitmap()
            AppInfo(
                name,
                packageName,
                icon
            )
        }
    }

    override fun convertToPackageName(appInfo: AppInfo): String = appInfo.packageName
}