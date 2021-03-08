package per.nullist.targetedcleaner.repository

import android.app.AlarmManager
import android.content.Context
import per.nullist.targetedcleaner.entity.SettingRepository

class LocalSettingRepository(private val context: Context) : SettingRepository {
    override fun setTimer(milliInterval: Int) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    }

    override fun deleteTimer() {
        TODO("Not yet implemented")
    }
}
