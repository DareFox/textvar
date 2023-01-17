
import kotlin.test.Test
import kotlin.test.assertEquals
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

    @Test
    fun testUnfoldTemplate() {
        assertEquals(
            expected = setOf("hi", "hello", "bye"),
            actual = unfoldTemplate(defaultSettings("[hi;hello;bye]"))
        )
        assertEquals(
            expected = setOf("hi world", "hi test", "hi kotlin"),
            actual = unfoldTemplate(defaultSettings("hi [world;test;kotlin]"))
        )
        assertEquals(
            expected = setOf("apple", "auto", "bee", "bus", "bye", "bruh", "cya", "cool", "cat"),
            actual = unfoldTemplate(defaultSettings("[a[pple;uto];b[ee;us;ye;ruh];c[ya;ool;at]]"))
        )
        assertEquals(
            expected = setOf(
                "i have apple",
                "i have auto",
                "i have cya",
                "i have cool",
                "i have cat",
                "i've had apple",
                "i've had auto",
                "i've had cya",
                "i've had cool",
                "i've had cat"
            ),
            actual = unfoldTemplate(defaultSettings("[i have;i've had] [a[pple;uto];c[ya;ool;at]]"))
        )
    }

    fun defaultSettings(text: String): Arguments {
        return Arguments(text, DEFAULT_LEFT_BRACKET, DEFAULT_RIGHT_BRACKET, DEFAULT_SEPARATOR)
    }

    @Test
    fun testRegexEscapeIfNecessary() {
        assertEquals(regexEscapeIfNecessary('w'), "w")
        assertEquals(regexEscapeIfNecessary('e'), "e")
        assertEquals(regexEscapeIfNecessary('9'), "9")
        assertEquals(regexEscapeIfNecessary('['), "\\[")
        assertEquals(regexEscapeIfNecessary(']'), "\\]")
        assertEquals(regexEscapeIfNecessary('+'), "\\+")
        assertEquals(regexEscapeIfNecessary('/'), "\\/")
        assertEquals(regexEscapeIfNecessary('0'), "0")
    }
}