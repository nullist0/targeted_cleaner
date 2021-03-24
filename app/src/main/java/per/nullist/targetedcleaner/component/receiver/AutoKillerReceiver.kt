package per.nullist.targetedcleaner.component.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AutoKillerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // TODO("AutoKillerReceiver.onReceive() is not implemented")
        Log.d("MAIN", "RECEIVED")
    }
}