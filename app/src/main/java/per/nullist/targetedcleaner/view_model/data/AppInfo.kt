package per.nullist.targetedcleaner.view_model.data

import androidx.compose.ui.graphics.ImageBitmap

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: ImageBitmap
) {
    override fun equals(other: Any?): Boolean {
        if(other is AppInfo) {
            return other.packageName == packageName
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return packageName.hashCode()
    }
}
