package per.nullist.targetedcleaner.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import per.nullist.targetedcleaner.repository.LocalLivePackageRepository
import per.nullist.targetedcleaner.repository.LocalLiveSettingRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverterImpl

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.run {
            when {
                isAssignableFrom(AppListViewModel::class.java) -> {
                    AppListViewModelImpl(
                        AppInfoPackageConverterImpl(application.applicationContext),
                        LocalLivePackageRepository(application.applicationContext)
                    ) as T
                }
                isAssignableFrom(MainSwitchViewModel::class.java) -> {
                    MainSwitchViewModelImpl(
                        LocalLiveSettingRepository(application.applicationContext)
                    ) as T
                }
                isAssignableFrom(TimerSettingViewModel::class.java) -> {
                    TimerSettingViewModelImpl(
                        LocalLiveSettingRepository(application.applicationContext)
                    ) as T
                }
                else -> {
                    super.create(modelClass)
                }
            }
        }
    }
}