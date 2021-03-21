package per.nullist.targetedcleaner.view_model

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.PackageRepository

class AppListViewModelTest {

    private lateinit var underTest : AppListViewModel

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()!!

    @Mock
    private lateinit var packageRepository: PackageRepository

    @Before
    fun setUp() {

    }

    @Test
    fun testTest() {
        // given
        val packageName = "packageTest.app"

        // when
//        underTest.addSafeApp(packageName)

        // then
    }
}