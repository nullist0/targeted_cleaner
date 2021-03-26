package per.nullist.targetedcleaner.component

import android.content.Context
import android.util.Log

import kotlin.reflect.KClass

typealias RouterExecutor = (context: Context, argument: Any?) -> Unit

object Router {
    private val routerMap = mutableMapOf<KClass<*>, RouterExecutor>()

    fun add(cls: KClass<*>, executor: RouterExecutor) {
        Log.d("MAIN", routerMap.toString())
        routerMap[cls] = executor
    }

    fun start(cls: KClass<*>, context: Context, argument: Any? = null) {
        Log.d("MAIN", routerMap.toString())
        routerMap[cls]?.invoke(context, argument)
    }
}
