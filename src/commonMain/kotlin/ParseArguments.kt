const val DEFAULT_LEFT_BRACKET = '['
const val DEFAULT_RIGHT_BRACKET = ']'
const val DEFAULT_SEPARATOR = ";"

val arguments = mapOf<String, String>(
    "--text" to "Text with template. Required",
    "--borderLeft" to "Character representing start of the template border. Default is $DEFAULT_LEFT_BRACKET",
    "--borderRight" to "Character representing end of the template border. Default is $DEFAULT_RIGHT_BRACKET",
    "--separator" to "Separator of template. Default is $DEFAULT_SEPARATOR",
    "--border" to "Two characters representing start and end of the border. \"--border {}\" equals to \"--borderLeft { --borderRight }\""
)

fun parseArguments(args: Array<String>): Arguments {
    var keyWords = arguments.keys
    val keyWordsValues = mutableMapOf<String, String>()

    var previous = ""
    args.forEach {
        if (previous == "--border") {
            requireUser(it.length == 2) {
                "--border accept only two characters representing left and right border"
            }

            keyWordsValues["--borderLeft"] = it[0].toString()
            keyWordsValues["--borderRight"] = it[1].toString()
        }

        if (previous in keyWords) {
            keyWordsValues[previous] = it
        }

        previous = it
    }


    val text = keyWordsValues["--text"] ?: showHelp("--text argument is empty")

    val left = keyWordsValues["--borderLeft"]?.let {
        requireUser(it.length == 1) { "Length of border character is 1" }
        it[0]
    } ?: DEFAULT_LEFT_BRACKET
    val right = keyWordsValues["--borderRight"]?.let {
        requireUser(it.length == 1)  { "Length of border character is 1" }
        it[0]
    } ?: DEFAULT_RIGHT_BRACKET
    val separator = keyWordsValues["--separator"] ?: DEFAULT_SEPARATOR

    return Arguments(text, left, right, separator)
}

fun showHelp(message: String?): Nothing {
    if (message != null) {
        println(message)
        println()
    }

    val longestString = arguments.keys.maxBy { it.length }

    arguments.forEach {
        val wideKey = it.key.makeStringWideAs(longestString)
        println("${wideKey}\t\t\t${it.value}")
    }

    throw UserError("")
}