package per.nullist.targetedcleaner.view_model.event_handler

abstract class MainSwitchEventHandler: EventHandler() {
    abstract fun setIsRunning(isRunning: Boolean)
}

