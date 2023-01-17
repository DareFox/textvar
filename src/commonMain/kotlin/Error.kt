import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.math.exp

/**
 * Exception class representing error made by user (e.g.: no arguments, wrong border)
 *
 * This will be printed without stack and classname. Only message
 */
class UserError(message: String?) : Exception(message)


/**
 * Throws an [UserError] with the result of calling [message] if the value is false.
 */
fun requireUser(expect: Boolean, message: () -> String) {
    if (!expect) {
        throw UserError(message())
    }
}

/**
 * Throws an [UserError] with the result of calling [message] if the value is null.
 */
@OptIn(ExperimentalContracts::class)
fun <T> requireUserNotNull(expect: T?, message: () -> String): T {
    contract {
        returnsNotNull()
    }

    if (expect == null) {
        throw UserError(message())
    } else {
        return expect
    }
}

