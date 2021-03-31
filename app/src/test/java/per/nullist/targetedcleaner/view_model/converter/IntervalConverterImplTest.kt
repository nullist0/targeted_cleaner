package per.nullist.targetedcleaner.view_model.converter

import org.junit.Before
import org.junit.Test

class IntervalConverterImplTest {

    private lateinit var converter: IntervalConverter

    @Before
    fun setUp() {
        converter = IntervalConverterImpl()
    }

    @Test
    fun testConvertToMillis() {
        // given
        val minute = 10

        // when
        val millis = converter.convertToMillis(minute)

        // then
        assert(millis == 600000L) { "10 min is 600000 millis" }
    }

    @Test
    fun testConvertToMin() {
        // given
        val millis = 600000L

        // when
        val minute = converter.convertToMin(millis)

        // then
        assert(minute == 10) { "10 min is 600000 millis" }
    }
}