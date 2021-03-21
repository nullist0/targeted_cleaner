package per.nullist.targetedcleaner.view_model.event_handler

import per.nullist.targetedcleaner.entity.SettingRepository

class MainSwitchEventHandlerImpl(
    private val settingRepository: SettingRepository
) : MainSwitchEventHandler {
    override fun setIsRunning(isRunning: Boolean) {
        settingRepository.isRunning = isRunning
    }
}