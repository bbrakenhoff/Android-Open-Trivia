package com.bbrakenhoff.opentrivia

import androidx.test.platform.app.InstrumentationRegistry
import com.bbrakenhoff.opentrivia.model.TriviaCategory
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.Assert.assertEquals

import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bbrakenhoff.opentrivia", appContext.packageName)
    }
}
