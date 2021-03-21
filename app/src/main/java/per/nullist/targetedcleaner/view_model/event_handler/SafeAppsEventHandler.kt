package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.view_model.data.AppInfo

interface SafeAppsEventHandler {
    fun add(info: AppInfo)
    fun remove(info: AppInfo)
}

