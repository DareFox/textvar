import kotlin.system.exitProcess

actual fun exitCode(code: Int) {
    exitProcess(code)
}