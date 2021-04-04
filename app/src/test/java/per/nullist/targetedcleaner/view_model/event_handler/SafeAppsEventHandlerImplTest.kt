package per.nullist.targetedcleaner.view_model.event_handler

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.PackageRepository
import per.nullist.targetedcleaner.view_model.converter.AppInfoPackageConverter
import per.nullist.targetedcleaner.view_model.data.AppInfo

@ExperimentalCoroutinesApi
class SafeAppsEventHandlerImplTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    private lateinit var underTest: SafeAppsEventHandler

    private lateinit var converter: AppInfoPackageConverter
    private lateinit var packageRepository: PackageRepository

    private lateinit var icon: ImageBitmap

    @Before
    fun setUp() {
        converter = mock(AppInfoPackageConverter::class.java)
        packageRepository = mock(PackageRepository::class.java)
        icon = mock(ImageBitmap::class.java)

        testCoroutineDispatcher = TestCoroutineDispatcher()

        underTest = SafeAppsEventHandlerImpl(converter, packageRepository, testCoroutineDispatcher)
    }

    @Test
    fun testAdd() = runBlockingTest {
        // given
        val app = AppInfo(
            "Targeted Cleaner",
            "per.nullist.targetedcleaner",
            icon
        )

        given(converter.convertToPackageName(app)).willReturn("per.nullist.targetedcleaner")

        // when
        underTest.add(app)

        // then
        verify(packageRepository).addSafeAppPackage("per.nullist.targetedcleaner")
    }

    @Test
    fun testRemove() = runBlockingTest {
        // given
        val app = AppInfo(
            "Targeted Cleaner",
            "per.nullist.targetedcleaner",
            icon
        )
        given(converter.convertToPackageName(app)).willReturn("per.nullist.targetedcleaner")

        // when
        underTest.remove(app)

        // then
        verify(packageRepository).removeSafeAppPackage("per.nullist.targetedcleaner")
    }
}