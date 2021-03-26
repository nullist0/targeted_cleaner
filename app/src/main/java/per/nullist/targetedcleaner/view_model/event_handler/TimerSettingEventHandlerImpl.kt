package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.launch
import per.nullist.targetedcleaner.entity.SettingRepository
import per.nullist.targetedcleaner.view_model.converter.IntervalConverter

class TimerSettingEventHandlerImpl(
    private val converter: IntervalConverter,
    private val settingRepository: SettingRepository
) : TimerSettingEventHandler() {
    override fun setInterval(interval: Int) {
        eventHandlerScope.launch {
            settingRepository.setInterval(converter.convertToMillis(interval))
        }
    }
}
