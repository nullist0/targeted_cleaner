package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.view_model.data.AppInfo

abstract class SafeAppsEventHandler: EventHandler() {
    abstract fun add(app: AppInfo)
    abstract fun remove(app: AppInfo)
}

