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

@Composable
fun AppInfoItem(
    info: AppInfo,
    checked: Boolean,
    onChangeChecked: (Boolean) -> Unit
) {
    Row (
        Modifier.clickable { onChangeChecked(!checked) }
    ) {
        Text(info.name)
        Checkbox(checked, onChangeChecked)
    }
}

@Composable
fun AppList(
    infoList: List<AppInfo>,
    checkedSet: Set<String>,
    onChangeItemChecked: (Boolean, String) -> Unit
) {
    LazyColumn() {
        items(infoList) {
            AppInfoItem(
                it,
                it.packageName in checkedSet
            ) { checked ->
                onChangeItemChecked(checked, it.packageName)
            }
        }
    }
}

@Composable
fun AppListActivityView(
    model: AppListViewModel
) {
    val apps by model.apps.observeAsState()
    val checkedSet by model.safeApps.observeAsState()

    AppList(
        apps ?: listOf(),
        checkedSet ?: setOf()
    ) { checked, packageName ->
        when {
            checked -> model.addSafeApp(packageName)
            else -> model.removeSafeApp(packageName)
        }
    }
}