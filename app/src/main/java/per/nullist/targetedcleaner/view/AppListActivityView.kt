package per.nullist.targetedcleaner.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import per.nullist.targetedcleaner.view_model.AppListViewModel
import per.nullist.targetedcleaner.view_model.data.AppInfo
import per.nullist.targetedcleaner.view_model.event_handler.SafeAppsEventHandler

@Composable
fun AppInfoItem(
    app: AppInfo,
    isSafe: Boolean,
    onChangeChecked: (Boolean) -> Unit
) {
    Row (
        Modifier.clickable { onChangeChecked(!isSafe) }
    ) {
        Text(app.name)
        Checkbox(isSafe, onChangeChecked)
    }
}

@Composable
fun AppList(
    apps: List<AppInfo>,
    safeApps: Set<AppInfo>,
    onChangeItemChecked: (Boolean, AppInfo) -> Unit
) {
    LazyColumn() {
        items(apps) { app ->
            AppInfoItem(
                app,
                safeApps.contains(app)
            ) { isSafe ->
                onChangeItemChecked(isSafe, app)
            }
        }
    }
}

@Composable
fun AppListActivityView(
    model: AppListViewModel,
    eventHandler: SafeAppsEventHandler
) {
    val apps by model.allInstalledApps.observeAsState()
    val safeApps by model.safeApps.observeAsState()

    AppList(
        apps?.sortedBy { it.name } ?: listOf(),
        safeApps ?: setOf()
    ) { isSafe, app ->
        when {
            isSafe -> eventHandler.add(app)
            else -> eventHandler.remove(app)
        }
    }
}
