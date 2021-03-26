package per.nullist.targetedcleaner.main.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking
import per.nullist.targetedcleaner.entity.ProcessKiller
import per.nullist.targetedcleaner.repository.LocalLivePackageRepository
import per.nullist.targetedcleaner.repository.LocalProcessRepository

class AutoKillerReceiver : BroadcastReceiver() {

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