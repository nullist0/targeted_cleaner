package per.nullist.targetedcleaner.repository

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
import per.nullist.targetedcleaner.livedata.LiveSettingRepository

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalLiveSettingRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var underTest : LiveSettingRepository
    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testCoroutineDispatcher = TestCoroutineDispatcher()

        underTest = LocalLiveSettingRepository(context, testCoroutineDispatcher)
    }

    @Test
    fun testSetIsRunningTrue() = runBlockingTest {
        // given
        val isRunning = true

        // when
        underTest.setIsRunning(isRunning)

        // then
        assert(underTest.getIsRunning())
    }

    @Test
    fun testSetIsRunningFalse() = runBlockingTest {
        // given
        val isRunning = false

        // when
        underTest.setIsRunning(isRunning)

        // then
        assert(!underTest.getIsRunning())
    }

    @Test
    fun testSetInterval() = runBlockingTest {
        // given
        val intervalInMillis = 60000L

        // when
        underTest.setInterval(intervalInMillis)

        // then
        assert(underTest.getInterval() == 60000L)
    }

    @Test
    fun testIsRunningLiveData() = runBlockingTest {
        // given
        val expectedIsRunning = true
        val isRunning : Boolean?

        // when
        underTest.setIsRunning(expectedIsRunning)

        isRunning = underTest.isRunningLiveData.getOrAwaitValue()

        // then
        assert(isRunning == expectedIsRunning)
    }

    @Test
    fun testIntervalLiveData() = runBlockingTest {
        // given
        val expectedIntervalInMillis = 60000L
        val interval : Long?

        // when
        underTest.setInterval(expectedIntervalInMillis)

        interval = underTest.intervalLiveData.getOrAwaitValue()

        // then
        assert(interval == expectedIntervalInMillis)
    }
}