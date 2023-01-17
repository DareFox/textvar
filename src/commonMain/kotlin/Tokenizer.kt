// TODO: Make functions private AND testable

fun borderErrorCheck(arguments: Arguments) {
    // stack of indexes of opened brackets
    val stack = mutableListOf<Int>()

    arguments.text.forEachIndexed { index, char ->
        if (char == arguments.leftBoundSymbol) {
            stack.add(index)
        }

        if (char == arguments.rightBoundSymbol) {
            requireUser(stack.isNotEmpty()) {
                "End of non-existing border at index $index\n\n" + underlineString(index, arguments.text)
            }

            stack.removeLast()
        }
    }

    requireUser(stack.isEmpty()) {
        val last = stack.last()
        "Not closed border at index $last\n\n" + underlineString(last, arguments.text)
    }
}

fun unfoldTemplate(arguments: Arguments): Set<String> {
    borderErrorCheck(arguments)

    val borderLeft = regexEscapeIfNecessary(arguments.leftBoundSymbol)
    val borderRight = regexEscapeIfNecessary(arguments.rightBoundSymbol)
    val regex = "$borderLeft(?!.*?$borderLeft).*?$borderRight".toRegex()
    var textVariations = setOf<String>(arguments.text)

    while (true) {
        val newTextVariations = mutableSetOf<String>()
        var foundAnyTemplate = false

        textVariations.forEach { text ->
            val template = regex.find(text) ?: return@forEach
            foundAnyTemplate = true
            newTextVariations += getTextVariations(template, text, arguments)
        }

        if (!foundAnyTemplate) {
            break
        }

        textVariations = newTextVariations
    }

    return textVariations
}

private fun getTextVariations(matchResult: MatchResult, text: String, arguments: Arguments): Set<String> {
    val isEmpty = (matchResult.range.last - matchResult.range.first) == 1

    if (isEmpty) {
        return setOf(text.replaceRange(matchResult.range, ""))
    }

    val rangeWithoutBorders = matchResult.range.first + 1 until matchResult.range.last;
    val values = text.substring(rangeWithoutBorders).split(arguments.separatorSymbol)

    val textVariations = values.map {
        text.replaceRange(matchResult.range, it)
    }.toSet()

    return textVariations
}

private fun regexEscapeIfNecessary(char: Char): String {
    val isLetter = "\\w".toRegex().find(char.toString()) != null

    return if (isLetter) {
        char.toString()
    } else "\\${char}"
}