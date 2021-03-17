package per.nullist.targetedcleaner.view.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowScope.RatioExpanded(
    modifier: Modifier,
    ratio: Float = 1f,
    content: @Composable BoxScope.() -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.weight(1f)
    ) {
        Box(
            content = content,
            contentAlignment = Alignment.Center,
            modifier = modifier.size(
                maxWidth,
                maxWidth * ratio
            )
        )
    }
}