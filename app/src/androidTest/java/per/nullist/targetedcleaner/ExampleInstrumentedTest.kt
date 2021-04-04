package per.nullist.targetedcleaner

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivityImpl>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("per.nullist.targetedcleaner", appContext.packageName)
    }
//
//    @Test
//    fun composeTest() {
//        composeTestRule.setContent {
//            Text("ha")
//        }
//
//        composeTestRule.onNode(hasText("hi")).assertExists("no exi")
//    }
}