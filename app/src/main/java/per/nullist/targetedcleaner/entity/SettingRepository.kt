package per.nullist.targetedcleaner.entity

interface SettingRepository {
    var interval: Long
    var isRunning: Boolean
}
