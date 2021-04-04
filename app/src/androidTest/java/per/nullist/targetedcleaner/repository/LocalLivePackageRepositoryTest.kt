package per.nullist.targetedcleaner.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import per.nullist.targetedcleaner.getOrAwaitValue
import per.nullist.targetedcleaner.livedata.LivePackageRepository

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalLivePackageRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var underTest: LivePackageRepository
    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        testCoroutineDispatcher = TestCoroutineDispatcher()
        underTest = LocalLivePackageRepository(context, testCoroutineDispatcher)
    }

    @Test
    fun testAllInstalledPackagesIsNotEmpty() = runBlockingTest {
        // given

        // when
        val allInstalledPackages = underTest.getAllInstalledPackages()

        // then
        assert(allInstalledPackages.isNotEmpty()) { "There is no installed packages." }
    }

    @Test
    fun testAddPackage() = runBlockingTest {
        // given
        val packageName = context.packageName

        // when
        underTest.addSafeAppPackage(packageName)

        // then
        assert(packageName in underTest.getSafeAppPackages())
    }

    @Test
    fun testRemovePackage() = runBlockingTest {
        // given
        val packageName = context.packageName

        // when
        underTest.removeSafeAppPackage(packageName)

        // then
        assert(packageName !in underTest.getSafeAppPackages())
    }

    @Test
    fun testSafeAppLiveData() = runBlockingTest {
        // given
        val addedPackageName = context.packageName
        val safeAppPackages : Set<String>?
        val observer: (Set<String>) -> Unit = {}

        try {
            // when
            underTest.safeAppPackagesLiveData.observeForever(observer)
            underTest.addSafeAppPackage(addedPackageName)

            safeAppPackages = underTest.safeAppPackagesLiveData.getOrAwaitValue()

            // then
            assert(addedPackageName in safeAppPackages) {
                "$safeAppPackages doesn't contain $addedPackageName"
            }
        } finally {
            underTest.safeAppPackagesLiveData.removeObserver(observer)
        }
    }
}