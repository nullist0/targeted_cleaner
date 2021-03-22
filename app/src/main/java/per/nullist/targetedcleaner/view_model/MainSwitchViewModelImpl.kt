package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import per.nullist.targetedcleaner.livedata.LiveSettingRepository

class MainSwitchViewModelImpl(
    settingRepository: LiveSettingRepository
) : MainSwitchViewModel() {
    override val isRunning: LiveData<Boolean> by lazy {
        settingRepository.isRunningLiveData
    }
}