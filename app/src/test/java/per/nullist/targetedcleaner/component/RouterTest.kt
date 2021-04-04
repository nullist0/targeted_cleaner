package per.nullist.targetedcleaner.component

import android.content.Context
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit

class RouterTest {
    @get:Rule
    val mockito = MockitoJUnit.rule()!!

    private lateinit var underTest: Router

    @Before
    fun setUp() {
        underTest = Router
    }

    @Test
    fun testRouter() {
        // given
        var isExecuted = false

        val cls = RouterTest::class
        val executor: RouterExecutor = {_, _ -> isExecuted = true}
        val context: Context = mock(Context::class.java)

        // when
        underTest.add(cls, executor)
        underTest.start(cls, context)

        // then
        assert(isExecuted)
    }
}