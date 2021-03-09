package per.nullist.targetedcleaner.repository

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceHelper {
    private const val NAME = "TargetedCleanerPreferences"
    fun instance(context: Context) : SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
}