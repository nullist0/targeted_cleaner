package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.*

import per.nullist.targetedcleaner.entity.SettingRepository

class MainSwitchEventHandlerImpl(
    private val settingRepository: SettingRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MainSwitchEventHandler() {
    override fun setIsRunning(isRunning: Boolean) {
        eventHandlerScope.launch(dispatcher) {
            settingRepository.setIsRunning(isRunning)
        }
    }
}