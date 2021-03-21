package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class TimerSettingViewModel: ViewModel() {
    abstract val timer : LiveData<Long>
}