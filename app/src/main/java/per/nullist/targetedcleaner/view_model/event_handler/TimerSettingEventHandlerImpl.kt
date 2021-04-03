package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import per.nullist.targetedcleaner.entity.SettingRepository
import per.nullist.targetedcleaner.view_model.converter.IntervalConverter

class TimerSettingEventHandlerImpl(
    private val converter: IntervalConverter,
    private val settingRepository: SettingRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : TimerSettingEventHandler() {
    override fun setInterval(interval: Int) {
        eventHandlerScope.launch(dispatcher) {
            settingRepository.setInterval(converter.convertToMillis(interval))
        }
    }
}
