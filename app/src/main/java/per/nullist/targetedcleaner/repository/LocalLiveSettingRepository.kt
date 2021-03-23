package per.nullist.targetedcleaner.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.core.content.edit

import per.nullist.targetedcleaner.component.receiver.AutoKillerReceiver
import per.nullist.targetedcleaner.livedata.LiveSettingRepository
import per.nullist.targetedcleaner.livedata.getBooleanLiveData
import per.nullist.targetedcleaner.livedata.getLongLiveData
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.IS_RUNNING
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.NAME
import per.nullist.targetedcleaner.repository.SharedPreferenceConfiguration.INTERVAL_IN_MIN

class LocalLiveSettingRepository(private val context: Context) : LiveSettingRepository {
    // TODO: it should not be defined in this file
    private val REQUEST_CODE = 10

    private val alarmManager by lazy { context.getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    private val instance by lazy { context.getSharedPreferences(NAME, Context.MODE_PRIVATE) }

    private inline fun <reified T> buildIntent(context: Context) : Intent = Intent(context, T::class.java)

    private fun buildPendingIntent(): PendingIntent {
        val intent = buildIntent<AutoKillerReceiver>(context)
        return PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override val isRunningLiveData: LiveData<Boolean>
        get() = instance.getBooleanLiveData(IS_RUNNING, false)
    override val intervalLiveData: LiveData<Long>
        get() = instance.getLongLiveData(INTERVAL_IN_MIN, 15 * 3600L)

    private fun toggleService(isRunning : Boolean) {
        if(isRunning) {
            alarmManager.run {
                val triggerAtMills = SystemClock.elapsedRealtime() + interval
                setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME,
                    triggerAtMills,
                    interval,
                    buildPendingIntent()
                )
            }
        } else {
            alarmManager.cancel(buildPendingIntent())
        }
    }

    override var interval : Long
        get() = instance.getLong(INTERVAL_IN_MIN, 0)
        set(value) { instance.edit { putLong(INTERVAL_IN_MIN, value) } }

    override var isRunning : Boolean
        get() = instance.getBoolean(IS_RUNNING, false)
        set(value) {
            toggleService(value)
            instance.edit { putBoolean(IS_RUNNING, value) }
        }
}