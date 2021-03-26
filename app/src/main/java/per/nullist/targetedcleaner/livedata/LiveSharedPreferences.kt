package per.nullist.targetedcleaner.livedata

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

//fun SharedPreferences.getIntLiveData(key: String, defValue: Int): LiveData<Int> {
//    return LiveSharedPreferences (this, get = { getInt(key, defValue) })
//}
fun SharedPreferences.getLongLiveData(key: String, defValue: Long): LiveData<Long> {
    return LiveSharedPreferences (this, get = { getLong(key, defValue) })
}
//fun SharedPreferences.getStringLiveData(key: String, defValue: String): LiveData<String> {
//    return LiveSharedPreferences (this, get = { getString(key, defValue) ?: defValue })
//}
fun SharedPreferences.getStringSetLiveData(key: String, defValue: Set<String>): LiveData<Set<String>> {
    return LiveSharedPreferences (this, get = { getStringSet(key, defValue) ?: defValue })
}
//fun SharedPreferences.getFloatLiveData(key: String, defValue: Float): LiveData<Float> {
//    return LiveSharedPreferences (this, get = { getFloat(key, defValue) })
//}
fun SharedPreferences.getBooleanLiveData(key: String, defValue: Boolean): LiveData<Boolean> {
    return LiveSharedPreferences (this, get = { getBoolean(key, defValue) })
}

class LiveSharedPreferences<T>(
    private val preferences: SharedPreferences,
    private val get: SharedPreferences.() -> T,
) : LiveData<T>(preferences.get()) {
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        value = preferences.get()
    }

    override fun onActive() {
        super.onActive()
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
