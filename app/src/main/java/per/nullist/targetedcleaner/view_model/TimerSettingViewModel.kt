package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.nullist.targetedcleaner.entity.SettingRepository

abstract class TimerSettingViewModel: ViewModel() {
    abstract val timer : MutableLiveData<Long>

    abstract fun changeTimer(interval: Long)
}

class TimerSettingViewModelImpl(
    private val repository: SettingRepository
) : TimerSettingViewModel() {
    override val timer: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>().apply {
            value = repository.interval
        }
    }

    override fun changeTimer(interval: Long) {
        timer.value = interval
        repository.interval = interval
    }
}
