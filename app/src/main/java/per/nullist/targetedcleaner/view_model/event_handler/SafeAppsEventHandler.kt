package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.view_model.data.AppInfo

interface SafeAppsEventHandler {
    fun add(app: AppInfo)
    fun remove(app: AppInfo)
}

