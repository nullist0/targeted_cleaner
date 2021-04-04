package per.nullist.targetedcleaner.view_model.event_handler

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import per.nullist.targetedcleaner.entity.SettingRepository
import per.nullist.targetedcleaner.view_model.converter.IntervalConverter

@ExperimentalCoroutinesApi
class TimerSettingEventHandlerImplTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    private lateinit var underTest: TimerSettingEventHandler

    private lateinit var converter: IntervalConverter
    private lateinit var settingRepository: SettingRepository

    @Before
    fun setUp() {
        converter = Mockito.mock(IntervalConverter::class.java)
        settingRepository = Mockito.mock(SettingRepository::class.java)

        testCoroutineDispatcher = TestCoroutineDispatcher()

        underTest = TimerSettingEventHandlerImpl(converter, settingRepository, testCoroutineDispatcher)
    }

    @Test
    fun testSetInterval() = runBlockingTest {
        // given
        val intervalInMinute = 10
        val intervalInMillis = 60000L
        given(converter.convertToMillis(intervalInMinute)).willReturn(intervalInMillis)

        // when
        underTest.setInterval(intervalInMinute)

        // then
        verify(settingRepository).setInterval(intervalInMillis)
    }
}