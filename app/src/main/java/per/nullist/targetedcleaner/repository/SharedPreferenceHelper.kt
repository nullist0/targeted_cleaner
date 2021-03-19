package per.nullist.targetedcleaner.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceHelper(private val context: Context) {
    private val instance : SharedPreferences by lazy {
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    var safeAppPackages : Set<String>
        get() = instance.getStringSet(SAFE_APP_PACKAGES, null) ?: setOf()
        set(value) { instance.edit { putStringSet(SAFE_APP_PACKAGES, value) } }

    var interval : Long
        get() = instance.getLong(TIMER_SETTING, 0L)
        set(value) { instance.edit { putLong(TIMER_SETTING, value) } }

    var isRunning : Boolean
        get() = instance.getBoolean(IS_RUNNING, false)
        set(value) { instance.edit { putBoolean(IS_RUNNING, value) } }

    companion object {
        private const val NAME = "TargetedCleanerPreferences"

        private const val SAFE_APP_PACKAGES = "SafeAppPackages"
        private const val TIMER_SETTING = "TimerSetting"
        private const val IS_RUNNING = "IsRunning"
    }
}