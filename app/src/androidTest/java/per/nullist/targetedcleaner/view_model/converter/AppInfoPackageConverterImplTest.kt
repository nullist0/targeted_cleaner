package per.nullist.targetedcleaner.view_model.converter

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.view_model.data.AppInfo

@RunWith(AndroidJUnit4::class)
class AppInfoPackageConverterImplTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var underTest: AppInfoPackageConverterImpl

    private lateinit var context : Context

    private lateinit var icon: ImageBitmap

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        underTest = AppInfoPackageConverterImpl(context)
        icon = mock(ImageBitmap::class.java)
    }

    @Test
    fun testConvertToAppInfo() {
        // given
        val packageName = context.packageName

        // when
        val appInfo = underTest.convertToAppInfo(packageName)

        // then
        assert(appInfo.name == "Targeted Cleaner") {
            "app name is not matched"
        }
        assert(appInfo.packageName == context.packageName) {
            "app package name is not matched"
        }
    }

    @Test
    fun testConvertToPackageName() {
        // given
        val appInfo = AppInfo(
            "Targeted Cleaner",
            context.packageName,
            icon
        )

        // when
        val packageName = underTest.convertToPackageName(appInfo)

        // then
        assert(packageName == context.packageName) {
            "app package name is not matched"
        }
    }
}