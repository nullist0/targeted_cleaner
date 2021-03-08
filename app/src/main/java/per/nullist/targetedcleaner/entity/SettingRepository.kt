package per.nullist.targetedcleaner.entity

interface SettingRepository {
    fun setTimer(milliInterval: Int)
    fun deleteTimer()
}
