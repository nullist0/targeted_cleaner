package per.nullist.targetedcleaner.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import per.nullist.targetedcleaner.repository.LocalLivePackageRepository
import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverterImpl
import per.nullist.targetedcleaner.view_model.converter.IntervalConverterImpl

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.d("VMF", modelClass.name)

        return modelClass.run {
            when {
                isAssignableFrom(AppListViewModel::class.java) -> {
                    Log.d("VMF", "AppListViewModel")
                    AppListViewModelImpl(
                        AppInfoPackageConverterImpl(application.applicationContext),
                        LocalLivePackageRepository(application.applicationContext)
                    ) as T
                }
                isAssignableFrom(MainSwitchViewModel::class.java) -> {
                    Log.d("VMF", "MainSwitchViewModel")
                    MainSwitchViewModelImpl(
                        LocalLiveSettingRepository(application.applicationContext)
                    ) as T
                }
                isAssignableFrom(TimerSettingViewModel::class.java) -> {
                    Log.d("VMF", "TimerSettingViewModel")
                    TimerSettingViewModelImpl(
                        IntervalConverterImpl(),
                        LocalLiveSettingRepository(application.applicationContext)
                    ) as T
                }
                else -> {
                    Log.d("VMF", "Other")
                    super.create(modelClass)
                }
            }
        }
    }
}