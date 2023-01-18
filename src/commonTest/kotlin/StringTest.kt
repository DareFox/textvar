import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class StringTest {
    @Test
    fun testUnderlineStringRange() {
        assertFails { underlineString(-3..0, "easy") }
        assertFails { underlineString(5..7, "easy") }
        assertEquals(
            expected =
            "easy\n" +
            "^^^ ", actual = underlineString(0..2, "easy")
        )
        assertEquals(
            expected =
            "easy\n" +
            "^^  ", actual = underlineString(0..1, "easy")
        )
        assertEquals(
            expected =
            "easy hehe\n" +
            "    ^^^  ", actual = underlineString(4..6, "easy hehe")
        )
    }

    @Test
    fun testUnderlineIndexRange() {
        assertFails { underlineString(-1, "easy") }
        assertFails { underlineString(5, "easy") }
        assertEquals(
            expected =
            "easy hehe\n" +
            "    ^    ", actual = underlineString(4, "easy hehe")
        )
        assertEquals(
            expected =
            "easy h\n" +
            "^     ", actual = underlineString(0, "easy hehe")
        )
    }
}