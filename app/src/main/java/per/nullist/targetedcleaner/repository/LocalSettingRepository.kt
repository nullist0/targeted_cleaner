package per.nullist.targetedcleaner.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import per.nullist.targetedcleaner.component.receiver.AutoKillerReceiver
import per.nullist.targetedcleaner.entity.SettingRepository

class LocalSettingRepository(private val context: Context) : SettingRepository {
    // TODO: it should not be defined in this file
    private val REQUEST_CODE = 10

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private fun getInterval() : Long =
        SharedPreferenceHelper
            .instance(context)
            .run { getLong("interval", 0L) }

    private inline fun <reified T> buildIntent(context: Context) : Intent = Intent(context, T::class.java)

    private fun buildPendingIntent(): PendingIntent {
        val intent = buildIntent<AutoKillerReceiver>(context)
        return PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun setTimer(milliInterval: Int) {
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
    }

    override fun deleteTimer() = alarmManager.cancel(buildPendingIntent())
}