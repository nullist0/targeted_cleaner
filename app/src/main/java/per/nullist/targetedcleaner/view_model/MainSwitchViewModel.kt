package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import per.nullist.targetedcleaner.entity.SettingRepository

abstract class MainSwitchViewModel : ViewModel() {
    abstract val isChecked: MutableLiveData<Boolean>

    abstract fun setIsChecked(isChecked: Boolean)
}

class MainSwitchViewModelImpl(
    private val repository: SettingRepository
) : MainSwitchViewModel() {

    override val isChecked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = repository.isRunning
        }
    }

    override fun setIsChecked(value: Boolean) {
        isChecked.value = value
        when (value) {
            true -> repository.turnOnService()
            false -> repository.turnOffService()
        }
    }
}