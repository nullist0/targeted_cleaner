package per.nullist.targetedcleaner.view_model

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.intent.IntentStubberRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.PackageRepository

@RunWith(AndroidJUnit4::class)
class AppListViewModelTest {

    private lateinit var underTest : AppListViewModel

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()!!

    @Mock
    private lateinit var packageRepository: PackageRepository

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        underTest = AppListViewModel(
            appContext.packageManager,
            packageRepository
        )
    }

    @Test
    fun testTest() {
        // given
        val packageName = "packageTest.app"

        // when
        underTest.addSafeApp(packageName)

        // then
    }
}