
import kotlin.test.Test
import kotlin.test.assertFails

class TokenaizerTest {
    @Test
    fun testBorderErrorCheck() {
        assertFails { borderErrorCheck(defaultSettings("[[]")) }
        assertFails { borderErrorCheck(defaultSettings("[[")) }
        assertFails { borderErrorCheck(defaultSettings("[")) }
        assertFails { borderErrorCheck(defaultSettings("[][][]]")) }
        assertFails { borderErrorCheck(defaultSettings("]")) }
        assertFails { borderErrorCheck(defaultSettings("[][")) }
        borderErrorCheck(defaultSettings("[]"))
        borderErrorCheck(defaultSettings("[][]"))
        borderErrorCheck(defaultSettings("[][][]              [              ]"))
    }

    fun defaultSettings(text: String): Arguments {
        return Arguments(text, DEFAULT_LEFT_BRACKET, DEFAULT_RIGHT_BRACKET, DEFAULT_SEPARATOR)
    }
}