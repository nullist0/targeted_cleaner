package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

class SafeAppsEventHandlerImpl(
    private val converter: AppInfoPackageConverter,
    private val packageRepository: PackageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : SafeAppsEventHandler() {
    override fun add(app: AppInfo) {
        eventHandlerScope.launch(dispatcher) {
            packageRepository.addSafeAppPackage(converter.convertToPackageName(app))
        }
    }

    override fun remove(app: AppInfo) {
        eventHandlerScope.launch(dispatcher) {
            packageRepository.removeSafeAppPackage(converter.convertToPackageName(app))
        }
    }
}