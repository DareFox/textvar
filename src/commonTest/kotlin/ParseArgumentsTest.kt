import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class ParseArgumentsTest {
    @Test
    fun testParseArguments() {
        assertFails { parseArguments(arrayOf("--text")) }
        assertFails { parseArguments(arrayOf("--text", "hehe", "--imaginaryArgument")) }
        assertFails { parseArguments(arrayOf("--imaginaryArgument")) }
        assertFails { parseArguments(arrayOf("--help")) }
        assertFails { parseArguments(arrayOf()) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--border")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--border", "{")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--border", "{}}")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--borderLeft", "{}")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--borderRight", "{}")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--borderRight", "{", "--separator")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--borderRight")) }
        assertFails { parseArguments(arrayOf("--text", "smth", "--borderRight", "--borderLeft", "[")) }

        parseArguments(arrayOf("--text", "smth", "--border", "{}"))
        parseArguments(arrayOf("--text", "--imaginaryArgumentButAsText", "--border", "{}"))
        parseArguments(arrayOf("--text", "smth", "--borderLeft", "{"))
        parseArguments(arrayOf("--text", "smth", "--borderRight", "}"))
        parseArguments(arrayOf("--text", "smth", "--borderRight", "}"))

        val mixed = parseArguments(arrayOf(
            "--text",
            "smth",
            "--borderRight",
            "}",
            "--border",
            "[]",
            "--borderLeft",
            "{",
            "--separator",
            "!"
        ))

        assertEquals(
            ']',
            mixed.rightBoundSymbol
        )
        assertEquals(
            '{',
            mixed.leftBoundSymbol
        )
        assertEquals(
            '!'.toString(),
            mixed.separatorSymbol
        )
    }
}