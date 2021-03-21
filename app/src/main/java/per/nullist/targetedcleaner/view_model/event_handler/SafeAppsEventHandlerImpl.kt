package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class SafeAppsEventHandlerImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: PackageRepository
) : SafeAppsEventHandler {
    override fun add(info: AppInfo) {
        packageRepository.safeAppPackages += info.packageName
    }

    override fun remove(info: AppInfo) {
        packageRepository.safeAppPackages -= info.packageName
    }
}