fun main(args: Array<String>) {
    try {
        actualMain(parseArguments(args))
    } catch (ex: Throwable) {
        if (ex.message?.isNotEmpty() == true) {
            println(ex.message)
        }
    }
}

fun actualMain(arguments: Arguments) {
    println(underlineString(0, "hey my name is"))
}