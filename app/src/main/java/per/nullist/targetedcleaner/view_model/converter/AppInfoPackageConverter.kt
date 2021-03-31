package per.nullist.targetedcleaner.view_model.converter

import per.nullist.targetedcleaner.view_model.data.AppInfo

interface AppInfoPackageConverter {
    fun convertToAppInfo(packageName: String) : AppInfo
    fun convertToPackageName(appInfo: AppInfo) : String
}