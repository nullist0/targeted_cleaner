package per.nullist.targetedcleaner.component

import android.content.Context

import kotlin.reflect.KClass

typealias RouterExecutor = (context: Context, argument: Any?) -> Unit

object Router {
    private val routerMap = mutableMapOf<KClass<*>, RouterExecutor>()

    fun add(cls: KClass<*>, executor: RouterExecutor) {
        routerMap[cls] = executor
    }

    fun start(cls: KClass<*>, context: Context, argument: Any? = null) {
        routerMap[cls]?.invoke(context, argument)
    }
}
