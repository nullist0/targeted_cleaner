package per.nullist.targetedcleaner.livedata

import androidx.lifecycle.LiveData
import per.nullist.targetedcleaner.entity.SettingRepository

interface LiveSettingRepository: SettingRepository {
    val isRunningLiveData: LiveData<Boolean>
    val intervalLiveData: LiveData<Long>
}