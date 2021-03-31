package per.nullist.targetedcleaner.view_model.event_handler

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.SettingRepository

class MainSwitchEventHandlerImplTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var handler: MainSwitchEventHandler

    private lateinit var settingRepository: SettingRepository

    @Before
    fun setUp() {
        settingRepository = mock(SettingRepository::class.java)
        handler = MainSwitchEventHandlerImpl(settingRepository)
    }

    @Test
    fun testSetIsRunningIsFalse() {
        // given

        // when
        handler.setIsRunning(false)

        // then
        verify(settingRepository).isRunning = false
    }

    @Test
    fun testSetIsRunningIsTrue() {
        // given

        // when
        handler.setIsRunning(true)

        // then
        verify(settingRepository).isRunning = true
    }
}