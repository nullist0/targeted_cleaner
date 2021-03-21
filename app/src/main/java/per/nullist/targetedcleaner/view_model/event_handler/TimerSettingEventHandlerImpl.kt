package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.entity.SettingRepository

class TimerSettingEventHandlerImpl(
    private val settingRepository: SettingRepository
) : TimerSettingEventHandler {
    override fun setInterval(intervalInMin: Int) {
        settingRepository.interval = intervalInMin * MillisPerMin
    }

    companion object {
        private const val MillisPerMin = 3600L
    }
}
