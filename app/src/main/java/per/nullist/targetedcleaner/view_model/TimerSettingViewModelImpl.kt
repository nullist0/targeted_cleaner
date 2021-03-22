package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import per.nullist.targetedcleaner.livedata.LiveSettingRepository

class TimerSettingViewModelImpl(
    settingRepository: LiveSettingRepository
) : TimerSettingViewModel() {
    override val interval: LiveData<Long> by lazy {
        settingRepository.intervalLiveData
    }
}