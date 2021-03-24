package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import per.nullist.targetedcleaner.livedata.LiveSettingRepository
import per.nullist.targetedcleaner.view_model.converter.IntervalConverter

class TimerSettingViewModelImpl(
    private val intervalConverter: IntervalConverter,
    settingRepository: LiveSettingRepository
) : TimerSettingViewModel() {
    override val interval: LiveData<Int> by lazy {
        settingRepository.intervalLiveData.map { intervalConverter.convertToMin(it) }
    }
}