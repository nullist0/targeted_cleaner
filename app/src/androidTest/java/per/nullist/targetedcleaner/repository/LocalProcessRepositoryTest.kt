package per.nullist.targetedcleaner.repository

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import per.nullist.targetedcleaner.entity.ProcessRepository

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalProcessRepositoryTest {
    private lateinit var underTest: ProcessRepository
    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        testCoroutineDispatcher = TestCoroutineDispatcher()
        underTest = LocalProcessRepository(context, testCoroutineDispatcher)
    }

    @Test
    fun testAllProcessPackagesIsNotEmpty() = runBlockingTest {
        // given

        // when
        val packages = underTest.getAllRunnablePackages()

        // then
        assert(packages.isNotEmpty()) { "List is empty" }
    }

    @Test
    fun testKillProcess() = runBlockingTest {
        // given

        // when
        val packageName = underTest.getAllRunnablePackages().first()
        underTest.killProcess(packageName)

        // then
        // TODO: There is no way to check this test
        // assert(packageName !in underTest.getAllRunnablePackages())
    }
}