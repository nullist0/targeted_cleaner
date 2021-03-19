package per.nullist.targetedcleaner.entity

interface SettingRepository {
    fun turnOnService()
    fun turnOffService()

    var interval: Long
    var isRunning: Boolean
}
