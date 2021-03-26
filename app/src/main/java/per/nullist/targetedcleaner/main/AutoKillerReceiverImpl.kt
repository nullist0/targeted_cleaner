package per.nullist.targetedcleaner.main

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking
import per.nullist.targetedcleaner.entity.ProcessKiller
import per.nullist.targetedcleaner.repository.LocalLivePackageRepository
import per.nullist.targetedcleaner.repository.LocalProcessRepository
import per.nullist.targetedcleaner.component.AutoKillerReceiver

class AutoKillerReceiverImpl : AutoKillerReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val killer = ProcessKiller(
            LocalProcessRepository(context),
            LocalLivePackageRepository(context)
        )
        runBlocking {
            killer.killAll()
        }
    }
}