package per.nullist.targetedcleaner.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import per.nullist.targetedcleaner.main.receiver.AutoKillerReceiver
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
        get() = instance.getLongLiveData(INTERVAL_IN_MIN, 15 * 60000L)

    override suspend fun getInterval(): Long {
        return withContext(Dispatchers.IO) { instance.getLong(INTERVAL_IN_MIN, 0) }
    }

    override suspend fun setInterval(interval: Long) {
        withContext(Dispatchers.IO) {
            instance.edit { putLong(INTERVAL_IN_MIN, interval) }
        }
    }

    override suspend fun getIsRunning(): Boolean {
        return withContext(Dispatchers.IO) { instance.getBoolean(IS_RUNNING, false) }
    }

    override suspend fun setIsRunning(isRunning: Boolean) {
        withContext(Dispatchers.IO) {
            toggleService(isRunning)
            instance.edit { putBoolean(IS_RUNNING, isRunning) }
        }
    }

    private suspend fun toggleService(isRunning : Boolean) {
        if(isRunning) {
            alarmManager.run {
                val interval = getInterval()
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
}