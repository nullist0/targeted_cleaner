package per.nullist.targetedcleaner.view_model.event_handler

abstract class TimerSettingEventHandler: EventHandler() {
    abstract fun setInterval(interval: Int)
}

