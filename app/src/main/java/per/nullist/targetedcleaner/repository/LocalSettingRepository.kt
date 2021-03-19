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
    private val helper = SharedPreferenceHelper(context)

    private inline fun <reified T> buildIntent(context: Context) : Intent = Intent(context, T::class.java)

    private fun buildPendingIntent(): PendingIntent {
        val intent = buildIntent<AutoKillerReceiver>(context)
        return PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun turnOnService() {
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
        }
    }

    override fun turnOffService() = alarmManager.cancel(buildPendingIntent())

    override var interval: Long
        get() = helper.interval
        set(value) {
            helper.apply {
                interval = value
                when(isRunning) {
                    true -> turnOnService()
                    false -> turnOffService()
                }
            }
        }
    override var isRunning: Boolean
        get() = helper.isRunning
        set(value) { helper.isRunning = value }
}