package per.nullist.targetedcleaner

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun list() {
        val list = mock(MutableList::class.java)
        given(list[0]).willReturn(3)
        assert(list[0] == 3)
        given(list[0]).willReturn(4)
        assert(list[0] == 4)
    }
}