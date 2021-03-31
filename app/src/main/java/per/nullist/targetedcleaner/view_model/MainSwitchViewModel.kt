package per.nullist.targetedcleaner.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * Data binder class for Switch Compose for MainActivityView
 */
abstract class MainSwitchViewModel : ViewModel() {
    abstract val isRunning: LiveData<Boolean>
}