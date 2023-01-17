fun main(args: Array<String>) {
    try {
        actualMain(parseArguments(args))
    } catch (ex: UserError) {
        if (ex.message != null) {
            println(ex.message)
        }
        exitCode(1)
    }
}

fun actualMain(arguments: Arguments) {
    unfoldTemplate(arguments).forEach {
        println(it)
    }
}

expect fun exitCode(code: Int)