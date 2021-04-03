package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.SettingRepository

@ExperimentalCoroutinesApi
class MainSwitchEventHandlerImplTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    private lateinit var handler: MainSwitchEventHandler

    private lateinit var settingRepository: SettingRepository

    @Before
    fun setUp() {
        settingRepository = mock(SettingRepository::class.java)
        testCoroutineDispatcher = TestCoroutineDispatcher()
        handler = MainSwitchEventHandlerImpl(settingRepository, testCoroutineDispatcher)
    }

    @Test
    fun testSetIsRunningIsFalse() = runBlockingTest {
        // given

        // when
        handler.setIsRunning(false)

        // then
        verify(settingRepository).setIsRunning(false)
    }

    @Test
    fun testSetIsRunningIsTrue() = runBlockingTest {
        // given

        // when
        handler.setIsRunning(true)

        // then
        verify(settingRepository).setIsRunning(true)
    }
}