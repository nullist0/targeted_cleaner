package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.launch
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class SafeAppsEventHandlerImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: PackageRepository
) : SafeAppsEventHandler() {
    override fun add(app: AppInfo) {
        eventHandlerScope.launch {
            packageRepository.addSafeAppPackage(converter.convertToPackageName(app))
        }
    }

    override fun remove(app: AppInfo) {
        eventHandlerScope.launch {
            packageRepository.removeSafeAppPackage(converter.convertToPackageName(app))
        }
    }
}