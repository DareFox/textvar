const val DEFAULT_LEFT_BRACKET = '['
const val DEFAULT_RIGHT_BRACKET = ']'
const val DEFAULT_SEPARATOR = ";"

val arguments = mapOf<String, String>(
    "--help" to "Show this help message",
    "--text" to "Text with template. Required",
    "--borderLeft" to "Character representing start of the template border. Default is $DEFAULT_LEFT_BRACKET",
    "--borderRight" to "Character representing end of the template border. Default is $DEFAULT_RIGHT_BRACKET",
    "--separator" to "Separator of template. Default is $DEFAULT_SEPARATOR",
    "--border" to "Two characters representing start and end of the border. \"--border {}\" equals to \"--borderLeft { --borderRight }\""
)

fun parseArguments(args: Array<String>): Arguments {
    var keyWords = arguments.keys
    val keyWordsValues = mutableMapOf<String, String>()
    val isArgumentLikeRegex = "--\\w*".toRegex()
    var previous = ""
    args.forEach {
        if (it == "--help") {
            showHelp(null)
        }

        if (previous == "--border") {
            requireUser(it.length == 2) {
                "--border accept only two characters representing left and right border"
            }

            keyWordsValues["--borderLeft"] = it[0].toString()
            keyWordsValues["--borderRight"] = it[1].toString()
        }

        if (it !in keyWords && isArgumentLikeRegex.find(it) != null && previous != "--text") {
            showHelp("Unknown argument $it")
        }

        if (previous in keyWords) {
            if (it in keyWords) {
                showHelp("$previous argument is empty")
            }

            keyWordsValues[previous] = it
        }



        previous = it
    }

    val text = keyWordsValues["--text"] ?: showHelp("--text argument is empty")

    if (previous in keyWords) {
        showHelp("$previous argument is empty")
    }

    val left = keyWordsValues["--borderLeft"]?.let {
        if (it.length != 1) {
            showHelp("Length of left border character should be 1, not ${it.length} ($it)")
        }
        it[0]
    } ?: DEFAULT_LEFT_BRACKET
    val right = keyWordsValues["--borderRight"]?.let {
        if (it.length != 1) {
            showHelp("Length of right border character should be 1, not ${it.length} ($it)")
        }
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
        val wideKey = it.key.padEnd(longestString.length, ' ')
        println("${wideKey}\t\t\t${it.value}")
    }

    throw UserError("")
}