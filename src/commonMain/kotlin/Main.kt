fun main(args: Array<String>) {
    try {
        actualMain(parseArguments(args))
    } catch(ex: UserError) {
        if (ex.message != null) {
            println(ex.message)
        }
    }
}

fun actualMain(arguments: Arguments) {
    unfoldTemplate(arguments).forEach {
        println(it)
    }
}