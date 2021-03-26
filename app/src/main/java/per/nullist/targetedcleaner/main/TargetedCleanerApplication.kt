package per.nullist.targetedcleaner.main

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import per.nullist.targetedcleaner.component.*

private const val AUTO_KILLER_REQUEST_CODE = 100

private inline fun <reified T: Activity> startActivity(context: Context) {
    context.startActivity(Intent(context, T::class.java))
}

private inline fun <reified T: BroadcastReceiver> registerBroadcastReceiver(context: Context, code: Int, interval: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, T::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager.setInexactRepeating(
        AlarmManager.ELAPSED_REALTIME,
        SystemClock.elapsedRealtime() + interval,
        interval,
        pendingIntent
    )
}

private inline fun <reified T: BroadcastReceiver> unregisterBroadcastReceiver(context: Context, code: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, T::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager.cancel(pendingIntent)
}

class TargetedCleanerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Router.add(AppListActivity::class) { context, _ ->
            startActivity<AppListActivityImpl>(context)
        }

        Router.add(MainActivity::class) { context, _ ->
            startActivity<MainActivityImpl>(context)
        }

        Router.add(TimerSettingActivity::class) { context, _ ->
            startActivity<TimerSettingActivityImpl>(context)
        }

        Router.add(AutoKillerReceiver::class) { context, argument ->
            when(argument) {
                null -> unregisterBroadcastReceiver<AutoKillerReceiverImpl>(context, AUTO_KILLER_REQUEST_CODE)
                else -> registerBroadcastReceiver<AutoKillerReceiverImpl>(context, AUTO_KILLER_REQUEST_CODE, argument as Long)
            }
        }
    }
}