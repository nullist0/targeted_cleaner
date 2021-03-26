package per.nullist.targetedcleaner.entity

interface SettingRepository {
    suspend fun getInterval() : Long
    suspend fun setInterval(interval: Long)

    suspend fun getIsRunning() : Boolean
    suspend fun setIsRunning(isRunning: Boolean)
}
