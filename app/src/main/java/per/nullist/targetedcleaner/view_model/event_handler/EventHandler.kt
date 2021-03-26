package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class EventHandler {
    val eventHandlerScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}