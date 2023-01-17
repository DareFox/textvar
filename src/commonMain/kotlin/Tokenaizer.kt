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